function solve(input) {

    let tripPrice = Number(input.shift());

    let puzzlesCount = Number(input.shift());

    let dollsCount = Number(input.shift());

    let bearsCount = Number(input.shift());

    let minionsCount = Number(input.shift());

    let trucksCount = Number(input.shift());

    let totalCount = puzzlesCount + dollsCount + bearsCount + minionsCount + trucksCount;

    let totalPrice = (puzzlesCount * 2.60) + (dollsCount * 3.00) + (bearsCount * 4.10)
        + (minionsCount * 8.20) + (trucksCount * 2.00);

    if (totalCount >= 50) {
        totalPrice = totalPrice * 0.75;
    }

    //For hire
    totalPrice = totalPrice * 0.90;

    if (totalPrice >= tripPrice) {

        console.log(`Yes! ${(totalPrice - tripPrice).toFixed(2)} lv left.`)

    } else {

        console.log(`Not enough money! ${(tripPrice - totalPrice).toFixed(2)} lv needed.`)

    }

}

solve([`40.8`, `20`, `25`, `30`, `50`, `10`]);