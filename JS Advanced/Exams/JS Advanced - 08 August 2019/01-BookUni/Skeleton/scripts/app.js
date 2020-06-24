function solve() {

    let titleField = document.querySelector('input[type="text"]');

    let yearField = document.querySelector('input[type="number"]');

    let priceField = document.querySelectorAll('input[type="number"]')[1];

    let buttonField = document.getElementsByTagName('button')[0];

    buttonField.addEventListener('click', addBook);

    function addBook(e) {

        e.preventDefault();

        let oldSection = document.getElementsByTagName('section')[0];
        let oldShelf = oldSection.getElementsByTagName('div')[0];

        let newSection = document.getElementsByTagName('section')[1];
        let newShelf = newSection.getElementsByTagName('div')[0];

        //Validation

        if (titleField.value === '' || Number(priceField.value) < 0 || Number(yearField.value) < 0) {
            return;
        }

        let createDiv = document.createElement('div');
        createDiv.classList.add('book');

        let paragraph = document.createElement('p');
        paragraph.textContent = `${titleField.value} [${yearField.value}]`;

        let buttonOne = document.createElement('button');
        buttonOne.textContent = `Buy it only for ${Number(priceField.value).toFixed(2)} BGN`;

        buttonOne.addEventListener('click', buyBook);

        let buttonTwo = document.createElement('button');
        buttonTwo.textContent = `Move to old section`;

        buttonTwo.addEventListener('click', changeBook);

        createDiv.appendChild(paragraph);
        createDiv.appendChild(buttonOne);

        if (Number(yearField.value) >= 2000) {

            createDiv.appendChild(buttonTwo);

            newShelf.appendChild(createDiv);


        } else {

            buttonOne.textContent = `Buy it only for ${(Number(priceField.value) * 0.85).toFixed(2)} BGN`;
            oldShelf.appendChild(createDiv);

        }



    }

    function buyBook(e) {

        e.target.parentNode.remove();

        let info = e.target.textContent.split(' ');
        let amount = Number(info[4]);

        let profitField = document.getElementsByTagName('h1')[1];
        let data = profitField.textContent.split(' ');
        data[3] = (Number(data[3]) + amount).toFixed(2);

        profitField.textContent = data.join(' ');

    }

    function changeBook(e) {

        let book = e.target.parentNode;

        e.target.parentNode.remove();

        book.getElementsByTagName('button')[1].remove();

        let firstButton = book.getElementsByTagName('button')[0];

        let data = firstButton.textContent.split(' ');

        let amount = Number(data[4]) * 0.85;

        data[4] = amount.toFixed(2);

        firstButton.textContent = data.join(' ');

        let oldSection = document.getElementsByTagName('section')[0];
        let oldShelf = oldSection.getElementsByTagName('div')[0];

        oldShelf.appendChild(book);

    }

}