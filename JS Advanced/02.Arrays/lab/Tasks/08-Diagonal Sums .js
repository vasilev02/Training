function solve(matrix) {

    let diagonalUpLeftToDownRight = 0;

    let diagonalDownLeftToUpRight = 0;

    for (let index = 0; index < matrix.length; index++) {

        let currentArray = matrix[index];

        diagonalUpLeftToDownRight += currentArray[index];

    }

    let size = matrix.length - 1;

    for (let index = 0; index < matrix.length; index++) {

        let currentArray = matrix[index];

        diagonalDownLeftToUpRight += currentArray[size--];

    }

    console.log(diagonalUpLeftToDownRight + ' ' + diagonalDownLeftToUpRight);

}

solve([ [3, 5, 17],
        [-1, 7, 14],
        [1, -8, 89]]
);