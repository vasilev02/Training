function solve() {

    let table = document.querySelector('ol[type="A"]');

    let button = document.querySelector('button[type="button"]');

    button.addEventListener('click', addName);

    function addName() {

        let inputText = document.querySelector('input[type="text"]').value;
        inputText = inputText.toLowerCase();

        let allLines = table.getElementsByTagName('li');

        let index = Number(inputText.charCodeAt(0));

        let charCode = index - 97;

        let wordToArray = inputText.split('');
        wordToArray[0] = wordToArray[0].toUpperCase();
        inputText = wordToArray.join('');

        let li = allLines[charCode];

        let arr = li.textContent.split(', ');

        if (arr[0] === '') {

            li.textContent = inputText;

        } else {



            arr.push(inputText);

            let data = arr.join(', ');

            li.textContent = data;
        }



    }

}