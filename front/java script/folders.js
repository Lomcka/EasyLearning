function showModal(modalId) {
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => modal.style.display = 'none');

    const modal = document.getElementById(modalId);
    modal.style.display = 'block';
}

function createModuleButtons(modules) {
    const modulesContainer = document.getElementById('folders-container');
    modulesContainer.innerHTML = '';

    modules.forEach(module => {
        const button = document.createElement('button');
        button.textContent = module.name;
        button.classList.add('module-button');
        button.addEventListener('click', () => {
            window.location.href = `modules.html?moduleData=${encodeURIComponent(module.name)}`;
        });
        modulesContainer.appendChild(button);
    });
}

function addModule(event) {
    event.preventDefault();
    const nameInput = document.getElementById('word-input');
    const moduleName = nameInput.value;
    const folderName = window.location.search.split('=')[1];

    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'add-module-to-folder'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            moduleName: moduleName,
            folderName: folderName
        })
    })
        .then(response => response.json())
        .then(() => {
            closeModal('add-card-form');
            nameInput.value = '';
            fetchModules(folderName);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'none';
}

function goBack() {
    window.location.href='main.html';
}

function fetchModules(folderName) {
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'get-modules',
        'folderName': folderName
    }))
        .then(response => response.json())
        .then(data => {
            createModuleButtons(data.modules);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function init() {
    const folderName = window.location.search.split('=')[1];
    const titleElement = document.getElementById('folder-title');
    titleElement.textContent = folderName;

    fetchModules(folderName);
}

init();