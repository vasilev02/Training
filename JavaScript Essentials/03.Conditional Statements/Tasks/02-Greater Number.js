function solve(input) {

    let numberOne = Number(input.shift());
    let numberTwo = Number(input.shift());

    if (numberOne > numberTwo) {

        console.log(`${numberOne}`);

    }else{
        
        console.log(`${numberTwo}`);

    }

}

solve([`5`,`3`]);
