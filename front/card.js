const urlParams = new URLSearchParams(window.location.search);
const jsonData = urlParams.get('data');

const data = JSON.parse(jsonData);

const okPower = data.ok.length;
const repeatPower = data.repeat.length;
const totalPower = okPower + repeatPower;

const chartData = [
{
values: [okPower, totalPower - okPower],
labels: ['OK', 'Repeat'],
type: 'pie'
}
];

Plotly.newPlot('chart', chartData);

const continueBtn = document.getElementById('continue-btn');
const resetBtn = document.getElementById('reset-btn');

continueBtn.addEventListener('click', () => {
fetch('/card-data', {
method: 'POST',
body: JSON.stringify(data)
})
.then(() => {
window.location.href = '/card.html?data=' + JSON.stringify(data);
});
});

resetBtn.addEventListener('click', () => {
const newData = {
ok: [...data.ok, ...data.repeat],
repeat: []
};

fetch('/card-data', {
method: 'POST',
body: JSON.stringify(newData)
})
.then(() => {
window.location.href = '/card.html?data=' + JSON.stringify(newData);
});
});

А код на стороне сервера может выглядеть так:

const express = require('express');
const app = express();
const bodyParser = require('body-parser');

app.use(bodyParser.json());

// Статические файлы
app.use(express.static('public'));

// Обработчик POST-запроса для сохранения данных
app.post('/card-data', function(req, res) {
const data = req.body;

// Сохранение данных в базе данных или другом хранилище

// Отправка страницы card.html с данными в качестве параметров запроса
res.redirect('/card.html?data=' + JSON.stringify(data));
});

// Запуск сервера на порту
app.listen(8070, function() {
console.log('Сервер запущен на порту 8070');
});