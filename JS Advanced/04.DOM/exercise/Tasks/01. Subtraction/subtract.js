function subtract() {

    let firstNumber = Number(document.getElementById('firstNumber').value);

    let secondNumber = Number(document.getElementById('secondNumber').value);

    let section = document.getElementById('result');

    section.textContent = firstNumber - secondNumber;



}