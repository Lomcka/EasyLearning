const loginForm = document.getElementById('login-form');
const errorMessage = document.getElementById('error-message');

loginForm.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(loginForm);
    const data = {};

    for (const [key, value] of formData.entries()) {
        data[key] = value;
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8070/EasyLearning/check-account', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);

                if (response.success) {
                    window.location.href = 'main.html';
                } else {
                    errorMessage.style.display = 'block';
                    errorMessage.textContent = 'Неверный логин или пароль';
                }
            } else {
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'Ошибка сервера';
            }
        }
    };

    xhr.send(JSON.stringify(data));
});