function solve() {

   let message = document.getElementById('chat_input').value;

   let button = document.getElementById('send');

   button.addEventListener('click', sendMessage);

   function sendMessage() {

      let message = document.getElementById('chat_input').value;

      let newMessage = document.createElement('div');

      newMessage.classList.add('message', 'my-message');

      newMessage.textContent = message;

      let getSection = document.getElementById('chat_messages');

      getSection.appendChild(newMessage);

      document.getElementById('chat_input').value = '';

   }

}

