function solve(a, b) {

    let firstNumber = Number(a);

    let secondNumber = Number(b);

    let sum = 0;

    for (let index = firstNumber; index <= secondNumber; index++) {

        sum += index;

    }

    console.log(sum);

}

solve('-8', '20');