let toPrint = makeOrder("water", 5);

function makeOrder(item, number) {

    let product = item;
    let quantity = Number(number);


    if (product === "water") {

        let priceForWater = x => x * 1.00;
        let finalPrice = priceForWater(quantity);
        console.log(`${finalPrice.toFixed(2)}`);

    } else if (product === "coffee") {

        let priceForCoffee = x => x * 1.50;
        let finalPrice = priceForCoffee(quantity);
        console.log(`${finalPrice.toFixed(2)}`);

    } else if (product === "coke") {

        let priceForCoke = x => x * 1.40;
        let finalPrice = priceForCoke(quantity);
        console.log(`${finalPrice.toFixed(2)}`);

    }else{

        let priceForSnacks = x => x * 2.00;
        let finalPrice = priceForSnacks(quantity);
        console.log(`${finalPrice.toFixed(2)}`);

    }

}