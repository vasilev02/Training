/**
 * @param {[]} array 
 */

function solve(array) {

    let outputArray = array.reduce((acc, element) => {

        const lastElement = acc[acc.length - 1];

        if (element >= lastElement || lastElement === undefined) {

            acc.push(element);

        }

        return acc;

    }, []);

    console.log(outputArray.join('\n'));

}

solve([1,
       3,
       8,
       4,
       10,
       12,
       3,
       2,
       24]
);