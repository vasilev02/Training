function solve(input) {

    let array = [];

    for (let index = 0; index < input.length; index++) {

        let number = parseInt(input[index]);

        array.push(number);

    }

    let sumOfEvenNumbers = 0;
    let sumOfOddNumbers = 0;


    for (let index = 0; index < array.length; index++) {

        if (array[index] % 2 == 0) {

            sumOfEvenNumbers += array[index];

        } else {

            sumOfOddNumbers += array[index];

        }

    }

    console.log(sumOfEvenNumbers - sumOfOddNumbers);

}

solve(['1', '2', '3', '4', '5']);