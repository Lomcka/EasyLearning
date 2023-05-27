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

function showModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'block';
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'none';
}

// function showCard(index) {
//     const card = cards[index];
//     currentCard.classList.add('card');
//     currentCard.innerHTML = `
// <div class="word">${showWordFirst ? card.word : card.translation}</div>
// <div class="translation">${showWordFirst ? card.translation : card.word}</div>
// `;
//   cardContainer.innerHTML = '';
//   cardContainer.appendChild(currentCard);
// }

function toggleTranslation() {
    const currentCard = cardContainer.querySelector('.card');
    // console.log("34: "+currentCard.textContent);
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
                resolve(event);
            }
        }, {once: true});
    });
}

function Relocate() {
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'resend-ok-repeat'
    }), {
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
        // console.log(currentIndex);
        // console.log(cards.length);
        if (currentIndex >= cards.length) {
            Relocate();
        } else {
            currentCard.classList.remove('right-answer');
            // showCard(order[currentIndex]);
        }
        currentCard.remove(); // Remove the card from the DOM after the animation
    }, 500);
    okCards.push(cards[order[currentIndex]]);
}

function handleWrongAnswer() {
    const currentCard = cardContainer.querySelector('.card');
    currentCard.classList.add('wrong-answer');
    setTimeout(() => {
        currentIndex++;
        if (currentIndex >= cards.length) {
            Relocate();
        } else {
            currentCard.classList.remove('wrong-answer');
            // showCard(order[currentIndex]);
        }
        currentCard.remove(); // Remove the card from the DOM after the animation
    }, 500);
    repeatCards.push(cards[order[currentIndex]]);
}

function Repeat() {
    // toggleRandomOrder();
    closeModal('settings');
}

document.addEventListener('keydown', event => {
    // console.log("keydown");
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
    // console.log("I\'m loading cards");
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        queryType: 'resend-ok-repeat'
    }))
        .then(response => response.json())
        .then(data => {
            // Сохраняем исходный порядок карточек в переменную originalOrder
            let originalOrder = data.map((_, index) => index);
            order = originalOrder;
            renderCards(data, originalOrder);
        })
        .catch(error => console.error(error));
    // showCard(0);
}

// Функция для отображения карточек на странице
async function renderCards(cards, order) {
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