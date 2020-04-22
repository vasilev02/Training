function main(input) {

    let text = input[0];
    let count = Number(input[1]);

    result = repeat(text,count);

    console.log(result);

}

function repeat(text, counter) {

    let textToReturn = "";

    textToReturn = text.repeat(counter);

    return textToReturn;
}

repeat([`abc`, `2`]);
