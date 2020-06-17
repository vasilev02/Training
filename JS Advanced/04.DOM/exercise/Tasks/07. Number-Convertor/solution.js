function solve() {

    let number = Number(document.getElementById('input').value);

    let resultArea = document.querySelector('input[id="result"]');

    let selectToSection = document.getElementById('selectMenuTo');

    let createOptionBinary = document.createElement('option');
    createOptionBinary.textContent = 'Binary';
    createOptionBinary.value = 'binary';


    let createOptionHexa = document.createElement('option');
    createOptionHexa.textContent = 'Hexadecimal';
    createOptionHexa.value = 'hexadecimal';

    selectToSection.appendChild(createOptionBinary);
    selectToSection.appendChild(createOptionHexa);

    let button = document.getElementsByTagName('button')[0];

    button.addEventListener('click', calc);

    function calc() {

        if (selectToSection.value === 'binary') {

            let num = (number >>> 0).toString(2);

            resultArea.value = num;

        } else {

            let num = number.toString(16).toUpperCase();

            resultArea.value = num;

        }

    }



}