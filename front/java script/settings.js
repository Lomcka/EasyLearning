const confirmPassword = document.getElementById('confirm-password');
const passwordMismatchMessage = document.querySelector('.password-mismatch-message');

function checkPasswordMatch() {
    const currentPassword = document.getElementById('new-password').value;
    if (confirmPassword.value !== '' && confirmPassword.value !== currentPassword) {
        confirmPassword.style.borderColor = 'red';
        passwordMismatchMessage.style.display = 'block';
    } else {
        confirmPassword.style.borderColor = '';
        passwordMismatchMessage.style.display = 'none';
    }
}

function getData() {
    fetch('http://localhost:8070/EasyLearning/get-data-for-settings')
        .then(response => response.json())
        .then(data => {
            document.getElementById('current-login').textContent = data.login;
            document.getElementById('current-security-word').textContent = data.securityWord;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function changeLogin() {
    const newLogin = document.getElementById('new-login').value;

    if (newLogin) {
        const data = {login: newLogin};

        fetch('http://localhost:8070/EasyLearning/change-login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                if (result.success) {
                    document.getElementById('current-login').textContent = newLogin;
                    alert(`Логин успешно изменен`);
                    document.getElementById('settings-form').reset();
                } else {
                    alert(`Этот логин уже занят`);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

function changePassword() {
    const currentPassword = document.getElementById('current-password').value;
    const newPassword = document.getElementById('new-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (newPassword === confirmPassword) {
        const data = {
            currentPassword: currentPassword,
            newPassword: newPassword
        };

        fetch('http://localhost:8070/EasyLearning/change-password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                if (result.success) {
                    alert(`Пароль успешно изменен`);
                    document.getElementById('settings-form-3').reset();
                } else {
                    alert(`Неверный пароль`);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        alert(`Пароли не совпадают`);
        document.getElementById('confirm-password').style.borderColor = 'red';
    }
}

function changeSecurityWord() {
    const newSecurityWord = document.getElementById('new-security-word').value;

    if (newSecurityWord) {
        const data = {securityWord: newSecurityWord};

        fetch('http://localhost:8070/EasyLearning/change-security-word', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                if (result.success) {
                    document.getElementById('current-security-word').textContent = newSecurityWord;
                    alert(`Кодовое слово успешно изменено`);
                    document.getElementById('settings-form-2').reset();
                } else {
                    alert(`Попробуйте другое`);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

function goBack() {
    window.location.href = 'main.html';
}

getData();