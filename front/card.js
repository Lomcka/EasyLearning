const urlParams = new URLSearchParams(window.location.search);
const jsonData = urlParams.get('data');

const data = JSON.parse(jsonData);
let okCards = data.ok;
let repeatCards = data.repeat;

const okPower = okCards.length;
const repeatPower = repeatCards.length;
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
    window.location.href = `cards.html`;

});


resetBtn.addEventListener('click', () => {
    const newData = {
        ok: [...data.ok, ...data.repeat],
        repeat: []
    };

    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'resend-ok-repeat'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'ok': newData,
            'repeat': []
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
    window.location.href = `cards.html`;
});