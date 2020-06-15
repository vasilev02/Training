function solve() {

  let tokens = document.querySelectorAll('.link-1');

  for (let index = 0; index < tokens.length; index++) {

    tokens[index].addEventListener('click', incrementView);

  }

  function incrementView(e) {

    let info = e.currentTarget;

    let paragraph = info.getElementsByTagName('p')[0];

    let data = paragraph.textContent.split(' ');

    data[1] = (Number(data[1]) + 1);

    paragraph.textContent = data.join(' ');

  }


}