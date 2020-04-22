function solve(firstArray, secondArray) {

    let arrayOne = [];
    let arrayTwo = [];

    let sum = 0;

    for (let index = 0; index < firstArray.length; index++) {

        let number = parseInt(firstArray[index]);
        sum += number;

        arrayOne.push(number);

    }

    for (let index = 0; index < secondArray.length; index++) {

        let number = parseInt(secondArray[index]);

        arrayTwo.push(number);

    }


    for (let index = 0; index < arrayOne.length; index++) {

        if (arrayOne[index] != arrayTwo[index]) {

            console.log(`Arrays are not identical. Found difference at ${index} index`);

            return
        }

    }

    console.log(`Arrays are identical. Sum: ${sum}`);

}

solve(['1', '2', '5'], [`1`, `2`, `3`]);