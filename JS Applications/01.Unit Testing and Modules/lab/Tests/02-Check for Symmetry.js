function isSymmetric(arr) {

    if (!Array.isArray(arr)) {
        return false; // Non-arrays are non-symmetric
    }

    let reversed = arr.slice(0).reverse(); // Clone and reverse

    let equal = (JSON.stringify(arr) == JSON.stringify(reversed));

    return equal;
}

let expect = require('chai').expect;

describe('test symmetric', function () {

    it('should check and return true', function () {

        let result = isSymmetric([1, 3, 1]);

        expect(result).to.be.true;

    });

    it('should check and return false for invalid input', function () {

        let result = isSymmetric('string');

        expect(result).to.be.false;

    });

    it('should check and return false', function () {

        let result = isSymmetric([1, 2, 3]);

        expect(result).to.be.false;

    });
});

