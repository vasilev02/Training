function solve() {

   let authorField = document.getElementById('creator');
   let titleField = document.getElementById('title');
   let categoryField = document.getElementById('category');
   let contentField = document.getElementById('content');

   let createButton = document.querySelector('button[class="btn create"]');

   createButton.addEventListener('click', addArticle);



   function addArticle(e) {

      e.preventDefault();

      let boxOfArticles = document.getElementsByTagName('section')[1];

      let newArticle = document.createElement('article');

      let createHeader = document.createElement('h1');
      createHeader.textContent = titleField.value;

      let createParagraph = document.createElement('p');
      createParagraph.textContent = 'Category:';

      let createStrong = document.createElement('strong');
      createStrong.textContent = categoryField.value;

      createParagraph.appendChild(createStrong);


      let createParagraphForAuthor = document.createElement('p');
      createParagraphForAuthor.textContent = 'Creator:';

      let createStrongForAuthor = document.createElement('strong');
      createStrongForAuthor.textContent = authorField.value;

      createParagraphForAuthor.appendChild(createStrongForAuthor);

      //--------
      let createParagraphForContent = document.createElement('p');
      createParagraphForContent.textContent = contentField.value;
      //--------

      let createDiv = document.createElement('div');

      createDiv.classList.add('buttons');

      let buttonOne = document.createElement('button');
      buttonOne.textContent = 'Delete';
      buttonOne.classList.add('btn', 'delete');



      let buttonTwo = document.createElement('button');
      buttonTwo.classList.add('btn', 'archive');
      buttonTwo.textContent = 'Archive';

      buttonOne.addEventListener('click', deleteArticle);
      buttonTwo.addEventListener('click', archiveArticle);

      createDiv.appendChild(buttonOne);
      createDiv.appendChild(buttonTwo);

      newArticle.appendChild(createHeader);
      newArticle.appendChild(createParagraph);
      newArticle.appendChild(createParagraphForAuthor);
      newArticle.appendChild(createParagraphForContent);
      newArticle.appendChild(createDiv);

      boxOfArticles.appendChild(newArticle);

   }

   function deleteArticle(e) {

      e.target.parentNode.parentNode.remove();

   }

   function archiveArticle(e) {

      let currentArticle = e.target.parentNode.parentNode;

      deleteArticle(e);

      let header = currentArticle.getElementsByTagName('h1')[0];

      let boxOfArchives = document.querySelector('section[class="archive-section"]');

      let list = boxOfArchives.getElementsByTagName('ul')[0];

      let item = document.createElement('li');
      item.textContent = header.textContent;

      list.appendChild(item);

      let sortArray = Array.from(document.getElementsByTagName('li')).sort((a, b) => {

         return a.textContent.localeCompare(b.textContent);

      });

      list.innerHTML = '';

      for (let index = 0; index < sortArray.length; index++) {

         let createElement = document.createElement('li');
         createElement.textContent = sortArray[index].innerText;

         list.appendChild(createElement);

      }



   }

}
