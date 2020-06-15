function solve() {

  let textInput = document.getElementById('input');

  let text = textInput.textContent;

  let section = document.getElementById('output');

  let sentences = text.split('. ');

  let counter = 0;

  let addText = '';

  for (let index = 0; index < sentences.length; index++) {

    if (counter === 3) {

      let createParagraph = document.createElement('p');

      createParagraph.textContent = addText;

      section.appendChild(createParagraph);

      addText = '';
      counter = 1;

      addText += sentences[index];

    } else {

      addText += sentences[index];
      counter++;

    }

  }

  if (counter !== 0) {

    let createParagraph = document.createElement('p');

    createParagraph.textContent = addText;

    section.appendChild(createParagraph);

  }

}