let mathEnforcer = {

    addFive: function (num) {

        if (typeof (num) !== 'number') {

            return undefined;

        }

        return num + 5;

    },

    subtractTen: function (num) {

        if (typeof (num) !== 'number') {

            return undefined;

        }

        return num - 10;

    },

    sum: function (num1, num2) {

        if (typeof (num1) !== 'number' || typeof (num2) !== 'number') {

            return undefined;

        }

        return num1 + num2;

    }

};

let expect = require('chai').expect;

describe('mathEnforcer tests', function () {

    describe('addFive function tests', function () {

        it('should return undifined for invalid input', function () {

            let result = mathEnforcer.addFive('abc');

            expect(result).to.be.undefined;

        });

        it('should return number plus 5', function () {

            let result = mathEnforcer.addFive(5);

            expect(result).to.be.equal(10);

        });

    });

    describe('subtractTen function tests', function () {

        it('should return undifined for invalid input', function () {

            let result = mathEnforcer.subtractTen('abc');

            expect(result).to.be.undefined;

        });

        it('should return number minus 10', function () {

            let result = mathEnforcer.subtractTen(20);

            expect(result).to.be.equal(10);

        });

    });

    describe('sum function tests', function () {

        it('should return undifined for invalid firts parameter', function () {

            let result = mathEnforcer.sum('abc', 2);

            expect(result).to.be.undefined;

        });

        it('should return undifined for invalid second parameter', function () {

            let result = mathEnforcer.sum(2, 'abc');

            expect(result).to.be.undefined;

        });

        it('should return numberOne plus numberTwo', function () {

            let result = mathEnforcer.sum(2, 2);

            expect(result).to.be.equal(4);

        });

    });

});
