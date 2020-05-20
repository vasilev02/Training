function solve(input) {

    let number = parseInt(input[0]);

    let functions = {

        chop: (x) => x / 2,

        dice: (x) => Math.sqrt(x),

        spice: (x) => x + 1,

        bake: (x) => x * 3,

        fillet: (x) => 0.8 * x

    }

    for (let index = 1; index < input.length; index++) {

        number = functions[input[index]](number);

        console.log(number);

    }

}

solve(['32', 'chop', 'chop', 'chop', 'chop', 'chop']);
