function solve(count, array) {

    let elements = [];

    for (let index = 0; index < array.length; index++) {
        elements.push(array[index]);

    }

    let textResult = "";


    for (let index = count - 1; index >= 0; index--) {

        textResult += array[index] + " ";

    }

    console.log(textResult);

}

solve(2, [66, 43, 75, 89, 47]);