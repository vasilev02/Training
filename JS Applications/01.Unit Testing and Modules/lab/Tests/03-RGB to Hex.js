function rgbToHexColor(red, green, blue) {

    if (!Number.isInteger(red) || (red < 0) || (red > 255)) {

        return undefined; // Red value is invalid

    }

    if (!Number.isInteger(green) || (green < 0) || (green > 255)) {

        return undefined; // Green value is invalid

    }

    if (!Number.isInteger(blue) || (blue < 0) || (blue > 255)) {

        return undefined; // Blue value is invalid

    }

    return "#" +

        ("0" + red.toString(16).toUpperCase()).slice(-2) +

        ("0" + green.toString(16).toUpperCase()).slice(-2) +

        ("0" + blue.toString(16).toUpperCase()).slice(-2);

}

let expect = require('chai').expect;

describe('colors', function () {

    describe('invalid red', function () {

        it('should be undifined - string', function () {

            let result = rgbToHexColor('abc', 5, 5);

            expect(result).to.be.undefined;

        });

        it('should be undifined - below zero', function () {

            let result = rgbToHexColor(-1, 5, 5);

            expect(result).to.be.undefined;

        });

        it('should be undifined - above 255', function () {

            let result = rgbToHexColor(256, 5, 5);

            expect(result).to.be.undefined;

        });

    });

    describe('invalid green', function () {

        it('should be undifined - string', function () {

            let result = rgbToHexColor(5, 'abc', 5);

            expect(result).to.be.undefined;

        });

        it('should be undifined - below zero', function () {

            let result = rgbToHexColor(5, -1, 5);

            expect(result).to.be.undefined;

        });

        it('should be undifined - above 255', function () {

            let result = rgbToHexColor(5, 256, 5);

            expect(result).to.be.undefined;

        });

    });

    describe('invalid blue', function () {

        it('should be undifined - string', function () {

            let result = rgbToHexColor(5, 5, 'abc');

            expect(result).to.be.undefined;

        });

        it('should be undifined - below zero', function () {

            let result = rgbToHexColor(5, 5, -1);

            expect(result).to.be.undefined;

        });

        it('should be undifined - above 255', function () {

            let result = rgbToHexColor(5, 5, 256);

            expect(result).to.be.undefined;

        });

    });

    describe('correct output', function () {

        it('should match the given color', function () {

            let result = rgbToHexColor(209, 142, 128);

            expect(result).to.be.equal('#D18E80');

        });

    });


});
