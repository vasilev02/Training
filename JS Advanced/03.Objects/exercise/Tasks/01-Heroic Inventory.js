function solve(input) {

    let heroData = [];

    for (let index = 0; index < input.length; index++) {

        let currentHeroArguments = input[index].split(' / ');

        let currentHeroName = currentHeroArguments[0];

        let currentHeroLevel = Number(currentHeroArguments[1]);

        let currentHeroItems = [];

        if (currentHeroArguments.length > 2) {

            currentHeroItems = currentHeroArguments[2].split(', ');

        }


        let hero = {

            name: currentHeroName,
            level: currentHeroLevel,
            items: currentHeroItems,

        };

        heroData.push(hero);

    }

    console.log(JSON.stringify(heroData));

}

solve(['Isacc / 25 / Apple, GravityGun',
    'Derek / 12 / BarrelVest, DestructionSword',
    'Hes / 1 / Desolator, Sentinel, Antara']
);