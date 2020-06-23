function solve() {

   let inputFields = document.querySelectorAll('input[type="text"]');

   let nameField = inputFields[1];
   let quantityField = inputFields[2];
   let priceField = inputFields[3];

   let addButton = document.getElementsByTagName('button')[1];

   let filterButton = document.getElementsByTagName('button')[0];

   let buyButton = document.getElementsByTagName('button')[2];

   buyButton.addEventListener('click', buyAndEmpty);

   filterButton.addEventListener('click', filterProducts);

   addButton.addEventListener('click', addProduct);

   function buyAndEmpty(e) {

      let priceText = document.getElementsByTagName('h1')[1];
      priceText.textContent = 'Total Price: 0.00';

      let section = document.getElementById('myProducts');

      let list = section.getElementsByTagName('ul')[0];

      list.innerHTML = '';

   }

   function addProduct(e) {

      e.preventDefault();

      let section = document.getElementById('products');
      let list = section.getElementsByTagName('ul')[0];

      let createListItem = document.createElement('li');

      let createSpan = document.createElement('span');
      createSpan.textContent = nameField.value;

      let createStrong = document.createElement('strong');
      createStrong.textContent = `Available: ${quantityField.value}`

      //-----------------------------------------------

      let createDiv = document.createElement('div');

      let createStrongForDiv = document.createElement('strong');
      createStrongForDiv.textContent = Number(priceField.value).toFixed(2);

      let createButton = document.createElement('button');
      createButton.textContent = 'Add to Client\'s List';
      createButton.addEventListener('click', addToClientList);

      createDiv.appendChild(createStrongForDiv);
      createDiv.appendChild(createButton);

      createListItem.appendChild(createSpan);
      createListItem.appendChild(createStrong);
      createListItem.appendChild(createDiv);

      list.appendChild(createListItem);


   }

   function filterProducts(e) {

      let filterText = document.getElementById('filter');

      let section = document.getElementById('products');
      let list = section.getElementsByTagName('ul')[0];

      for (let index = 0; index < list.children.length; index++) {

         let current = list.getElementsByTagName('li')[index];
         let name = current.getElementsByTagName('span')[0].textContent;

         let changedName = name.toLowerCase();
         let changerFilterText = filterText.value.toLowerCase();

         if (changedName.includes(changerFilterText)) {

            current.style.display = 'block';

         } else {

            current.style.display = 'none';

         }

      }

   }

   function addToClientList(e) {

      let item = e.target.parentNode.parentNode;

      let strong = item.getElementsByTagName('strong')[0].textContent;

      let availableNumber = Number(strong.split(' ')[1]);

      if (availableNumber > 0) {
         availableNumber--;

         item.getElementsByTagName('strong')[0].textContent = `Available: ${availableNumber}`;

         let section = document.getElementById('myProducts');

         let list = section.getElementsByTagName('ul')[0];

         let createListItem = document.createElement('li');
         createListItem.textContent = item.getElementsByTagName('span')[0].textContent;

         let createStrong = document.createElement('strong');
         createStrong.textContent = Number(item.getElementsByTagName('strong')[1].textContent).toFixed(2);

         createListItem.appendChild(createStrong);

         list.appendChild(createListItem);

         let priceText = document.getElementsByTagName('h1')[1];

         let data = priceText.textContent.split(' ');

         data[2] = (Number(data[2]) + Number(createStrong.textContent)).toFixed(2);

         priceText.textContent = data.join(' ');

      }

      if (availableNumber == 0) {

         e.target.parentNode.parentNode.remove();

      }




   }


}