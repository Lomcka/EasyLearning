const confirmPasswordInput = document.querySelector('input[name="confirm-password"]');
const passwordInput = document.querySelector('input[name="password"]');
const passwordMismatchMessage = document.querySelector('.password-mismatch-message');

function checkPasswordMatch() {
    if (confirmPasswordInput.value !== passwordInput.value) {
        confirmPasswordInput.classList.add('password-mismatch');
        passwordMismatchMessage.style.display = 'block';
    } else {
        confirmPasswordInput.classList.remove('password-mismatch');
        passwordMismatchMessage.style.display = 'none';
    }
}

const createAccountForm = document.getElementById('create-account-form');

createAccountForm.addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(createAccountForm);
    const data = {};

    for (const [key, value] of formData.entries()) {
        data[key] = value;
    }

    if (data.password !== data['confirm-password']) {
        passwordMismatchMessage.style.display = 'block';
        return;
    }

    passwordMismatchMessage.style.display = 'none';

    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8070/EasyLearning/create-account', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const response = JSON.parse(xhr.responseText);

            if (response.exists) {
                alert(`Аккаунт с логином "${data.username}" уже существует.`);
            } else {
                alert(`Аккаунт с логином "${data.username}" успешно создан.`);
                window.location.href = 'main.html';
            }
        }
    };

    xhr.send(JSON.stringify(data));
});

function goBack() {
    window.location.href='main.html';
}