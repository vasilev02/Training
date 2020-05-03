function solve(first, second, third) {

    let totalLength = 0;

    totalLength += first.length;
    totalLength += second.length;
    totalLength += third.length;

    console.log(totalLength);

    let result = Math.floor(totalLength / 3);

    console.log(result);

}

solve('chocolate', 'ice cream', 'cake');