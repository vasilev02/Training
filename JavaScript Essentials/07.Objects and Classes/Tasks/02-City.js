function solve(cityName, cityArea, cityPopulation, country, cityPostCode) {

    let person = { name: cityName, area: cityArea, population: cityPopulation, country: country, postCode: cityPostCode };

    for (const key in person) {

        console.log(`${key} -> ${person[key]}`);

    }

}

solve("Sofia", " 492", "1238438", "Bulgaria", "1000");