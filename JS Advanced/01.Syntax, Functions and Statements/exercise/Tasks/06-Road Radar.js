function solve(input) {

    let speed = input.shift();

    let area = input.shift();

    function calculateOverLimit(currentSpeed, currentLimit) {

        return currentSpeed - currentLimit;

    }

    let printType = (overSpeed) => {

        if (overSpeed <= 0) {

            console.log('');

        } else if (overSpeed <= 20) {

            console.log('speeding');

        } else if (overSpeed <= 40) {

            console.log('excessive speeding');

        } else {

            console.log('reckless driving');

        }

    }

    switch (area) {

        case 'city':

            printType(calculateOverLimit(speed, 50));

            break;

        case 'residential':

            printType(calculateOverLimit(speed, 20));

            break;

        case 'interstate':

            printType(calculateOverLimit(speed, 90));

            break;

        case 'motorway':

            printType(calculateOverLimit(speed, 130));

            break;


    }


}

solve([40, 'city']);