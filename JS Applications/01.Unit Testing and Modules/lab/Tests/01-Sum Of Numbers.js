function sum(arr) {
    let sum = 0;
    for (num of arr) {
        sum += Number(num);
    }
    return sum;
}

let expect = require("chai").expect;

describe('sum', function () {

    it('test with string, returns NaN', function () {

        let actual = sum('abc');

        expect(actual).to.be.NaN;

    });

    it('test with array of str, returns number', function () {

        let expected = sum(['1', '2']);

        expect(expected).to.equal(3, 'papameter in not array of numbers');

    });

    it('expect sum ([1,2.3] to be 6)', function () {

        let expected = 6;

        let actual = sum([1, 2, 3]);

        expect(actual).to.equal(expected);

    });

});
