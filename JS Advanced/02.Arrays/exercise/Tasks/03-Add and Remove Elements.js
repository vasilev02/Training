/**
 * @param {[]} array 
 */

function solve(array) {

    let newArray = [];

    let counter = 1;

    for (let index = 0; index < array.length; index++) {

        let command = array[index];

        if (command === 'add') {

            newArray.push(counter++);

        } else if (command === 'remove') {

            newArray.pop();

            counter++;

        }

    }

    console.log(newArray.length > 0 ? newArray.join('\n') : 'Empty');

}

solve(['add',
       'add',
       'remove',
       'add',
       'add']
);