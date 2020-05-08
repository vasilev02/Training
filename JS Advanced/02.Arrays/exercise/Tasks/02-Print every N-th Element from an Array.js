/**
 * @param {[]} array 
 */

function solve(array) {

    let step = +array.pop();

    for (let index = 0; index < array.length; index += step) {

        console.log(array[index]);

    }

}

solve(['5',
       '20',
       '31',
       '4',
       '20',
       '2']
);