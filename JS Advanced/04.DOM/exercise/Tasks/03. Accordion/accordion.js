function toggle() {

    let text = document.querySelector('#extra');

    let button = document.querySelector('.button');

    let position = text.style.display;

    if (position === 'none') {

        button.textContent = 'Less';

        text.style.display = 'block';

    } else {

        button.textContent = 'More';

        text.style.display = 'none';

    }

}