function solve(array) {

    let firstResult = sum(array);
    console.log(firstResult);

    let secondResult = sumWithDeleteByOne(array);
    console.log(secondResult);

    let thirdResult = concat(array);
    console.log(thirdResult);

    //Make methods to help

    function sum(array) {

        let sum = 0;

        for (let index = 0; index < array.length; index++) {

            sum += array[index];

        }

        return sum;

    }

    function sumWithDeleteByOne(array) {

        let sum = 0;

        for (let index = 0; index < array.length; index++) {

            sum += 1 / array[index];

        }

        return sum;

    }

    function concat(array) {

        let concatResult = "";

        for (let index = 0; index < array.length; index++) {

            concatResult += array[index];

        }

        return concatResult;

    }

}



solve([1, 2, 3]);