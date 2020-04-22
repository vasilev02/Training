function solve(input) {

    let array = [];

    for (let index = 0; index < input.length; index++) {
        
        array.push(input[index]);
        
    }

    let textResult = "";


    for (let index = array.length - 1; index >= 0; index--) {

        textResult += array[index] + " ";

    }

    console.log(textResult);

}

solve(['1', '2', '3', '4', '5']);