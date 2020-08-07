
let button = document.getElementsByTagName('button')[0];

button.addEventListener('click', async function () {

    let inputFiled = document.getElementById('towns');

    let div = document.getElementById('root');

    let templatingString = await (await fetch('./unList.hbs')).text();

    Handlebars.registerPartial('town', await (await fetch('./list.hbs')).text());

    let tfunction = Handlebars.compile(templatingString);

    let towns = inputFiled.value.split(', ');

    let generated = tfunction({ towns });

    div.innerHTML = generated;

});