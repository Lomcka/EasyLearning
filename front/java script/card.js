let okCards = [];
let repeatCards = [];


const continueBtn = document.getElementById('continue-btn');
const resetBtn = document.getElementById('reset-btn');

function goBack() {
    window.location.href='main.html';
}

function loadArrays() {
    fetch('http://localhost:8070/EasyLearning/resend/resend-ok-repeat2')
        .then(response => response.json())
        .then(data => {
            okCards = data.ok;
            repeatCards = data.repeat;
            console.log(okCards);
            console.log(repeatCards);

            const chartData = [
                {
                    values: [okCards.length, repeatCards.length],
                    labels: ['OK', 'Repeat'],
                    type: 'pie'
                }
            ];

            Plotly.newPlot('chart', chartData);
        })
        .catch(error => console.error(error));
}

continueBtn.addEventListener('click', () => {
    fetch('http://localhost:8070/EasyLearning/resend/resend-ok-repeat' , {
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
            window.location.href = `cards.html`;
        })
        .catch(error => {
            // Handle any errors
            console.error('Error:', error);
        });

});


resetBtn.addEventListener('click', () => {
    okCards.forEach(data => {
        repeatCards.push(data);
    });
    const newData = {
        ok: [],
        repeat: repeatCards
    };

    fetch('http://localhost:8070/EasyLearning/resend/resend-ok-repeat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newData)
    })
        .then(response => response.json())
        .then(data => {
            // Handle the response from the server
            console.log(data);
            window.location.href = `cards.html`;
        })
        .catch(error => {
            // Handle any errors
            console.error('Error:', error);
        });
});

loadArrays();