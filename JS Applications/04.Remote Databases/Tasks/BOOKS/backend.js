
let appId = '8F6F82A2-9E1E-A198-FF79-520CBF5D1E00';
let restId = '08BDA936-8D4E-4C7F-BED3-8C93ED6585BF';

export function getBooks() {

    return fetch(`https://api.backendless.com/${appId}/${restId}/data/books`)
        .then(response => response.json());

}

export function createBook(current) {

    fetch(`https://api.backendless.com/${appId}/${restId}/data/books`, {

        method: "POST",
        body: JSON.stringify(current)

    });

}

export function editBook(current) {

    fetch(`https://api.backendless.com/${appId}/${restId}/data/books/${current.objectId}`, {

        method: "PUT",
        body: JSON.stringify(current)

    });

}

export function deleteBook(current) {

    fetch(`https://api.backendless.com/${appId}/${restId}/data/books/${current.objectId}`, {

        method: "DELETE"

    });

}