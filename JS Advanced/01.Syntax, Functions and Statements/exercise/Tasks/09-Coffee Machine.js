function solve(input) {

    let moneyGain = 0;

    for (let index = 0; index < input.length; index++) {

        let totalSumForCurrentOrder = 0;

        let currentOrder = input[index].split(', ');

        let puttedCoins = Number(currentOrder[0]);

        let typeOfDrink = currentOrder[1];

        if (typeOfDrink === 'coffee') {

            let typeOfCoffee = currentOrder[2];

            if (typeOfCoffee === 'decaf') {

                totalSumForCurrentOrder += 0.90;

            } else if (typeOfCoffee === 'caffeine') {

                totalSumForCurrentOrder += 0.80;

            }

        } else if (typeOfDrink === 'tea') {

            totalSumForCurrentOrder += 0.80;

        }

        if (input[index].includes('milk')) {

            let number = totalSumForCurrentOrder * 0.10;
            let procent = Math.ceil(number);
            procent /= 10;

            totalSumForCurrentOrder += procent;

        }

        let quantityOfSugar = currentOrder[currentOrder.length - 1];

        if (quantityOfSugar > 0 && quantityOfSugar <= 5) {
            totalSumForCurrentOrder += 0.10;
        }

        if (totalSumForCurrentOrder <= puttedCoins) {

            console.log(`You ordered ${typeOfDrink}. Price: \$${totalSumForCurrentOrder.toFixed(2)} Change: \$${(puttedCoins - totalSumForCurrentOrder).toFixed(2)}`);

            moneyGain += totalSumForCurrentOrder;

        } else {

            console.log(`Not enough money for ${typeOfDrink}. Need \$${(totalSumForCurrentOrder - puttedCoins).toFixed(2)} more`);

        }

    }

    console.log(`Income Report: \$${moneyGain.toFixed(2)}`);


}
solve(['1.00, coffee, caffeine, milk, 4', '0.40, tea, milk, 2', '1.00, coffee, decaf, 0']);