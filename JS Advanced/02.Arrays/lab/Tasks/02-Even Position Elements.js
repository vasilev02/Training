function solve(array) {

    let result = '';

    for (let index = 0; index < array.length; index += 2) {

        const element = array[index];

        result += (element + ' ');

    }

    console.log(result);

}

solve(['20', '30', '40']);