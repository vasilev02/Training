class Console {

    static get placeholder() {
        return /{\d+}/g;
    }

    static writeLine() {
        let message = arguments[0];
        if (arguments.length === 1) {
            if (typeof (message) === 'object') {
                message = JSON.stringify(message);
                return message;
            }
            else if (typeof (message) === 'string') {
                return message;
            }
        }
        else {
            if (typeof (message) !== 'string') {
                throw new TypeError("No string format given!");
            }
            else {
                let tokens = message.match(this.placeholder).sort(function (a, b) {
                    a = Number(a.substring(1, a.length - 1));
                    b = Number(b.substring(1, b.length - 1));
                    return a - b;
                });
                if (tokens.length !== (arguments.length - 1)) {
                    throw new RangeError("Incorrect amount of parameters given!");
                }
                else {
                    for (let i = 0; i < tokens.length; i++) {
                        let number = Number(tokens[i].substring(1, tokens[i].length - 1));
                        if (number !== i) {
                            throw new RangeError("Incorrect placeholders given!");
                        }
                        else {
                            message = message.replace(tokens[i], arguments[i + 1])
                        }
                    }
                    return message;
                }
            }
        }
    }
};

let expect = require('chai').expect;

//Tests - - - - - - -

describe("Console.writeLine(string)", function () {

    it("Console.writeLine(string) -> string", function () {

        let string = "Bla bla bla";

        expect(Console.writeLine(string)).to.equal(string);

    });
    it("Console.writeLine(Object) -> JSON.stringify(Object)", function () {

        let object = { property: "property" };

        expect(Console.writeLine(object)).to.equal(JSON.stringify(object))

    });

    it("Console.writeLine() -> TypeError", function () {

        expect(() => Console.writeLine()).to.throw(TypeError);

    });

    it("Console.writeLine(1, 2) -> TypeError", function () {

        expect(() => Console.writeLine(1, 2)).to.throw(TypeError)

    });

    it("Console.writeLine('{0}.', '1','2') -> RangeError", function () {

        let string = "{0}.";

        expect(() => Console.writeLine(string, '1', '2')).to.throw(RangeError);

    });

    it("Console.writeLine('{0}{0}.', '1','2') -> RangeError", function () {

        let string = "{0}{0}.";

        expect(() => Console.writeLine(string, '1', '2')).to.throw(RangeError);

    });

    it("Console.writeLine('{0}{1}.', '1','2') -> '12'", function () {

        let string = "{0}{1}.";

        expect(Console.writeLine(string, '1', '2')).to.equal("12.");

    });

    it("Console.writeLine('{0}{1}{2}', '1','2') -> RangeError", function () {

        let string = "{0}{1}{2}";

        expect(() => Console.writeLine(string, '1', '2')).to.throw(RangeError);

    });

    it("Console.writeLine('{3}', '1') -> RangeError", function () {

        expect(() => Console.writeLine("{3}", '1')).to.throw(RangeError);

    });
});