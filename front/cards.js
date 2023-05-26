const cardContainer = document.querySelector('.card-container');
const modal = document.querySelector('.modal');
const modalTitle = document.querySelector('.modal-header .title');
const okButton = document.querySelector('.modal-footer .ok-button');
const repeatButton = document.querySelector('.modal-footer .repeat-button');
const closeButton = document.querySelector('.modal-header .close-button');
let currentIndex = 0;
let cards = [];
let randomOrder = true;
let showWordFirst = true;
let okCards = [];
let repeatCards = [];

function showModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'block';
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'none';
}
function showCard(index) {
    const card = cards[index];
    const currentCard = document.createElement('div');
    currentCard.classList.add('card');
    currentCard.innerHTML = `
    <div class="word">${showWordFirst ? card.word : card.translation}</div>
    <div class="translation">${showWordFirst ? card.translation : card.word}</div>
  `;
    cardContainer.innerHTML = '';
    cardContainer.appendChild(currentCard);
}

function toggleTranslation() {
    const currentCard = cardContainer.querySelector('.card');
    currentCard.classList.toggle('show-translation');
}

function toggleRandomOrder() {
    randomOrder = !randomOrder;
}

function toggleShowWordFirst() {
    showWordFirst = !showWordFirst;
}

function handleRightAnswer() {
    const currentCard = cardContainer.querySelector('.card');
    currentCard.classList.add('right-answer');
    setTimeout(() => {
        currentIndex++;
        if (currentIndex >= cards.length) {
            window.location.href = `card.html?ok=${JSON.stringify(okCards)}&repeat=${JSON.stringify(repeatCards)}`;
        } else {
            currentCard.classList.remove('right-answer');
            showCard(currentIndex);
        }
    }, 500);
    okCards.push(cards[currentIndex]);
}

function handleWrongAnswer() {
    const currentCard = cardContainer.querySelector('.card');
    currentCard.classList.add('wrong-answer');
    setTimeout(() => {
        currentIndex++;
        if (currentIndex >= cards.length) {
            window.location.href = `card.html?ok=${JSON.stringify(okCards)}&repeat=${JSON.stringify(repeatCards)}`;
        } else {
            currentCard.classList.remove('wrong-answer');
            showCard(currentIndex);
        }
    }, 500);
    repeatCards.push(cards[currentIndex]);
}

function openModal() {
    modal.style.display = 'flex';
}

function initModal() {
    modalTitle.textContent = 'Settings';
    repeatButton.textContent = 'Заново';
    closeButton.innerHTML = '&times;';
    closeButton.addEventListener('click', closeModal);
    okButton.addEventListener('click', () => {
        toggleRandomOrder();
        closeModal();
    });
    repeatButton.addEventListener('click', () => {
        toggleRandomOrder();
        closeModal();
    });
}

document.addEventListener('keydown', event => {
    if (event.code === 'Space') {
        toggleTranslation();
    } else if (event.code === 'ArrowRight') {
        handleRightAnswer();
    } else if (event.code === 'ArrowLeft') {
        handleWrongAnswer();
    }
});

function toggleOrder() {
    var orderText = document.getElementById("order-text");
    if (orderText.innerHTML === "Прямой порядок") {
        orderText.innerHTML = "Перемешанные";
        // Добавляем функционал для опции "Перемешанные"
        toggleRandomOrder();
    } else {
        orderText.innerHTML = "Прямой порядок";
        // Добавляем функционал для опции "Прямой порядок"
        toggleShowWordFirst();
    }
}

// Функция для загрузки карточек из JSON-файла
function loadCards() {
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'get-cards-to-repeat'
    })
        .then(response => response.json())
        .then(data => {
            // Сохраняем исходный порядок карточек в переменную originalOrder
            originalOrder = data.map((_, index) => index);
            renderCards(data, originalOrder);
        })
        .catch(error => console.error(error)));
}

// Функция для отображения карточек на странице
function renderCards(cards, order) {
    cardContainer.innerHTML = '';
    order.forEach(i => {
        const card = document.createElement('div');
        const front = document.createElement('div');
        const back = document.createElement('div');

        card.classList.add('card');
        front.classList.add('front');
        back.classList.add('back');
        if (cards[i].status === 'ok') {
            card.classList.add('ok');
        } else if (cards[i].status === 'repeat') {
            card.classList.add('repeat');
        }

        front.textContent = cards[i].front;
        back.textContent = cards[i].back;

        card.appendChild(front);
        card.appendChild(back);

        // Добавляем обработчик клика на карточку
        card.addEventListener('click', () => {
            back.classList.toggle('show');
        });

        cardContainer.appendChild(card);
    });
}


loadCards();
initModal();
