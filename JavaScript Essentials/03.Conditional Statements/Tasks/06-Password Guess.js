function solve(input) {

    let typedPasswword = input.shift();


    let secretPassword = "s3cr3t!P@ssw0rd";

    if (typedPasswword === secretPassword) {

        console.log("Welcome");

    } else {

        console.log("Wrong password!")

    }

}

solve([`s3cr3t!P@ssw0rd`]);