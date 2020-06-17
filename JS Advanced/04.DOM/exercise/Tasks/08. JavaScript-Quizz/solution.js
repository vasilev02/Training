function solve() {

  let correctAnswers = [];
  let count = 0;
  correctAnswers.push('onclick');
  correctAnswers.push('JSON.stringify()');
  correctAnswers.push('A programming API for HTML and XML documents');

  let resultArea = document.querySelector('li[class="results-inner"]');

  let questions = document.getElementsByTagName('section');

  let step = 0;

  for (let index = 0; index < questions.length; index++) {

    let current = questions[index];

    let kids = current.childNodes;

    kids[1].addEventListener('click', checkAnswer);
    kids[2].addEventListener('click', checkAnswer);

  }

  function checkAnswer(e) {

    let answ = e.target.innerText;

    if (correctAnswers.includes(answ)) {
      count++;
    }

    e.currentTarget.style.display = 'none';
    step++;

    if (step == 3) {

      if (count == 3) {
        resultArea.firstElementChild.textContent = 'You are recognized as top JavaScript fan!';
        resultArea.parentNode.setAttribute('style', 'display : block');
      } else {

        resultArea.firstElementChild.textContent = `You have ${count} right answers`;
        resultArea.parentNode.setAttribute('style', 'display : block');
      }

    } else {

      questions[step].setAttribute('style', 'display : block');

    }

  }

}
