function solve(data) {

    let object = JSON.parse(data);

    for (const key in object) {

        console.log(`${key}: ${object[key]}`);

    }

}

solve('{"name": "George", "age": 40, "town": "Sofia"}');