function attachEvents() {

    let inputAuthor = document.getElementById('author');
    let inputMessage = document.getElementById('content');

    let btnSubmit = document.getElementById('submit');
    let btnRefresh = document.getElementById('refresh');

    let boxMessages = document.getElementById('messages');

    let url = 'http://localhost:8000/messenger';

    btnSubmit.addEventListener('click', () => {

        let object = {

            author: inputAuthor.value,
            content: inputMessage.value

        }

        fetch(url, {

            method: 'POST',
            body: JSON.stringify(object)

        });

        inputAuthor.value = '';
        inputMessage.value = '';

    });

    btnRefresh.addEventListener('click', () => {

        fetch(url)
            .then(response => response.json())
            .then(data => {

                let array = Object.keys(data);

                boxMessages.innerHTML = '';

                for (let index = 0; index < array.length; index++) {

                    let current = data[array[index]];

                    boxMessages.textContent += `${current['author']}: ${current['content']}\n`;

                }

            });

    });

}

attachEvents();