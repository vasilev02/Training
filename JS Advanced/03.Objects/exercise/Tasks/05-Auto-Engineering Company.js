function solve(arr) {

    let brands = new Map();

    for (let line of arr) {

        let tokens = line.split(" | ").filter(w => w !== "");

        let brand = tokens[0];

        let model = tokens[1];

        let quantity = Number(tokens[2]);
 
        if (brands.has(brand)) {

            if (brands.get(brand).has(model)) {

                brands.get(brand).set(model, brands.get(brand).get(model) + quantity);

            } else {

                brands.get(brand).set(model, quantity);

            }
        } else {

            let modelsAndTotalSold = new Map();

            modelsAndTotalSold.set(model, quantity);

            brands.set(brand, modelsAndTotalSold);

        }
    }

    for (let [brandForCurrentCar, modelForCurrentCar] of brands) {

        console.log(`${brandForCurrentCar}`);

        for (let [model, totalSold] of modelForCurrentCar) {

            console.log(`###${model} -> ${totalSold}`);

        }
    }
}

solve(["Audi | Q7 | 1000",
"Audi | Q6 | 100",
"BMW | X5 | 1000",
"BMW | X6 | 100",
"Citroen | C4 | 123",
"Volga | GAZ - 24 | 1000000",
"Lada | Niva | 1000000",
"Lada | Jigula | 1000000",
"Citroen | C4 | 22",
"Citroen | C5 | 10"])