function solve(input) {

    class Cat {

        constructor(name, age) {

            this.name = name;

            this.age = age;

        }

        sayMellow() {
            console.log(`${this.name}, age ${this.age} says Meow`);
        }

    }

    let allCats = [];


    for (let index = 0; index < input.length; index++) {

        let [catName, catAge] = input[index].split(" ");

        let currentCat = new Cat(catName, catAge);

        allCats.push(currentCat);

    }

    for (let index = 0; index < allCats.length; index++) {

        let cat = allCats[index];

        cat.sayMellow();

    }


}

solve(['Mellow 2', 'Tom 5']);