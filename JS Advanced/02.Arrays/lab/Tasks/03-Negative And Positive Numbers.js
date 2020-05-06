function solve(array) {

    let newArray = [];

    for (let index = 0; index < array.length; index++) {

        const element = array[index];

        if (element < 0) {

            newArray.unshift(element);

        } else {

            newArray.push(element);

        }

    }

    newArray.forEach(e => console.log(e));

}

solve([3, -2, 0, -1]);