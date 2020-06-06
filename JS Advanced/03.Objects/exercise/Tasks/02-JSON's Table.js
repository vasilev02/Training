function solve(input) {

    let textToPrint = '<table>\n';

    for (const jsonText of input) {

        let person = JSON.parse(jsonText);

        textToPrint += '\t<tr>\n';

        textToPrint += `\t\t<td>${person['name']}</td>\n`;

        textToPrint += `\t\t<td>${person['position']}</td>\n`;

        textToPrint += `\t\t<td>${Number(person['salary'])}</td>\n`;


        textToPrint += '\t</tr>\n';

    }

    textToPrint += '</table>\n';

    console.log(textToPrint);


}

solve(['{"name":"Pesho","position":"Promenliva","salary":100000}',
    '{"name":"Teo","position":"Lecturer","salary":1000}',
    '{"name":"Georgi","position":"Lecturer","salary":1000}']
);