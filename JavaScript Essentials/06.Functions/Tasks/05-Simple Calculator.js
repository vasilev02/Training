calculate(5,5,'add')

function calculate(a, b, operation) {

    let numberOne = a;
    let numberTwo = b;
    let operator = operation;


    if (operator === "multiply") {

        console.log(`${numberOne * numberTwo}`);

    } else if (operator === "add") {

        console.log(`${numberOne + numberTwo}`);

    } else if (operator === "subtract") {

        console.log(`${numberOne - numberTwo}`);

    } else {

        console.log(`${numberOne / numberTwo}`);

    }

}