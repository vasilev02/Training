function main(input) {

    let grade = Number(input[0]);

    printGrade(grade);

}

function printGrade(grade) {

    if (grade >= 2.00 && grade < 3.00) {
        console.log("Fail");
    } else if (grade >= 3.00 && grade < 3.50) {
        console.log("Poor");
    } else if (grade >= 3.50 && grade < 4.50) {
        console.log("Good");
    } else if (grade >= 4.50 && grade < 5.50) {
        console.log("Very good");
    } else if (grade >= 5.50 && grade <= 6.00) {
        console.log("Excellent");
    }

}

printGrade([3.33]);
