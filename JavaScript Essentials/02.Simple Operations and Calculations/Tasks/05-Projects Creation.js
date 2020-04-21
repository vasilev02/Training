function solve(input) {

    let name = input.shift();

    let projectsCount = Number(input.shift());

    let hoursToWork = projectsCount * 3;


    console.log(`The architect ${name} will need ${hoursToWork} hours to complete ${projectsCount} project/s.`);

}

solve([`Valentin`, `5`]);