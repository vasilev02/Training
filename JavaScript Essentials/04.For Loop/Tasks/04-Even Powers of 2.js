function solve(input) {

    let number = input.shift();

    for (let index = 0; index <= number; index++) {

        if (index % 2 == 0) {

            console.log(Math.pow(2, index));
        }

    }

}

solve([`2`]);