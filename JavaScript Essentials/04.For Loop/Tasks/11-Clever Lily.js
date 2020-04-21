function solve(input) {

    let ageOfLilly = Number(input.shift());

    let washingMachinePrice = Number(input.shift());

    let priceForOneToy = Number(input.shift());

    let totalSum = 0;
    let toyCounter = 0;
    let risingSum = 10.0;

    for (let index = 1; index <= ageOfLilly; index++) {

        if (index % 2 == 0) {

            totalSum += risingSum - 1;

            risingSum += 10;

        } else {

            toyCounter++;

        }

    }

    totalSum += (toyCounter * priceForOneToy);



    if (totalSum >= washingMachinePrice) {

        console.log(`Yes! ${(totalSum - washingMachinePrice).toFixed(2)}`);

    } else {

        console.log(`No! ${(washingMachinePrice - totalSum).toFixed(2)}`);

    }

}

solve([`10`, `170.00`, `6`]);