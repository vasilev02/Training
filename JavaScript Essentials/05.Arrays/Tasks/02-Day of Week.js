function solve(data) {

    let days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

    let number = data[0];

    number -= 1;

    if (number >= 0 && number < days.length) {

        console.log(days[number]);

    } else {

        console.log("Invalid day!");

    }

}

solve([3]);