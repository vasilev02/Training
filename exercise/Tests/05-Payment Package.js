class PaymentPackage {
    constructor(name, value) {
        this.name = name;
        this.value = value;
        this.VAT = 20;      // Default value    
        this.active = true; // Default value
    }

    get name() {
        return this._name;
    }
    set name(newValue) {
        if (typeof newValue !== 'string') {
            throw new Error('Name must be a non-empty string');
        }
        if (newValue.length === 0) {
            throw new Error('Name must be a non-empty string');
        }
        this._name = newValue;
    }

    get value() {
        return this._value;
    }

    set value(newValue) {
        if (typeof newValue !== 'number') {
            throw new Error('Value must be a non-negative number');
        }
        if (newValue < 0) {
            throw new Error('Value must be a non-negative number');
        }
        this._value = newValue;
    }

    get VAT() {
        return this._VAT;
    }

    set VAT(newValue) {
        if (typeof newValue !== 'number') {
            throw new Error('VAT must be a non-negative number');
        }
        if (newValue < 0) {
            throw new Error('VAT must be a non-negative number');
        }
        this._VAT = newValue;
    }

    get active() {
        return this._active;
    }

    set active(newValue) {
        if (typeof newValue !== 'boolean') {
            throw new Error('Active status must be a boolean');
        }
        this._active = newValue;
    }

    toString() {
        const output = [
            `Package: ${this.name}` + (this.active === false ? ' (inactive)' : ''),
            `- Value (excl. VAT): ${this.value}`,
            `- Value (VAT ${this.VAT}%): ${this.value * (1 + this.VAT / 100)}`
        ];
        return output.join('\n');
    }
}

// Should throw an error
try {
    const hrPack = new PaymentPackage('HR Services');
} catch (err) {
    console.log('Error: ' + err.message);
}
const packages = [
    new PaymentPackage('HR Services', 1500),
    new PaymentPackage('Consultation', 800),
    new PaymentPackage('Partnership Fee', 7000),
];
console.log(packages.join('\n'));

const wrongPack = new PaymentPackage('Transfer Fee', 100);
// Should throw an error
try {
    wrongPack.active = null;
} catch (err) {
    console.log('Error: ' + err.message);
}


//Tests - - -

let expect = require('chai').expect;


describe('PaymentPackage tests', function () {

    describe('constructor tests', function () {

        it('should make initialization correctly', function () {

            expect(() => new PaymentPackage('Name', 5)).to.not.throw();

        });

        it('should throw error different from string first parameter', function () {

            expect(() => new PaymentPackage(1, 5)).to.throw();

        });

        it('should throw error empty (length = 0) first parameter', function () {

            expect(() => new PaymentPackage('', 5).to.throw());

        });

        it('should throw error negative second parameter', function () {

            expect(() => new PaymentPackage('Name', -1).to.throw());

        });

        it('should throw error different second parameter', function () {

            expect(() => new PaymentPackage('Name', []).to.throw());

        });

    });

    describe('get properties tests', function () {

        it('should get name correctly', function () {

            let payment = new PaymentPackage('Name',5);

            expect(payment.name).to.be.equal('Name');

        });

        it('should get value correctly', function () {

            let payment = new PaymentPackage('Name',5);

            expect(payment.value).to.be.equal(5);

        });

        it('should get VAT correctly', function () {

            let payment = new PaymentPackage('Name',5);

            expect(payment.VAT).to.be.equal(20);

        });

        it('should get active correctly', function () {

            let payment = new PaymentPackage('Name',5);

            expect(payment.active).to.be.true;

        });

    });

    describe('set properties tests', function () {

        it('should get name correctly', function () {

            let payment = new PaymentPackage('Name',5);

            payment.name = 'Hello';

            expect(payment.name).to.be.equal('Hello');

        });

        it('should get value correctly', function () {

            let payment = new PaymentPackage('Name',5);

            payment.value = 3;

            expect(payment.value).to.be.equal(3);

        });

        it('should get VAT correctly', function () {

            let payment = new PaymentPackage('Name',5);

            payment.VAT = 1;

            expect(payment.VAT).to.be.equal(1);

        });

        it('should get active correctly', function () {

            let payment = new PaymentPackage('Name',5);

            payment.active = false;

            expect(payment.active).to.be.false;

        });

    });

});