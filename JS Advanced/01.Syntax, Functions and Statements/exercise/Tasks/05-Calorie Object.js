function solve(input) {

    let result = {};

    for (let index = 0; index < input.length; index += 2) {

        result[input[index]] = parseInt(input[index + 1]);

    }

    console.log(result);

}

solve(['Yoghurt', '48', 'Rise', '138', 'Apple', '52']);