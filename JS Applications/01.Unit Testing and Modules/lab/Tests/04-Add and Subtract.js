function createCalculator() {

    let value = 0;

    return {
        add: function (num) { value += Number(num); },
        subtract: function (num) { value -= Number(num); },
        get: function () { return value; }
    }

}

let expect = require('chai').expect;

describe('createCalculator tests', function () {

    it('shold have certain properties', function () {

        let result = createCalculator();

        expect(result).to.haveOwnProperty('add');
        expect(result).to.haveOwnProperty('subtract');
        expect(result).to.haveOwnProperty('get');

    });

    it('shold add correctly number as string', function () {

        let currentState = createCalculator();

        currentState.add('2');

        let result = currentState.get();

        expect(result).to.be.equal(2);


    });

    it('shold add correctly number as integer', function () {

        let currentState = createCalculator();

        currentState.add(2);

        let result = currentState.get();

        expect(result).to.be.equal(2);


    });

    it('shold subtract correctly number', function () {

        let currentState = createCalculator();

        currentState.subtract(2);

        let result = currentState.get();

        expect(result).to.be.equal(-2);


    });

});
