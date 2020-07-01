class VeterinaryClinic {

    constructor(clinicName, capacity) {

        this.clinicName = clinicName;
        this.capacity = capacity;
        this.clients = [];
        this.totalProfit = 0;
        this.currentWorkLoad = 0;

    }

    newCustomer(ownerName, petName, kind, procedures) {

        if (this.capacity < this.clients.length + 1) {
            throw new Error('Sorry, we are not able to accept more patients!');
        }

        for (let index = 0; index < this.clients.length; index++) {

            let current = this.clients[index];

            if (current.petName === petName && current.ownerName === ownerName && current.procedures.length > 0) {
                throw new Error(`This pet is already registered under ${ownerName} name! ${petName} is on our lists, waiting for ${current.procedures.join(', ')}.`);
            }

        }

        let petObject = {

            petName,
            ownerName,
            kind,
            procedures

        }

        this.clients.push(petObject);
        this.currentWorkLoad++;

        return `Welcome ${petName}!`;

    }

    onLeaving(ownerName, petName) {

        let petClient = this.clients.find(p => {

            if (p.petName === petName) {
                return p;
            }

        });

        if (petClient === undefined) {
            throw new Error('Sorry, there is no such client!');
        }

        if (petClient.ownerName !== ownerName) {
            throw new Error(`Sorry, there are no procedures for ${petName}!`);
        }

        if (petClient.procedures.length === 0) {
            throw new Error(`Sorry, there are no procedures for ${petName}!`);
        }

        this.totalProfit += petClient.procedures.length * 500;

        petClient.procedures = [];
        this.currentWorkLoad--;

        return `Goodbye ${petName}. Stay safe!`;

    }

    toString() {

        let arrayOfPeople = [];

        let string = '';

        let result = (this.currentWorkLoad / this.capacity) * 100;

        result = Math.floor(result);

        string += `${this.clinicName} is ${result}% busy today!\n`;

        string += `Total profit: ${this.totalProfit.toFixed(2)}$\n`;



        this.clients.sort((a, b) => {

            return a.ownerName.localeCompare(b.ownerName);

        }).forEach(pet => {

            let ownName = pet.ownerName;

            if (arrayOfPeople.includes(ownName)) {
                return;
            }
            arrayOfPeople.push(ownName);

            string += `${ownName} with:\n`;

            let arr = [];

            for (let index = 0; index < this.clients.length; index++) {

                let currentPet = this.clients[index];

                if (currentPet.ownerName === ownName) {
                    arr.push(currentPet);
                }

            }
            arr.sort((a, b) => {

                return a.petName.localeCompare(b.petName);

            }).forEach(e => {

                string += `---${e.petName} - a ${e.kind.toLowerCase()} that needs: ${e.procedures.join(', ')}\n`;


            });



        });

        return string.trim();

    }

}

let clinic = new VeterinaryClinic('SoftCare', 10);
console.log(clinic.newCustomer('Jim Jones', 'Tom', 'Cat', ['A154B', '2C32B', '12CDB']));
console.log(clinic.newCustomer('Anna Morgan', 'Max', 'Dog', ['SK456', 'DFG45', 'KS456']));
console.log(clinic.newCustomer('Jim Jones', 'Tiny', 'Cat', ['A154B']));
console.log(clinic.onLeaving('Jim Jones', 'Tiny'));
console.log(clinic.toString());
clinic.newCustomer('Jim Jones', 'Sara', 'Dog', ['A154B']);
console.log(clinic.toString());
