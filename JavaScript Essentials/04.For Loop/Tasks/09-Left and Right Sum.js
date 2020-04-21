function solve(input) {

    let counter = Number(input.shift());

    let leftSum = 0;
    let rightSum = 0;

    for (let i = 1; i <= counter; i++) {

        let number = Number(input.shift());

        leftSum += number;

    }

    for (let i = 1; i <= counter; i++) {

        let number = Number(input.shift());

        rightSum += number;

    }


    if (leftSum == rightSum) {

        console.log(`Yes, sum = ${leftSum}`);

    } else {

        console.log(`No, diff = ${Math.abs(leftSum - rightSum)}`);


    }

}

solve([`1`, `1`, `1`]);