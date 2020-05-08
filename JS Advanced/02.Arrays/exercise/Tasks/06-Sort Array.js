/**
 * @param {[]} array 
 */

function solve(array) {

    let outputArray = array
        .sort((first, second) => first.length - second.length || first.localeCompare(second));

    console.log(outputArray.join('\n'));

}

solve(['Isacc',
       'Theodor',
       'Jack',
       'Harrison',
       'George']
);