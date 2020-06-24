class Library {

    constructor(libraryName) {

        this.libraryName = libraryName;

        this.subscribers = [];

        this.subscriptionTypes = {
            normal: libraryName.length,
            special: libraryName.length * 2,
            vip: Number.MAX_SAFE_INTEGER
        }
    }

    subscribe(name, type) {

        if (type !== 'normal' && type !== 'special' && type !== 'vip') {
            throw new Error(`The type ${type} is invalid`);
        }

        for (let index = 0; index < this.subscribers.length; index++) {

            let current = this.subscribers[index];

            if (current.name === name) {
                current.type = type;
                return current;
            }

        }

        let object = {

            name: name,
            type: type,
            books: []

        }

        this.subscribers.push(object);

        return object;

    }

    unsubscribe(name) {

        for (let index = 0; index < this.subscribers.length; index++) {

            let current = this.subscribers[index];

            if (current.name === name) {

                let newArray = [];

                for (let index = 0; index < this.subscribers.length; index++) {

                    if (this.subscribers[index].name !== name) {
                        newArray.push(this.subscribers[index]);
                    }

                }

                this.subscribers = newArray;
                return this.subscribers;

            }

        }

        throw new Error(`There is no such subscriber as ${name}`);

    }

    receiveBook(subscriberName, bookTitle, bookAuthor) {

        for (let index = 0; index < this.subscribers.length; index++) {

            let current = this.subscribers[index];

            if (current.name === subscriberName) {

                let bookObject = {
                    title: bookTitle,
                    author: bookAuthor
                }

                let typeOfSub = current.type;

                if (typeOfSub === 'normal') {

                    if (current.books.length < this.subscriptionTypes.normal) {
                        current.books.push(bookObject);
                    } else {
                        throw new Error(`"You have reached your subscription limit ${this.subscriptionTypes.normal}!"`);
                    }

                } else if (typeOfSub === 'special') {

                    if (current.books.length < this.subscriptionTypes.special) {
                        current.books.push(bookObject);
                    } else {
                        throw new Error(`"You have reached your subscription limit ${this.subscriptionTypes.special}!"`);
                    }

                } else if (typeOfSub === 'vip') {

                    if (current.books.length < this.subscriptionTypes.vip) {
                        current.books.push(bookObject);
                    } else {
                        throw new Error(`"You have reached your subscription limit ${this.subscriptionTypes.vip}!"`);
                    }

                }

                return current;

            }

        }

        throw new Error(`There is no such subscriber as ${subscriberName}`);

    }

    showInfo() {

        if (this.subscribers.length == 0) {
            return `${this.libraryName} has no information about any subscribers`;
        }

        let string = '';

        for (let index = 0; index < this.subscribers.length; index++) {

            let current = this.subscribers[index];

            string += `Subscriber: ${current.name}, Type: ${current.type}\n`;
            string += `Received books: `;

            for (let index = 0; index < current.books.length; index++) {

                let currentBook = current.books[index];

                if (index === current.books.length - 1) {

                    string += `${currentBook.title} by ${currentBook.author}\n`;

                } else {

                    string += `${currentBook.title} by ${currentBook.author}, `;

                }

            }

        }

        return string;

    }

}


let lib = new Library('Lib');

lib.subscribe('Peter', 'normal');
lib.subscribe('John', 'special');

lib.receiveBook('John', 'A Song of Ice and Fire', 'George R. R. Martin');
lib.receiveBook('Peter', 'Lord of the rings', 'J. R. R. Tolkien');
lib.receiveBook('John', 'Harry Potter', 'J. K. Rowling');

console.log(lib.showInfo());

