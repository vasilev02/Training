function solve(input) {

    let figure = input.shift();


    switch (figure) {

        case "rectangle":

            let length = input.shift();
            let width = input.shift();

            console.log(`${(length * width).toFixed(3)}`)

            break;

        case "circle":

            let radius = input.shift();

            console.log(`${(Math.PI * radius * radius).toFixed(3)}`)

            break;

        case "square":

            let a = input.shift();

            console.log(`${(a * a).toFixed(3)}`)

            break;

        case "triangle":

            let base = input.shift();
            let height = input.shift();

            console.log(`${((base * height) / 2).toFixed(3)}`)

            break;

    }

}

solve([`square`,`5`]);