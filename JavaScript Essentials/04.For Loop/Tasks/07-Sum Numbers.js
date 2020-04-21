function solve(input) {

    let counter = input.shift();

    let sum = 0;

    for (let i = 1; i <= counter; i++) {

        let currentNumber = Number(input.shift());

        sum += currentNumber;

    }

    console.log(sum);

}

solve([`2`, `10`, `-5`]);