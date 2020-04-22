let toPrint = mathOperaton(2, 8);
console.log(toPrint);

function mathOperaton(a,b) {

    let nummber = Number(a);
    let power = Number(b);

    let result = Math.pow(nummber, power);

    return result;
}

