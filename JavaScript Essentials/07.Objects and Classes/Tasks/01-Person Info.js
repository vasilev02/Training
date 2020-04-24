function solve(firstName, lastName, personAge) {

    let person = { firstName: firstName, lastName: lastName, age: personAge };

    for (const key in person) {

        console.log(`${key}: ${person[key]}`);

    }

}

solve("Peter",
    "Pan",
    "20"
);