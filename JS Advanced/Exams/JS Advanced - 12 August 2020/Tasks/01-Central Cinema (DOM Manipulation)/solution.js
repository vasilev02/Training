function solve() {

    let divContainer = document.getElementById('container').children;
    let nameField = divContainer[0];
    let hallField = divContainer[1];
    let priceField = divContainer[2];
    let screenButton = divContainer[3];

    let clearButton = document.getElementsByTagName('button')[1];

    clearButton.addEventListener('click', function (e) {

        let section = document.getElementById('archive');
        let ul = section.children[1];

        ul.innerHTML = '';

    })


    screenButton.addEventListener('click', function (e) {

        e.preventDefault();

        if (nameField.value === '' || hallField.value === '' || priceField.value === '') {
            return;
        }
        if (!Number(priceField.value)) {
            return;
        }



        let section = document.getElementsByTagName('section')[0];
        let ul = section.children[1];

        let createListItem = document.createElement('li');

        let span = document.createElement('span');
        span.textContent = nameField.value;

        let strong = document.createElement('strong');
        strong.textContent = `Hall: ${hallField.value}`;

        let div = document.createElement('div');

        let innerStrong = document.createElement('strong');
        let price = Number(priceField.value);
        innerStrong.textContent = `${price.toFixed(2)}`

        let innerInput = document.createElement('input');
        innerInput.setAttribute('placeholder', 'Tickets Sold');

        let archiveButton = document.createElement('button');
        archiveButton.textContent = 'Archive';
        archiveButton.addEventListener('click', moveToArchive);

        div.appendChild(innerStrong);
        div.appendChild(innerInput);
        div.appendChild(archiveButton);

        createListItem.appendChild(span);
        createListItem.appendChild(strong);
        createListItem.appendChild(div);

        ul.appendChild(createListItem);

        //!!!!!!!! Clear the inputs 

        nameField.value = '';
        hallField.value = '';
        priceField.value = '';


    });

    function moveToArchive(e) {

        let item = e.target.parentNode.parentNode;

        let kids = item.children;

        let span = kids[0];
        let strong = kids[1];
        let div = kids[2];

        let buttonche = div.children[2];

        let priceToken = div.children[0];

        let price = Number(priceToken.textContent);

        let input = div.children[1];

        if (!Number(input.value)) {
            return;
        }

        let result = price * input.value;

        div.outerHTML = '';

        strong.textContent = `Total amount: ${result.toFixed(2)}`;

        buttonche.textContent = 'Delete';

        item.appendChild(buttonche);
        buttonche.addEventListener('click', deleteItem);

        let section = document.getElementById('archive');
        let ul = section.children[1];

        ul.appendChild(item);

    }

    function deleteItem(e) {

        e.target.parentNode.remove();

    }

}