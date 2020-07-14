function isOddOrEven(string) {

    if (typeof (string) !== 'string') {

        return undefined;

    }

    if (string.length % 2 === 0) {

        return "even";

    }

    return "odd";
}

let expect = require('chai').expect;

describe('isOddOrEvenTests', function () {

    it('should return undifined for invalid input', function () {

        let result = isOddOrEven(1);

        expect(result).to.be.undefined;

    });

    it('should return even as result', function () {

        let result = isOddOrEven('string');

        expect(result).to.be.equal('even');

    });

    it('should return odd as result', function () {

        let result = isOddOrEven('hello');

        expect(result).to.be.equal('odd');

    });

});
