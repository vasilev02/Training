function growingWord() {

  let text = document.querySelector('#exercise p');

  if (!text.hasAttribute('style')) {

    text.setAttribute('style', 'color : blue');
    text.style['font-size'] = '2px';

  } else {

    let pixels = Number(text.style['font-size'].charAt(0));
    let currentColor = text.style.color;

    if (currentColor === 'blue') {

      text.style.color = 'green'
      text.style['font-size'] = `${pixels*2}px`;

    } else if (currentColor === 'green') {

      text.style.color = 'red'
      text.style['font-size'] = `${pixels*2}px`;

    }else if (currentColor === 'red') {

      text.style.color = 'blue'
      text.style['font-size'] = `${pixels*2}px`;

    }

  }

}