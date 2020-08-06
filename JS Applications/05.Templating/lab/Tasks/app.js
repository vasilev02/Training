import { contacts } from './contacts.js';

window.addEventListener('load', async function () {

    let template = await (await fetch('./main.hbs')).text();

    Handlebars.registerPartial('contact', await (await fetch('./current.hbs')).text());

    let createFunction = Handlebars.compile(template);

    let generated = createFunction({ contacts });

    let body = document.getElementsByTagName('body')[0];

    body.innerHTML = generated;

    body.addEventListener('click', function (e) {

        if (e.target.tagName === 'BUTTON') {

            let div = e.target.parentNode;

            let result = div.getElementsByTagName('div')[0]
    
            if (result.style.display === '') {
    
                result.style.display = 'block';

            } else {
    
                result.style.display = '';

    
            }
    
        }

    });

});