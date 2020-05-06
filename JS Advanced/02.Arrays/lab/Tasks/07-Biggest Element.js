function solve(matrix) {

    let number = Number.MIN_SAFE_INTEGER;

    for (let index = 0; index < matrix.length; index++) {

        let currentArray = matrix[index];

        for (let index = 0; index < currentArray.length; index++) {

            let element = currentArray[index];

            if (element > number) {

                number = element;

            }

        }

    }

    console.log(number);

}

solve([ [20, 50, 10],
        [8, 33, 145] ]
);