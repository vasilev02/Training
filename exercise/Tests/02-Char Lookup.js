function lookupChar(string, index) {

    if (typeof (string) !== 'string' || !Number.isInteger(index)) {

        return undefined;

    }

    if (string.length <= index || index < 0) {

        return "Incorrect index";

    }

    return string.charAt(index);

}

let expect = require('chai').expect;

describe('lookupChar tests',function(){

    describe('invalid inputs returning undifined',function(){

        it('should return undifined for invalid first parameter',function(){

            let result = lookupChar(1,5);

            expect(result).to.be.undefined;

        });

        it('should return undifined for invalid second parameter',function(){

            let result = lookupChar('string','hello');

            expect(result).to.be.undefined;

        });

    });

    describe('invalid index returning Incorrect index',function(){

        it('should fail for bigger index',function(){

            let result = lookupChar('string',7);

            expect(result).to.be.equal('Incorrect index');

        });

        it('should fail for equal string length',function(){

            let result = lookupChar('string',6);

            expect(result).to.be.equal('Incorrect index');

        });

        it('should fail for negative number (index)',function(){

            let result = lookupChar('string',-1);

            expect(result).to.be.equal('Incorrect index');

        });

    });

    describe('valid index returning correct result',function(){

        it('should return the letter of given index',function(){

            let result = lookupChar('string',1);

            expect(result).to.be.equal('t');

        });

    });

});
