
window.addEventListener('load', async function () {

    let template = await (await fetch('./main.hbs')).text();

    let section = document.getElementById('allCats');

    Handlebars.registerPartial('cat', await (await fetch('./current.hbs')).text());

    let createFunction = Handlebars.compile(template);

    let generated = createFunction({ cats });

    section.innerHTML = generated;

});

window.addEventListener('click', function (e) {

    if (e.target.tagName === 'BUTTON') {

        let div = e.target.parentNode.parentNode;

        let result = div.querySelector('.status');

        if (result.style.display === 'none') {

            result.style.display = 'block';

            e.target.textContent = "Hide status code";

        } else {

            result.style.display = 'none';

            e.target.textContent = "Show status code";

        }

    }

});

