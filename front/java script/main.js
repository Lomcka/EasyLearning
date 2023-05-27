const modulesContainer = document.getElementById('modules-container');
const foldersContainer = document.getElementById('folders-container');

// Загрузка модулей и папок с сервера
function loadModulesAndFolders() {
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'get-modules-and-folders'
    }))
        .then(response => response.json())
        .then(data => {
            createModuleButtons(data.modules);
            createFolderButtons(data.folders);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Создание кнопок для модулей
function createModuleButtons(modules) {
    modulesContainer.innerHTML = '';

    modules.forEach(module => {
        const button = document.createElement('button');
        button.textContent = module.name;
        button.classList.add('button-link');

        button.addEventListener('click', () => {
            const urlParams = new URLSearchParams();
            urlParams.append('module', module.name);
            window.location.href = `modules.html?${urlParams.toString()}`;
        });

        modulesContainer.appendChild(button);
    });
}

// Создание кнопок для папок
function createFolderButtons(folders) {
    foldersContainer.innerHTML = '';

    folders.forEach(folder => {
        const button = document.createElement('button');
        button.textContent = folder.name;
        button.classList.add('button-link');

        button.addEventListener('click', () => {
            const urlParams = new URLSearchParams();
            urlParams.append('folder', folder.name);
            window.location.href = `folders.html?${urlParams.toString()}`;
        });

        foldersContainer.appendChild(button);
    });
}

// Открытие модального окна для добавления нового модуля
const addModuleModal = document.getElementById('add-module-modal');
const addModuleConfirmButton = document.getElementById('add-module-confirm-button');
const createModuleButton = document.getElementById('create-module-button');

createModuleButton.addEventListener('click', () => {
    addModuleModal.style.display = "block";
});

addModuleConfirmButton.addEventListener('click', () => {
    const moduleNameInput = document.getElementById('module-name-input');

    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'add-new-module'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'moduleName': moduleNameInput.value})
    })
        .then(response => response.json())
        .then(() => {
            closeModal('add-module-modal');
            loadModulesAndFolders();
        })
        .catch(error => {
            console.error('Error:', error);
        });

    addModuleModal.style.display = "none";
    moduleNameInput.value = '';
});

function logOut(){
    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'log-out'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    })
}

// Открытие модального окна для объединения модулей
const mergeModulesModal = document.getElementById('merge-modules-modal');
const mergeModulesConfirmButton = document.getElementById('merge-modules-confirm-button');
const mergeModulesButton = document.getElementById('merge-modules-button');

mergeModulesButton.addEventListener('click', () => {
    mergeModulesModal.style.display = "block";
});

mergeModulesConfirmButton.addEventListener('click', () => {
    const moduleNamesInput = document.getElementById('module-names-input');
    const newModuleNameInput = document.getElementById('new-module-name-input');

    const moduleNames = moduleNamesInput.value.split(',');
    const modules = moduleNames.map(name => ({name}));

    fetch('http://localhost:8070/EasyLearning/module/${newModuleNameInput.value}/merge?' + new URLSearchParams({
        'queryType': 'merge-modules'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(modules)
    })
        .then(response => response.json())
        .then(() => {
            closeModal('merge-modules-modal');
            loadModulesAndFolders();
        })
        .catch(error => {
            console.error('Error:', error);
        });

    mergeModulesModal.style.display = "none";
    moduleNamesInput.value = '';
    newModuleNameInput.value = '';
});

// Открытие модального окна для добавления новой папки
const addFolderModal = document.getElementById('add-folder-modal');
const addFolderConfirmButton = document.getElementById('add-folder-confirm-button');
const createFolderButton = document.getElementById('create-folder-button');

createFolderButton.addEventListener('click', () => {
    addFolderModal.style.display = "block";
});

addFolderConfirmButton.addEventListener('click', () => {
    const folderNameInput = document.getElementById('folder-name-input');

    fetch('http://localhost:8070/EasyLearning?' + new URLSearchParams({
        'queryType': 'add-new-folder'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({folderName: folderNameInput.value})
    })
        .then(response => response.json())
        .then(() => {
            closeModal('add-folder-modal');
            loadModulesAndFolders();
        })
        .catch(error => {
            console.error('Error:', error);
        });

    addFolderModal.style.display = "none";
    folderNameInput.value = '';
});

// Открытие модального окна для объединения папок
const mergeFoldersModal = document.getElementById('merge-folders-modal');
const mergeFoldersConfirmButton = document.getElementById('merge-folders-confirm-button');
const mergeFoldersButton = document.getElementById('merge-folders-button');

mergeFoldersButton.addEventListener('click', () => {
    mergeFoldersModal.style.display = "block";
});

mergeFoldersConfirmButton.addEventListener('click', () => {
    const folderNamesInput = document.getElementById('folder-names-input');
    const newFolderNameInput = document.getElementById('new-folder-name-input');

    const folderNames = folderNamesInput.value.split(',');
    const folders = folderNames.map(name => ({name}));

    fetch('http://localhost:8070/EasyLearning//folder/${newFolderNameInput.value}/merge?' + new URLSearchParams({
        'queryType': 'merge-folders'
    }), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(folders)
    })
        .then(response => response.json())
        .then(() => {
            closeModal('merge-folders-modal');
            loadModulesAndFolders();
        })
        .catch(error => {
            console.error('Error:', error);
        });

    mergeFoldersModal.style.display = "none";
    folderNamesInput.value = '';
    newFolderNameInput.value = '';
});

// Закрытие модальных окон
function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = 'none';
}

document.querySelectorAll('.modal-close').forEach(button => {
    button.addEventListener('click', () => {
        const modal = button.closest('.modal');
        modal.style.display = 'none';
    });
});

// Загрузка модулей и папок при загрузке страницы
loadModulesAndFolders();