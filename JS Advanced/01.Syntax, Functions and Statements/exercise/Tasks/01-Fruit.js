function solve(fruit, grams, pricePerKilo) {

    let weightInKilo = grams / 1000;

    let sum = weightInKilo * pricePerKilo;

    console.log(`I need \$${sum.toFixed(2)} to buy ${weightInKilo.toFixed(2)} kilograms ${fruit}.`);


}

solve('orange', 2500, 1.80);