solve(-1, 0, 1);

function solve(a, b, c) {

    let numberOne = a;
    let numberTwo = b;
    let numberThree = c;


    if (numberOne >= 0 && numberTwo >= 0 && numberThree >= 0) {

        console.log("Positive");

    } else if (numberOne < 0 && numberTwo < 0 && numberThree >= 0) {

        console.log("Positive");

    } else if (numberOne < 0 && numberTwo >= 0 && numberThree < 0) {

        console.log("Positive");

    } else if (numberOne >= 0 && numberTwo < 0 && numberThree < 0) {

        console.log("Positive");

    } else if (numberOne == 0 || numberTwo == 0 || numberThree == 0) {

        console.log("Positive");

    } else {

        console.log("Negative");

    }

}