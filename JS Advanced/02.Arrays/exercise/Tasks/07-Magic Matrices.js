function solve(matrix) {

    let check = true;

    // sum to check

    let sum = matrix[0].reduce((acc, element) => acc + element, 0);

    //moving through lines and columns

    for (let index = 0; index < matrix.length; index++) {

        let currentLine = matrix[index].reduce((acc, element) => acc + element, 0);

        let currentColumn = 0;

        for (let i = 0; i < matrix.length; i++) {

            currentColumn += matrix[i][index];

        }

        if (currentLine !== sum || currentColumn !== sum) {

            console.log('false');

            check = false;

            break;
        }

    }

    // check finally if they are equal

    if (check === true) {

        console.log('true');

    }

}

solve([[4, 5, 6],
       [6, 5, 4],
       [5, 5, 5]]
);