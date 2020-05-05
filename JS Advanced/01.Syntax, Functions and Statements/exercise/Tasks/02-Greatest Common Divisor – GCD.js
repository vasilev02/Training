function solve(a, b) {

    let first = a;
    let second = b;

    while (second !== 0) {

        [first, second] = [second, first % second];

    }

    console.log(first);


}

solve(10, 14);