function solve(personName, personLastName, personHairColor) {

    let person = { name: personName, lastName: personLastName, hairColor: personHairColor };

    let result = JSON.stringify(person);

    console.log(result);

}

solve('George',
    'Jones',
    'Brown'
);