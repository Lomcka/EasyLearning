function renderModules(content) {
    const moduleContainer = document.getElementById("modules-container");
    moduleContainer.innerHTML = "";

    const cardDiv = document.createElement("div");
    cardDiv.classList.add("module-card");
    content.forEach((card) => {

        const wordHeader = document.createElement("li");
        wordHeader.textContent = `${card.word} - ${card.translation}`;
        cardDiv.appendChild(wordHeader);

        moduleContainer.appendChild(cardDiv);
    });
}


function showCards() {
    const moduleName = window.location.search.split('=')[1];
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        queryType: 'get-cards-to-repeat',
        moduleName: moduleName
    }))
        .then(response => response.json())
        .then(data => {
            // Сохраняем исходный порядок карточек в переменную originalOrder
            let originalOrder = data.map((_, index) => index);
            renderCards(data, originalOrder);
        })
        .catch(error => console.error(error));
    window.location.href = `cards.html?moduleName=${encodeURIComponent(window.location.search.split('=')[1])}`;
}

function showModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'block';
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'none';
}

function addCard(event) {
    event.preventDefault();

    const moduleName = window.location.search.split('=')[1];
    const wordInput = document.getElementById('word-input');
    const translationInput = document.getElementById('translation-input');

    const cardData = {
        moduleName: moduleName,
        word: wordInput.value,
        translation: translationInput.value
    };

    wordInput.value = '';
    translationInput.value = '';

    // Send cardData to localhost:8070 using fetch
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'add-Card'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cardData)
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
}

function shareModule(event) {
    event.preventDefault();

    const usernameInput = document.getElementById('username-input');
    const passwordInput = document.getElementById('password-input');

    const shareData = {
        username: usernameInput.value,
        password: passwordInput.value,
        moduleName: window.location.search.split("=")[1]
    };

    usernameInput.value = '';
    passwordInput.value = '';

// Send shareData to localhost:8070 using fetch
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'share-module'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(shareData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                alert('Успешно поделились');
            } else if (data.status === 'not success') {
                alert('Поделиться не получилось. Проверьте логин и кодовое слово');
            } else if (data.status === 'exists') {
                alert('У этого пользователя уже есть модуль с таким именем');
            }
            closeModal('share-module-form');
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function goBack() {
    window.history.back();
}

// Fetch moduleData from localhost:8070 using fetch

function loadCards() {
    const moduleName = window.location.search.split("=")[1];
    fetch(
        "http://localhost:8070/EasyLearning?" +
        new URLSearchParams({
            queryType: "get-module-data",
            moduleName: moduleName,
        })
    )
        .then((response) => response.json())
        .then((data) => {
            // Handle the response from the server
            console.log(data);
            document.getElementById("module-title").textContent = data.name;
            renderModules(data.content);
        })
        .catch((error) => {
            // Handle any errors
            console.error("Error:", error);
        });
}

loadCards();