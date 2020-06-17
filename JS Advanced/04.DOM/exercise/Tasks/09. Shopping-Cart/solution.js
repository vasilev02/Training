function solve() {

   let addButtons = document.querySelectorAll('.add-product');

   let checkOutButton = document.querySelector('button[class="checkout"]');

   checkOutButton.addEventListener('click', totalAmount);


   let area = document.querySelector('textarea[rows="5"]');

   let cost = 0;
   let list = [];

   for (let index = 0; index < addButtons.length; index++) {

      let current = addButtons[index];

      current.addEventListener('click', buyProduct);

   }

   function totalAmount(e) {

      let text = 'You bought ';

      text += list.join(', ');

      text += ' for ' + cost.toFixed(2)+'.';

      area.textContent += text;

      e.target.disabled = true;

      addButtons.forEach(b =>{

         b.disabled = true;

      })

   }

   function buyProduct(e) {

      let product = e.currentTarget.parentNode.parentNode;

      let kids = product.children;

      let price = kids[3].textContent;
      cost += Number(price);


      let name = kids[1].children[0].textContent;
      if (!list.includes(name)) {
         list.push(name);
      }

      let productInfo = `Added ${name} for ${price} to the cart.\n`;

      area.textContent += productInfo;


      console.log();

   }

}