function solve(firstNumber, secondNUmber, operator) {

    switch (operator) {

        case '+':

            console.log(firstNumber + secondNUmber);

            break;

        case '*':

            console.log(firstNumber * secondNUmber);

            break;

        case '-':

            console.log(firstNumber - secondNUmber);

            break;

        case '/':

            console.log(firstNumber / secondNUmber);

            break;

        case '**':

            console.log(firstNumber ** secondNUmber);

            break;

        case '%':

            console.log(firstNumber % secondNUmber);

            break;


    }

}

solve(3, 5.5, '*');