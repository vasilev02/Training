function solve(a, b, c) {


    let biggerNumber = a >= b ? a : b;

    if (biggerNumber >= c) {

        console.log(`The largest number is ${biggerNumber}.`);

    } else {

        console.log(`The largest number is ${c}.`);

    }

}

solve(5, -3, 16);