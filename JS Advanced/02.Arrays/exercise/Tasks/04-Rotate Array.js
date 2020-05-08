/**
 * @param {[]} array 
 */

function solve(array) {

    let counter = +array.pop() % array.length;

    for (let index = 0; index < counter; index++) {

        let current = array.pop();

        array.unshift(current);

    }

    console.log(array.join(' '));

}

solve(['1',
       '2',
       '3',
       '4',
       '2']
);