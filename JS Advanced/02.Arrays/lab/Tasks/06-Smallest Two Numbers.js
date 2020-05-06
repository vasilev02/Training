function solve(array) {

    array.sort((a, b) => a - b);

    let resultArray = array.slice(0, 2);

    console.log(resultArray.join(' '));

}

solve([3, 0, 10, 4, 7, 3]);