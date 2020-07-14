class Warehouse {

    get capacity() {
        return this._capacity;
    }

    set capacity(givenSpace) {

        if (typeof givenSpace === 'number' && givenSpace > 0) {
            return this._capacity = givenSpace;
        } else {
            throw `Invalid given warehouse space`;
        }
    }

    constructor(capacity) {
        this.capacity = capacity;
        this.availableProducts = { 'Food': {}, 'Drink': {} };
    }

    addProduct(type, product, quantity) {
        let addedQuantity = ((this.capacity - this.occupiedCapacity()) - quantity);
        let output;

        if (addedQuantity >= 0) {

            if (this.availableProducts[type].hasOwnProperty(product) === false) {
                this.availableProducts[type][product] = 0;
            }

            this.availableProducts[type][product] += quantity;
            output = this.availableProducts[type];

        } else {
            throw `There is not enough space or the warehouse is already full`;
        }

        return output;
    }

    orderProducts(type) {

        let output;
        let sortedKeys = Object.keys(this.availableProducts[type])
            .sort((a, b) => this.availableProducts[type][b] - this.availableProducts[type][a]);

        let newObj = {};

        for (let product of sortedKeys) {

            if (newObj.hasOwnProperty(product) === false) {
                newObj[product] = 0;
            }

            newObj[product] += this.availableProducts[type][product];
        }

        this.availableProducts[type] = newObj;
        output = this.availableProducts[type];

        return output;
    }

    occupiedCapacity() {

        let output = 0;
        let productsCount = Object.keys(this.availableProducts['Food']).length +
            Object.keys(this.availableProducts['Drink']).length;
        if (productsCount > 0) {

            let quantityInStock = 0;

            for (let type of Object.keys(this.availableProducts)) {

                for (let product of Object.keys(this.availableProducts[type])) {

                    quantityInStock += this.availableProducts[type][product];
                }
            }

            output = quantityInStock;
        }

        return output;
    }

    revision() {

        let output = "";

        if (this.occupiedCapacity() > 0) {

            for (let type of Object.keys(this.availableProducts)) {
                output += `Product type - [${type}]\n`;
                for (let product of Object.keys(this.availableProducts[type])) {
                    output += `- ${product} ${this.availableProducts[type][product]}\n`;
                }
            }
        } else {
            output = 'The warehouse is empty';
        }

        return output.trim();
    }

    scrapeAProduct(product, quantity) {

        let type = Object.keys(this.availableProducts).find(t => Object.keys(this.availableProducts[t]).includes(product));
        let output;

        if (type !== undefined) {

            if (quantity <= this.availableProducts[type][product]) {
                this.availableProducts[type][product] -= quantity;
            } else {
                this.availableProducts[type][product] = 0;
            }

            output = this.availableProducts[type];

        } else {
            throw `${product} do not exists`;
        }

        return output;
    }
}

let expect = require('chai').expect;

//Tests - - - - - - - - - -

describe('PaymentPackage', function () {
    function getInstance() {
        return new Warehouse(100);
    }
    it("Test if class properties exists", function () {
        let inst = getInstance();
        expect(Object.getPrototypeOf(inst).hasOwnProperty('capacity')).to.be.equal(true);
        expect(Object.getPrototypeOf(inst).hasOwnProperty('constructor')).to.be.equal(true);
        expect(typeof Warehouse).to.be.equal('function');
    });
    it('capacity to throw with negative num', function () {
        expect(() => {
            let inst = new Warehouse(-100);
        }).to.throw();
    });
    it('capacity to throw with zero', function () {
        expect(() => {
            let inst = new Warehouse(0);
        }).to.throw();
    });
    it("should have a getter property 'capacity'", function () {
        const paymentPackage = getInstance()
        expect(paymentPackage.capacity).to.equal(100)
    });

    it("should have a setter property 'capacity'", function () {
        const paymentPackage = getInstance();
        paymentPackage.capacity = 10;
        expect(paymentPackage.capacity).to.equal(10);
    });
    describe('warehouse add products tests', function () {
        it('add product should add product and return it\'s name', function () {
            let warehouse = getInstance();
            let product = 'rice';
            let type = 'Food';
            let quantity = 5;
            let productObject = {
                'rice': quantity
            }
            let result = warehouse.addProduct(type, product, quantity);
            expect(productObject).to.deep.equal(result);
        });
        it('add product should take right amount of capacity', function () {
            let capacity = 10;
            let warehouse = new Warehouse(capacity);
            let product = 'rice';
            let type = 'Food';
            let quantity = 5;

            warehouse.addProduct(type, product, quantity);
            let takenSpace = warehouse.occupiedCapacity();
            let spaceThatShouldBeTaken = capacity - quantity;
            expect(takenSpace).to.equal(spaceThatShouldBeTaken);
        });

        it('add product should add product and increase its quantity if exists', function () {
            let warehouse = getInstance();
            let product = 'rice';
            let type = 'Food';
            let quantity = 5;

            warehouse.addProduct(type, product, quantity);
            warehouse.addProduct(type, product, quantity);
            let riceQuantity = warehouse.availableProducts.Food[product];
            let expectedQuantity = quantity * 2;
            expect(riceQuantity).to.equal(expectedQuantity);
        });

        it('add product should throw string if not enough capacity', function () {

            expect(function () {
                let warehouse = new Warehouse(5);
                let product = 'rice';
                let type = 'Food';
                let quantity = 10;

                warehouse.addProduct(type, product, quantity);
            }).to.throw('There is not enough space or the warehouse is already full');
        });
    })
    describe('warehouse sort products test', function () {

        it('sort products should sort products of the type descending', function () {
            let warehouse = getInstance();
            let productOne = 'rice';
            let productType = 'Food';
            let productOneQuantity = 10;
            let productTwo = 'bread';
            let productTwoQuantity = 5;
            warehouse.addProduct(productType, productOne, productOneQuantity);
            warehouse.addProduct(productType, productTwo, productTwoQuantity);
            let result = warehouse.orderProducts(productType);
            let expectedOrder = { 'rice': 10, 'bread': 5 };
            expect(result).to.deep.equal(expectedOrder);
        });
    });
    describe('warehouse revision tests', function () {

        it('revision should return empty warehouse message', function () {
            let warehouse = getInstance();
            let revision = warehouse.revision();
            let expectedOutput = 'The warehouse is empty';
            expect(revision).to.equal(expectedOutput);
        });

        it('revision should return  ordered list', function () {
            let warehouse = getInstance();
            let productOne = 'rice';
            let productType = 'Food';
            let productTypeTwo = 'Drink';
            let productOneQuantity = 10;
            let productTwo = 'bread';
            let productTwoQuantity = 5;
            warehouse.addProduct(productType, productOne, productOneQuantity);
            warehouse.addProduct(productType, productTwo, productTwoQuantity);
            let revision = warehouse.revision();
            let expectedOutput = `Product type - [${productType}]\n` +
                `- ${productOne} ${productOneQuantity}\n` +
                `- ${productTwo} ${productTwoQuantity}\n` +
                `Product type - [${productTypeTwo}]\n`;
            let expected = expectedOutput.trim();
            expect(revision).to.equal(expected);
        });
    });

    describe('warehouse scrape a product tests', function () {

        it('it should return nonexisting product', function () {
            const noneExistingType = "wine";
            let expectedResult = `${noneExistingType} do not exists`;
            expect(function () {
                let warehouse = getInstance();
                let productOne = 'rice';
                let productType = 'Food';
                let productOneQuantity = 10;
                let productTwo = 'bread';
                let productTwoQuantity = 5;
                warehouse.addProduct(productType, productOne, productOneQuantity);
                warehouse.addProduct(productType, productTwo, productTwoQuantity);
                warehouse.scrapeAProduct(noneExistingType, 5);
            }).to.throw(expectedResult)

        });

        it('it should reset amount of the product with not enough quantity', function () {
            let warehouse = getInstance();
            let product = 'rice';
            let productType = 'Food';
            let productQuantity = 5;
            let productWantedQuantity = 10;
            warehouse.addProduct(productType, product, productQuantity);
            warehouse.scrapeAProduct(product, productWantedQuantity);
            let currentCountProduct = warehouse.availableProducts.Food[product];
            const expectedCount = 0;
            expect(currentCountProduct).to.equal(expectedCount);
        });

        it('it should return correct quantity of the product', function () {
            let warehouse = getInstance();
            let product = 'rice';
            let productType = 'Food';
            let productQuantity = 10;
            let productWantedQuantity = 5;
            warehouse.addProduct(productType, product, productQuantity);
            warehouse.scrapeAProduct(product, productWantedQuantity);
            let currentCountProduct = warehouse.availableProducts.Food[product];
            const expectedCount = 5;
            expect(currentCountProduct).to.equal(expectedCount);
        });
    });
});