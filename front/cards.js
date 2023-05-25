const menuButton = document.querySelector('.menu-button');
const moreButton = document.querySelector('.more-button');
const menu = document.querySelector('.menu');
const cardContainer = document.querySelector('.card-container');

let originalOrder = [];

// Функция для загрузки карточек из JSON-файла
function loadCards() {
  fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
              'queryType': 'get-cards-to-repeat'
          }), {
    .then(response => response.json())
    .then(data => {
      // Сохраняем исходный порядок карточек в переменную originalOrder
      originalOrder = data.map((_, index) => index);
      renderCards(data, originalOrder);
    })
    .catch(error => console.error(error));
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

// Обработчик клика на кнопку "Menu"
menuButton.addEventListener('click', () => {
  menu.classList.toggle('show');
});

// Обработчик клика на кнопку "More"
moreButton.addEventListener('click', () => {
  menu.classList.toggle('show');
});

// Обработчик изменения значения радио-кнопок в выпадающем меню
document.querySelectorAll('input[name="sort-by"]').forEach(radio => {
  radio.addEventListener('change', event => {
    switch (event.target.value) {
      case 'ok':
        renderCards(cardsData, sortCardsByStatus('ok'));
        break;
      case 'repeat':
        renderCards(cardsData, sortCardsByStatus('repeat'));
        break;
      case 'original-order':
        renderCards(cardsData, originalOrder);
        break;
      default:
        console.error(`Unknown sort option: ${event.target.value}`);
    }
  });
});

// Загружаем карточки при запуске страницы
loadCards();