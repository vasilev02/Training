import { monkeys } from './monkeys.js';

window.addEventListener('load', async function () {


    let template = await (await fetch('./main.hbs')).text();

    Handlebars.registerPartial('monkey', await (await fetch('./current.hbs')).text());

    let createFunction = Handlebars.compile(template);

    let generated = createFunction({ monkeys });

    document.getElementsByTagName('section')[0].innerHTML = generated;

    document.querySelector('.monkeys').addEventListener('click',function(e){

        if(e.target.tagName === 'BUTTON'){

            let div = e.target.parentNode;

            let result = div.getElementsByTagName('p')[0];

            result.style.display = 'block';

        }

    });


});


