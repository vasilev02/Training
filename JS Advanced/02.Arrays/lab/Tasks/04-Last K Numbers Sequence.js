function solve(length, k) {

    let outputArray = [1];

    for (let i = 1; i < length; i++) {

        outputArray[i] = sumLastK(outputArray, k);

    }

    function sumLastK(array, k) {

        k = array.length > k ? k : array.length;

        let sum = 0;

        for (let i = 1; i <= k; i++) {
            sum += array[array.length - i];
        }

        return sum;

    }

    console.log(outputArray.join(' '));

}

solve(6, 3);