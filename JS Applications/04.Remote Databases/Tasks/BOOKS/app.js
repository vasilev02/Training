import * as data from "./backend.js";

let createButton = document.getElementsByTagName('button')[1];

let inputAuthor = document.getElementById('author');
let inputTitle = document.getElementById('title');
let inputIsbn = document.getElementById('isbn');

let loadButton = document.getElementById('loadBooks');

loadButton.addEventListener('click', async function () {

    let result = await data.getBooks();

    let tbody = document.getElementsByTagName('tbody')[0];

    tbody.innerHTML = '';

    for (let index = 0; index < result.length; index++) {

        let tr = document.createElement('tr');

        let tdAuthor = document.createElement('td');
        let tdName = document.createElement('td');
        let tdIsbn = document.createElement('td');
        let tdButtons = document.createElement('td');

        let editButton = document.createElement('button');
        editButton.textContent = 'Edit';

        editButton.addEventListener('click', function () {

            tdAuthor.innerHTML = '<input id="auth" type="text" id="a" >';
            tdName.innerHTML = '<input id="nam" type="text" id="name" >';
            tdIsbn.innerHTML = '<input id="btn" type="text" id="b" >';

            let btn = document.createElement('button');
            btn.textContent = 'Save';
            tr.appendChild(btn);

            btn.addEventListener('click', function () {

                let authValue = document.getElementById("auth").value;
                let nameValue = document.getElementById("nam").value;
                let isbnValue = document.getElementById("btn").value;

                let tdAuthor = document.createElement('td');
                let tdName = document.createElement('td');
                let tdIsbn = document.createElement('td');

                tdAuthor.textContent = authValue;
                tdName.textContent = nameValue;
                tdIsbn.textContent = isbnValue;

                result[index].author = authValue;
                result[index].title = nameValue;
                result[index].isbn = isbnValue;

                data.editBook(result[index]);

                btn.outerHTML = '';

            });




        });

        let deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';

        deleteButton.addEventListener('click', function () {

            data.deleteBook(result[index]);

        });


        tdAuthor.textContent = result[index].author;
        tdName.textContent = result[index].title;
        tdIsbn.textContent = result[index].isbn;

        tr.appendChild(tdAuthor);
        tr.appendChild(tdName);
        tr.appendChild(tdIsbn);


        tdButtons.appendChild(editButton);
        tdButtons.appendChild(deleteButton);

        tr.appendChild(tdButtons);

        tbody.appendChild(tr);

    }

});



createButton.addEventListener('click', function (e) {

    e.preventDefault();

    let object = {

        author: inputAuthor.value,
        title: inputTitle.value,
        isbn: inputIsbn.value

    }

    data.createBook(object);

    inputAuthor.value = '';
    inputTitle.value = '';
    inputIsbn.value = '';

});



