function solve(number) {

    let arrayNumber = number.toString().split('');

    let firstSymbol = arrayNumber[0];

    let check = true;

    let sum = 0;

    for (let index = 0; index < arrayNumber.length; index++) {

        if (firstSymbol !== arrayNumber[index]) {
            check = false;
        }

        sum += parseInt(arrayNumber[index]);

    }

    console.log(check);

    console.log(sum);

}

solve(222222);