class Stringer {

    constructor(innerString, innerLength) {

        this.innerString = innerString;

        this.innerLength = Number(innerLength);

        this.constantNumber = innerLength;
    }

    increase(length) {
        this.innerLength += length;
    }

    decrease(length) {

        this.innerLength -= length;

        if (this.innerLength < 0) {

            this.innerLength = 0;

        }
    }

    toString() {

        if (this.innerLength < this.constantNumber - 1) {

            let stringToPrint = this.innerString.substring(0, this.innerLength);

            stringToPrint += '...';

            return stringToPrint;

        } else {

            return this.innerString.substring(0, this.innerLength);

        }


    }



}

let test = new Stringer("Test", 5);
console.log(test.toString()); // Test

test.decrease(3);
console.log(test.toString()); // Te...

test.decrease(5);
console.log(test.toString()); // ...

test.increase(4);
console.log(test.toString()); // Test
