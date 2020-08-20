function solveClasses() {


    class Hall {

        constructor(capacity, name) {

            this.capacity = capacity;
            this.name = name;
            this.events = [];

        }

        hallEvent(title) {

            if (this.events.includes(title)) {
                throw new Error("This event is already added!");
            }

            this.events.push(title);

            return "Event is added.";

        }

        close() {

            this.events = [];

            return `${this.name} hall is closed.`

        }

        toString() {

            let string = `${this.name} hall - ${this.capacity}\n`;

            if (this.events.length > 0) {

                string += `Events: ${this.events.join(', ')}\n`;

            }

            return string.trim();

        }

    }

    class MovieTheater extends Hall {

        constructor(capacity, name, screenSize) {

            super(capacity, name);
            this.screenSize = screenSize;

        }


        close() {

            let result = super.close();

            result += "Аll screenings are over.";

            return result;

        }

        toString() {

            let string = super.toString() + '\n';

            string += `${this.name} is a movie theater with ${this.screenSize} screensize and ${this.capacity} seats capacity.\n`;

            return string.trim();
        }

    }

    class ConcertHall extends Hall {

        constructor(capacity, name) {

            super(capacity, name);
            this.performers = [];

        }

        hallEvent(title, performers) {

            let result = super.hallEvent(title);

            for (let index = 0; index < performers.length; index++) {

                let curr = performers[index];
                this.performers.push(curr);

            }

            return result;

        }

        close() {

            let result = super.close();
            this.performers = [];
            result += "Аll performances are over.";
            return result;

        }

        toString() {

            let string = super.toString() + '\n';

            if (this.performers.length > 0) {
                string += `Performers: ${this.performers.join(', ')}.`
            }

            return string.trim();

        }

    }

    return {

        Hall,
        MovieTheater,
        ConcertHall

    }

}

let classes = solveClasses();
let hall = new classes.Hall(20, 'Main');
console.log(hall.hallEvent('Breakfast Ideas'));
console.log(hall.hallEvent('Annual Charity Ball'));
console.log(hall.toString());
console.log(hall.close());

console.log('--------------------------------------------------------------------------------------');

let movieHall = new classes.MovieTheater(10, 'Europe', '10m');
console.log(movieHall.hallEvent('Top Gun: Maverick'));
console.log(movieHall.toString());
console.log('--------------------------------------------------------------------------------------');

let concert = new classes.ConcertHall(5000, 'Diamond');
console.log(concert.hallEvent('The Chromatica Ball', ['LADY GAGA']));
console.log(concert.toString());
console.log(concert.close());
console.log(concert.toString());
