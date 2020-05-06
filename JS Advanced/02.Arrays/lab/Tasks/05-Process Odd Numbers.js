function solve(array) {

    array = array.map(e => e * 2);

    let result = '';

    for (let index = array.length - 1; index >= 0; index--) {

        if (index % 2 !== 0) {

            result += array[index] + ' ';

        }

    }

    console.log(result);

}

solve([10, 15, 20, 25]);