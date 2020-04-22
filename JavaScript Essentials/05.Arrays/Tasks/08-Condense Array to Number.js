function solve(input) {

    let resizingArray = [];

    for (let index = 0; index < input.length; index++) {

        resizingArray.push(parseInt(input[index]));

    }

    let changingArray = [];


    while (resizingArray.length > 1) {

        for (let index = 0; index < resizingArray.length - 1; index++) {

            let a = resizingArray[index];

            let b = resizingArray[index + 1];

            changingArray.push(a + b);

        }

        resizingArray = changingArray;
        changingArray = [];

    }

    console.log(resizingArray[0]);

}

solve(['1', '2', '5', `10`]);