function solve() {

    let buttons = document.querySelectorAll('button[type="button"]');

    let operationText = document.getElementById('expressionOutput');

    let resultText = document.getElementById('resultOutput');

    for (let index = 0; index < buttons.length; index++) {

        let current = buttons[index];

        current.addEventListener('click', pressButton);

    }

    function pressButton(e) {

        let press = e.target.innerText;

        if (press === 'C') {

            operationText.textContent = '';
            resultText.textContent = '';

            return;

        }

        if (press === '=') {

            let firstOperand = false;
            let operand = false;
            let secondOperand = false;

            let data = operationText.textContent.split(' ');

            if (typeof Number(data[0]) == 'number' && data[0] !== '') {
                firstOperand = true;
            }
            if (data[1] === '+' || data[1] === '-' || data[1] === 'x' || data[1] === '/') {
                operand = true;
            }
            if (typeof Number(data[2]) == 'number' && data[2] !== '') {
                secondOperand = true;
            }

            if (firstOperand === true && operand === true && secondOperand === true) {

                let result = 0;
                let first = Number(data[0]);
                let second = Number(data[2]);
                let mark = data[1];

                if (mark === '+') {

                    result = first + second;

                } else if (mark === '-') {

                    result = first - second;

                } else if (mark === 'x') {

                    result = first * second;

                } else if (mark === '/') {

                    result = first / second;

                }

                resultText.textContent = result;


            } else {

                resultText.textContent = 'NaN';

            }

        } else {

            if (resultText.textContent !== '') {

                resultText.textContent = '';
                operationText.textContent = press;
                return;

            }


            if (press === '+' || press === '-' || press === 'x' || press === '/') {

                operationText.textContent += " " + press + " ";

            } else {

                operationText.textContent += press;

            }

        }

    }

}