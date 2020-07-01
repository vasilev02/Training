function solve() {

    let dataField = document.querySelectorAll('input[type="text"]');

    let nameField = dataField[0];
    let ageField = dataField[1];
    let kindField = dataField[2];
    let currentOwnerField = dataField[3];

    let button = document.getElementsByTagName('button')[0];

    button.addEventListener('click', findNewFriend);

    function findNewFriend(e) {

        e.preventDefault();

        if (nameField.value === '' || ageField.value === '' || kindField.value === '' || currentOwnerField.value === '') {
            return;
        }
        if (!(Number.isInteger(Number(ageField.value)))) {
            return;
        }

        let getSection = document.getElementById('adoption');

        let getList = getSection.getElementsByTagName('ul')[0];


        let createListItem = document.createElement('li');

        let createParagraph = document.createElement('p');

        let createFirstStrong = document.createElement('strong');
        createFirstStrong.textContent = nameField.value;
        createParagraph.appendChild(createFirstStrong);

        createParagraph.innerHTML += ' is a ';

        let createSecondStrong = document.createElement('strong');
        createSecondStrong.textContent = ageField.value;
        createParagraph.appendChild(createSecondStrong);

        createParagraph.innerHTML += ' year old ';


        let createThirdStrong = document.createElement('strong');
        createThirdStrong.textContent = kindField.value;
        createParagraph.appendChild(createThirdStrong);

        createListItem.appendChild(createParagraph);


        getList.appendChild(createListItem);
        createListItem.appendChild(createParagraph);

        let createSpan = document.createElement('span');
        createSpan.textContent = `Owner: ${currentOwnerField.value}`
        createListItem.appendChild(createSpan);


        let createButton = document.createElement('button');
        createButton.textContent = 'Contact with owner';
        createListItem.appendChild(createButton);

        createButton.addEventListener('click', changeButton);


        nameField.value = '';
        ageField.value = '';
        kindField.value = '';
        currentOwnerField.value = '';


    }

    function changeButton(e) {

        let box = e.target.parentNode;

        let section = e.target.parentNode.parentNode;

        let oldButton = box.getElementsByTagName('button')[0].remove();

        let createDiv = document.createElement('div');

        let createInput = document.createElement('input');
        createInput.setAttribute('placeholder', 'Enter your names');

        let newButton = document.createElement('button');
        newButton.textContent = 'Yes! I take it!';

        newButton.addEventListener('click', movePet);

        createDiv.appendChild(createInput);
        createDiv.appendChild(newButton);

        box.appendChild(createDiv);

    }

    function movePet(e) {

        let getDiv = e.target.parentNode;

        let box = e.target.parentNode.parentNode;

        let inputField = getDiv.getElementsByTagName('input')[0];

        if (inputField.value === '') {
            return;
        }


        let getSection = document.getElementById('adopted');

        let owner = box.getElementsByTagName('span')[0];
        owner.textContent = `New Owner: ${inputField.value}`;

        let newBtn = document.createElement('button');
        newBtn.textContent = 'Checked';
        box.appendChild(newBtn);

        newBtn.addEventListener('click', removePet);

        e.target.parentNode.remove();

        getSection.appendChild(box);

    }

    function removePet(e) {

        e.target.parentNode.remove();

    }

}

