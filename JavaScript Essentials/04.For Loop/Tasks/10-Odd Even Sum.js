function solve(input) {

    let counter = Number(input.shift());

    let evenSum = 0;
    let oddSum = 0;

    for (let i = 1; i <= counter; i++) {

        let number = Number(input.shift());

        if (i % 2 == 0) {

            evenSum += number;

        } else {

            oddSum += number;

        }

    }



    if (evenSum == oddSum) {

        console.log(`Sum = ${evenSum}`);

    } else {

        console.log(`Diff = ${Math.abs(evenSum - oddSum)}`);

    }

}

solve([`4`, `10`, `50`, `60`, `20`]);