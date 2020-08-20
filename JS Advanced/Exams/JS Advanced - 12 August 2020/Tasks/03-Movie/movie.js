class Movie {

    constructor(movieName, ticketPrice) {

        this.movieName = movieName;
        this.ticketPrice = Number(ticketPrice);
        this.screenings = [];
        this.totalProfit = 0;
        this.ticketsCount = 0;

    }

    newScreening(date, hall, description) {

        let result = this.screenings.find(s => {

            if (s.date === date && s.hall === hall) {
                return s;
            }

        });

        if (result !== undefined) {
            throw new Error(`Sorry, ${hall} hall is not available on ${date}`);
        }

        let obj = {

            date: date,
            hall: hall,
            description: description

        }

        this.screenings.push(obj);

        return `New screening of ${this.movieName} is added.`;

    }

    endScreening(date, hall, soldTickets) {

        let result = undefined;
        let indexObj = -1;

        for (let index = 0; index < this.screenings.length; index++) {

            let s = this.screenings[index];

            if (s.date === date && s.hall === hall) {
                result = s;
                indexObj = index;
            }

        }

        if (result === undefined) {
            throw new Error(`Sorry, there is no such screening for ${this.movieName} movie.`);
        }

        this.totalProfit += this.ticketPrice * Number(soldTickets);
        this.ticketsCount += soldTickets;

        this.screenings[indexObj] = null;

        let newArr = [];

        for (let index = 0; index < this.screenings.length; index++) {

            let current = this.screenings[index];

            if (current !== null) {
                newArr.push(current);
            }

        }

        this.screenings = newArr;

        return `${this.movieName} movie screening on ${date} in ${hall} hall has ended. Screening profit: ${(this.ticketPrice * Number(soldTickets)).toFixed(0)}`;

    }

    toString() {

        let string = `${this.movieName} full information:\n`;

        string += `Total profit: ${this.totalProfit.toFixed(0)}$\n`;

        string += `Sold Tickets: ${this.ticketsCount}\n`;

        if (this.screenings.length > 0) {

            string += 'Remaining film screenings:\n';

            this.screenings.sort((a, b) => {

                return a.hall.localeCompare(b.hall);

            }).forEach(s => {

                string += `${s.hall} - ${s.date} - ${s.description}\n`;

            })

        } else {

            string += 'No more screenings!\n';

        }

        return string.trim();

    }

}

let m = new Movie('Wonder Woman 1984', '10.00');
console.log(m.newScreening('October 2, 2020', 'IMAX 3D', `3D`));
console.log(m.newScreening('October 3, 2020', 'Main', `regular`));
console.log(m.newScreening('October 4, 2020', 'IMAX 3D', `3D`));
console.log(m.endScreening('October 2, 2020', 'IMAX 3D', 150));
console.log(m.endScreening('October 3, 2020', 'Main', 78));
console.log(m.toString());

m.newScreening('October 4, 2020', '235', `regular`);
m.newScreening('October 5, 2020', 'Main', `regular`);
m.newScreening('October 3, 2020', '235', `regular`);
m.newScreening('October 4, 2020', 'Main', `regular`);
console.log(m.toString());
