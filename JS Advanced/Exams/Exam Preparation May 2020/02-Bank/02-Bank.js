class Bank {

    constructor(bankName) {

        this._bankName = bankName;
        this.allCustomers = [];

    }

    newCustomer(customer) {

        this.allCustomers.find(c => {

            if (c.personalId === customer.personalId) {

                throw new Error(`${customer.firstName} ${customer.lastName} is already our customer!`);

            }



        });

        //customer.transactionInfos = [];

        //customer.totalMoney = 0;

        this.allCustomers.push(customer);

        return customer;

    }

    depositMoney(personalId, amount) {

        let person = this.allCustomers.find(c => {

            return c.personalId === personalId;

        });

        if (person === undefined) {

            throw new Error('We have no customer with this ID!');

        } else {

            if (isNaN(person.totalMoney)) {

                person.totalMoney = amount;

            } else {

                person.totalMoney += amount;

            }

            if (!Array.isArray(person.transactionInfos)) {

                person.transactionInfos = [];

            }


            let message = `${person.transactionInfos.length + 1}. ${person.firstName} ${person.lastName} made deposit of ${amount}$!`;

            person.transactionInfos.push(message);

            return `${person.totalMoney}$`;

        }

    }

    withdrawMoney(personalId, amount) {

        let person = this.allCustomers.find(c => {

            return c.personalId === personalId;

        });

        if (person === undefined) {

            throw new Error('We have no customer with this ID!');

        } else {

            if (isNaN(person.totalMoney)) {

                person.totalMoney = amount;

            }

            if (!Array.isArray(person.transactionInfos)) {

                person.transactionInfos = [];

            }

            if (person.totalMoney < amount) {

                throw new Error(`${person.firstName} ${person.lastName} does not have enough money to withdraw that amount!`);

            } else {

                person.totalMoney -= amount;

                let message = `${person.transactionInfos.length + 1}. ${person.firstName} ${person.lastName} withdrew ${amount}$!`

                person.transactionInfos.push(message);

                return `${person.totalMoney}$`;

            }

        }

    }

    customerInfo(personalId) {

        let person = this.allCustomers.find(c => {

            return c.personalId === personalId;

        });

        if (person === undefined) {

            throw new Error('We have no customer with this ID!');

        } else {

            let infoString = '';

            infoString += `Bank name: ${this._bankName}\n`;

            infoString += `Customer name: ${person.firstName} ${person.lastName}\n`;

            infoString += `Customer ID: ${person.personalId}\n`;

            infoString += `Total Money: ${person.totalMoney}$\n`;

            infoString += `Transactions:\n`;

            for (let index = person.transactionInfos.length - 1; index >= 0; index--) {

                infoString += person.transactionInfos[index];

                infoString += `\n`;

            }

            return infoString.trim();


        }

    }


}

let bank = new Bank('SoftUni Bank');

console.log(bank.newCustomer({ firstName: 'Svetlin', lastName: 'Nakov', personalId: 6233267 }));
console.log(bank.newCustomer({ firstName: 'Mihael', lastName: 'Mileva', personalId: 4151596 }));

bank.depositMoney(6233267, 250);
console.log(bank.depositMoney(6233267, 250));
bank.depositMoney(4151596, 555);

console.log(bank.withdrawMoney(6233267, 125));

console.log(bank.customerInfo(6233267));

