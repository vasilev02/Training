function solve() {

    let task = document.getElementById('task');
    let description = document.getElementById('description');
    let dueDate = document.getElementById('date');

    let button = document.getElementById('add');

    button.addEventListener('click', addTask);

    function addTask() {

        if (task.value === "") {
            return;
        }
        if (description.value === '') {
            return;
        }
        if (dueDate.value === '') {
            return;
        }

        let getCurrentSection = document.getElementsByTagName('section')[1];
        let currentDiv = getCurrentSection.getElementsByTagName('div')[1];
        let createArticle = document.createElement('article');

        let createHeader = document.createElement('h3');
        createHeader.textContent = task.value;

        let createPDescription = document.createElement('p');
        createPDescription.textContent = "Description: " + description.value;

        let createPDate = document.createElement('p');
        createPDate.textContent = "Due Date: " + dueDate.value;

        let createDiv = document.createElement('div');
        createDiv.classList.add('flex');

        let buttonOne = document.createElement('button');
        buttonOne.textContent = 'Start';
        buttonOne.classList.add('green');

        let buttonTwo = document.createElement('button');
        buttonTwo.textContent = 'Delete';
        buttonTwo.classList.add('red');

        buttonTwo.addEventListener('click', deleteArticle);
        buttonOne.addEventListener('click', changeToProgress);

        createDiv.appendChild(buttonOne);
        createDiv.appendChild(buttonTwo);

        createArticle.appendChild(createHeader);
        createArticle.appendChild(createPDescription);
        createArticle.appendChild(createPDate);
        createArticle.appendChild(createDiv);

        currentDiv.appendChild(createArticle);


    }

    function deleteArticle(e) {

        e.target.parentNode.parentNode.remove();

    }

    function changeToProgress(e) {

        let kids = e.target.parentNode.parentNode.childNodes;
        let header = kids[0];
        let pDescr = kids[1];
        let pDate = kids[2];

        let getCurrentSection = document.getElementsByTagName('section')[2];
        let currentDiv = getCurrentSection.getElementsByTagName('div')[1];
        let createArticle = document.createElement('article');

        let createHeader = document.createElement('h3');
        createHeader.textContent = header.textContent;

        let createPDescription = document.createElement('p');
        createPDescription.textContent = pDescr.textContent;

        let createPDate = document.createElement('p');
        createPDate.textContent = pDate.textContent;

        let createDiv = document.createElement('div');
        createDiv.classList.add('flex');

        let buttonOne = document.createElement('button');
        buttonOne.textContent = 'Delete';
        buttonOne.classList.add('red');

        let buttonTwo = document.createElement('button');
        buttonTwo.textContent = 'Finish';
        buttonTwo.classList.add('orange');

        buttonOne.addEventListener('click', deleteArticle);
        buttonTwo.addEventListener('click', moveToFinal);

        createDiv.appendChild(buttonOne);
        createDiv.appendChild(buttonTwo);

        createArticle.appendChild(createHeader);
        createArticle.appendChild(createPDescription);
        createArticle.appendChild(createPDate);
        createArticle.appendChild(createDiv);

        currentDiv.appendChild(createArticle);

    }

    function moveToFinal(e) {

        let kids = e.target.parentNode.parentNode.childNodes;
        let header = kids[0];
        let pDescr = kids[1];
        let pDate = kids[2];

        deleteArticle(e);

        let getCurrentSection = document.getElementsByTagName('section')[3];
        let currentDiv = getCurrentSection.getElementsByTagName('div')[1];
        let createArticle = document.createElement('article');

        let createHeader = document.createElement('h3');
        createHeader.textContent = header.textContent;

        let createPDescription = document.createElement('p');
        createPDescription.textContent = pDescr.textContent;

        let createPDate = document.createElement('p');
        createPDate.textContent = pDate.textContent;

        createArticle.appendChild(createHeader);
        createArticle.appendChild(createPDescription);
        createArticle.appendChild(createPDate);

        currentDiv.appendChild(createArticle);

    }



}