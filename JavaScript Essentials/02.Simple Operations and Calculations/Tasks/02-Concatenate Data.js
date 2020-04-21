function solve(input) {

    let name = input.shift();
    let lastName = input.shift();
    let age = Number(input.shift());
    let town = input.shift();

    console.log(`You are ${name} ${lastName}, a ${age}-years old person from ${town}.`);

}

solve([`Valentin`, `Vasilev`, `18`, `Sofia`]);