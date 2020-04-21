function solve(input) {

    let counter = Number(input.shift());

    let MAX_NUMBER = -9999999;
    let MIN_NUMBER = 9999999;

    for (let i = 1; i <= counter; i++) {

        let currentNumber = Number(input.shift());

        if (currentNumber > MAX_NUMBER) {
            MAX_NUMBER = currentNumber;
        }

        if (currentNumber < MIN_NUMBER) {
            MIN_NUMBER = currentNumber;
        }

    }

    console.log(`Max number: ${MAX_NUMBER}`);
    console.log(`Min number: ${MIN_NUMBER}`);

}

solve([`2`, `10`, `-5`]);