function solve(input) {

    let dogsCount = Number(input.shift());

    let anotherAnimalsCount = Number(input.shift());

    let totalSum = (dogsCount * 2.50) + (anotherAnimalsCount * 4);

    console.log(`${totalSum.toFixed(2)} lv.`);

}

solve([`5`, `4`]);