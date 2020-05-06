function solve(array) {

    let sum = 0;

    sum += parseInt(array[0]);

    sum += parseInt(array[array.length - 1]);

    console.log(sum);

}

solve(['20', '30', '40']);