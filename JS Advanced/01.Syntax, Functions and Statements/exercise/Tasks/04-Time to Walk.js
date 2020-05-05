function solve(steps, stepLength, speed) {

    let totalLength = steps * stepLength;

    let totalRestInMinutes = Math.floor(totalLength / 500);

    let totalTime = totalLength / speed / 1000 * 60;

    let totalTimeInSeconds = Math.ceil((totalRestInMinutes + totalTime) * 60);

    let result = new Date(null, null, null, null, null, totalTimeInSeconds);

    console.log(result.toString().split(' ')[4]);

}

solve(4000, 0.60, 5);