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
    const data = {}; // здесь должен быть ваш объект данных

    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'return-cards-to-repeat'
    }), {
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

    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'return-cards-to-repeat'
    }), {
        method: 'POST',
        body: JSON.stringify(newData)
    })
        .then(() => {
            window.location.href = '/card.html?data=' + JSON.stringify(newData);
        });
});