function solve(input) {

    let array = [];

    for (let index = 0; index < input.length; index++) {

        let number = parseInt(input[index]);

        array.push(number);

    }

    let sum = 0;


    for (let index = 0; index < array.length; index++) {

        if (array[index] % 2 == 0) {

            sum += array[index];

        }

    }

    console.log(sum);

}

solve(['1', '2', '3', '4', '5']);