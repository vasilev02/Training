function solve(input) {

    let sizeOfHome = Number(input.shift());

    let priceWithoutDiscount = sizeOfHome * 7.61;

    let finalPrice = priceWithoutDiscount * 0.82;

    console.log(`The final price is: ${finalPrice.toFixed(2)} lv.`);
    console.log(`The discount is: ${(priceWithoutDiscount - finalPrice).toFixed(2)} lv.`);

}

solve([`540`]);