function solve(matrix) {

    let counter = 0;

    // up to down

    for (let index = 0; index < matrix.length - 1; index++) {

        let currentArray = matrix[index];

        let nextArray = matrix[index + 1];

        for (let index = 0; index < currentArray.length; index++) {

            if (currentArray[index] == nextArray[index]) {

                counter++;

            }

        }

    }

    //left to right

    for (let index = 0; index < matrix.length; index++) {

        let currentArray = matrix[index];

        for (let index = 0; index < currentArray.length - 1; index++) {

            if (currentArray[index] == currentArray[index + 1]) {

                counter++;

            }

        }

    }

    console.log(counter);

}

solve([ ['test', 'yes', 'yo', 'ho'],
         ['well', 'done', 'yo', '6'],
         ['not', 'done', 'yet', '5']]
);