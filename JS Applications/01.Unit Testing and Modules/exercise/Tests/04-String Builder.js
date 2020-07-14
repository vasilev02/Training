class StringBuilder {

    constructor(string) {

        if (string !== undefined) {

            StringBuilder._vrfyParam(string);

            this._stringArray = Array.from(string);

        } else {

            this._stringArray = [];

        }
    }

    append(string) {

        StringBuilder._vrfyParam(string);

        for (let i = 0; i < string.length; i++) {

            this._stringArray.push(string[i]);

        }

    }

    prepend(string) {

        StringBuilder._vrfyParam(string);

        for (let i = string.length - 1; i >= 0; i--) {

            this._stringArray.unshift(string[i]);

        }
    }

    insertAt(string, startIndex) {

        StringBuilder._vrfyParam(string);

        this._stringArray.splice(startIndex, 0, ...string);

    }

    remove(startIndex, length) {

        this._stringArray.splice(startIndex, length);

    }

    static _vrfyParam(param) {

        if (typeof param !== 'string') throw new TypeError('Argument must be string');

    }

    toString() {

        return this._stringArray.join('');

    }
}

//-------------------------------------

let str = new StringBuilder('hello');
str.append(', there');
str.prepend('User, ');
str.insertAt('woop',5 );
console.log(str.toString());
str.remove(6, 3);
console.log(str.toString());

//--------------------------------------

//Tests - - -

let expect = require('chai').expect;

describe('StringBuilder tests', function () {

    describe('constructor tests', function () {

        it('should make initialization correct with string', function () {

            expect(() => new StringBuilder('string')).to.not.throw();

        });

        it('should make initialization correct without string', function () {

            expect(() => new StringBuilder()).to.not.throw();

        });

    });

    describe('append function tests', function () {

        it('should append correctly', function () {

            let builder = new StringBuilder('string');

            builder.append('str');

            let result = builder.toString();

            expect(result).to.be.equal('stringstr');

        });

        it('append function should throw typeError', function () {

            let builder = new StringBuilder("string");

            expect(() => builder.append(1).to.throw());

        });

    });

    describe('prepend function tests', function () {

        it('should prepend correctly', function () {

            let builder = new StringBuilder('string');

            builder.prepend('str');

            let result = builder.toString();

            expect(result).to.be.equal('strstring');

        });

        it('prepend function should throw typeError', function () {

            let builder = new StringBuilder("string");

            expect(() => builder.prepend(1)).to.throw();

        });

    });

    describe('insert functionality', function () {

        it('should insert correctly', function () {

            let builder = new StringBuilder('string');

            builder.insertAt('str', 1);

            let result = builder.toString();

            expect(result).to.be.equal('sstrtring');

        });

        it('insert function should throw typeError', function () {

            let builder = new StringBuilder("string");

            expect(() => builder.append(1).to.throw);

        });

    });

    describe('remove functionality', function () {

        it('should remove correctly', function () {

            let builder = new StringBuilder('string');

            builder.remove(1, 2);

            let result = builder.toString();

            expect(result).to.be.equal('sing');

        });

    });

});