const cardContainer = document.querySelector('.card-container');
let currentIndex = 0;
let cards = [];
let randomOrder = true;
let showWordFirst = true;
let okCards = [];
let repeatCards = [];
let cur_translation = '';
let cur_word = '';
order = []

function goBack() {
    window.location.href = 'main.html';
}

function showModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'block';
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'none';
}

function toggleTranslation() {
    const currentCard = cardContainer.querySelector('.card');
    currentCard.textContent = '';
    currentCard.textContent += cur_translation;
    currentCard.classList.toggle('show-translation');
}

function toggleRandomOrder() {
    randomOrder = !randomOrder;
}

function toggleShowWordFirst() {
    showWordFirst = !showWordFirst;
}

function waitForKeyPress() {
    return new Promise((resolve) => {
        document.addEventListener('keydown', (event) => {
            if (event.code === 'ArrowLeft' || event.code === 'ArrowRight') {
                resolve();
            }
        }, {once: true});
    });
}

function Relocate() {
    fetch('http://localhost:8070/EasyLearning/resend/resend-ok-repeat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'ok': okCards,
            'repeat': repeatCards
        })
    })
        .then(response => response.json())
        .then(data => {
            // Handle the response from the server
            console.log(data);
            closeModal('add-card-form');
            loadCards();
        })
        .catch(error => {
            // Handle any errors
            console.error('Error:', error);
        });
    window.location.href = `card.html`;
}

function handleRightAnswer() {
    const currentCard = cardContainer.querySelector('.card');
    currentCard.classList.add('right-answer');
    setTimeout(() => {
        currentIndex++;
        if (currentIndex >= cards.length) {
            Relocate();
        } else {
            currentCard.classList.remove('right-answer');
        }
        currentCard.remove(); // Remove the card from the DOM after the animation
    }, 500);
    okCards.push(cards[order[currentIndex]]);
}

function handleWrongAnswer() {
    console.log(okCards);
    console.log(repeatCards);
    const currentCard = cardContainer.querySelector('.card');
    currentCard.classList.add('wrong-answer');
    setTimeout(() => {
        currentIndex++;
        if (currentIndex >= cards.length) {
            Relocate();
        } else {
            currentCard.classList.remove('wrong-answer');
        }
        currentCard.remove(); // Remove the card from the DOM after the animation
    }, 500);
    repeatCards.push(cards[order[currentIndex]]);
}

function Repeat() {
    closeModal('settings');
}

document.addEventListener('keydown', event => {
    if (event.code === 'Space') {
        toggleTranslation();
        let temp = cur_translation;
        cur_translation = cur_word;
        cur_word = temp;
    } else if (event.code === 'ArrowRight') {
        handleRightAnswer();
    } else if (event.code === 'ArrowLeft') {
        handleWrongAnswer();
    }
});


// Функция для загрузки карточек из JSON-файла
function loadCards() {
    fetch('http://localhost:8070/EasyLearning/resend/resend-ok-repeat2')
        .then(response => response.json())
        .then(data => {
            // Сохраняем исходный порядок карточек в переменную originalOrder
            cards = data.repeat;
            let i = 0;
            while (i < cards.length) {
                order.push(i);
                ++i;
            }
            console.log(cards);
            console.log(order);
            renderCards(order);
        })
        .catch(error => console.error(error));
    /*cards = [
        {
            'word': 'aba',
            'translation': 'cadaba'
        },
        {
            'word': 'mam',
            'translation': 'мама'
        },
        {
            'word': 'lol',
            'translation': 'кек'
        }
    ]
    let i = 0;
    while (i < cards.length) {
        order.push(i);
        ++i;
    }
    renderCards(order);*/
}

// Функция для отображения карточек на странице
async function renderCards(order) {
    cardContainer.innerHTML = '';
    for (const i of order) {
        const card = document.createElement('div');
        const word = document.createElement('div');
        const translation = document.createElement('div');

        card.classList.add('card');
        word.classList.add('front');
        translation.classList.add('back');

        if (cards[i].status === 'repeat') {
            card.classList.add('repeat');
        }

        word.textContent = showWordFirst ? cards[i].word : cards[i].translation;
        translation.textContent = showWordFirst ? cards[i].translation : cards[i].word;

        cur_translation = translation.textContent;
        cur_word = word.textContent;

        card.appendChild(showWordFirst ? word : translation);
        card.addEventListener('click', (card) => {
            card.classList.toggle('show-translation');
        });

        cardContainer.appendChild(card);
        await waitForKeyPress();
    }
}


loadCards();