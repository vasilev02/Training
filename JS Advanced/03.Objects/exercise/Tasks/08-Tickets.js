function solve(array, parameter) {

    class Request {

        constructor(destination, price, status) {

            this.destination = destination;

            this.price = Number(price);

            this.status = status;

        }

    }

    let allTickets = [];

    for (let index = 0; index < array.length; index++) {

        let current = array[index].split('|');

        let currentDestination = current[0];
        let currentPrice = Number(current[1]);
        let currentStatus = current[2];

        allTickets.push(new Request(currentDestination, currentPrice, currentStatus));

    }

    if (parameter === 'destination') {

        return allTickets.sort((a, b) => {

            return a.destination.localeCompare(b.destination);

        });

    }
    if (parameter === 'status') {

        return allTickets.sort((a, b) => {

            return a.status.localeCompare(b.status);

        });

    }
    if (parameter === 'price') {

        return allTickets.sort((a, b) => {

            return a.price - b.price;

        });

    }

}

console.log(solve(['Philadelphia|94.20|available',
'New York City|95.99|available',
'New York City|95.99|sold',
'Boston|126.20|departed'],
'status'
));