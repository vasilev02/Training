function solve(input) {

    let length = Number(input.shift());

    let width = Number(input.shift());

    let heigth = Number(input.shift());

    let procent = Number(input.shift()) * 0.01;;

    let volume = length * heigth * width;

    let totalLiters = volume * 0.001;

    let finalLiters = totalLiters * (1- procent);

    console.log(`${finalLiters.toFixed(3)}`);

}

solve([`85`, `75`, `47`, `17`]);