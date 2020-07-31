let appId = '8F6F82A2-9E1E-A198-FF79-520CBF5D1E00';
let restId = '08BDA936-8D4E-4C7F-BED3-8C93ED6585BF';

export function getStudents() {

    return fetch(`https://api.backendless.com/${appId}/${restId}/data/students`)
        .then(response => response.json());

}

export function createStudent(object) {

    fetch(`https://api.backendless.com/${appId}/${restId}/data/students`, {

        method: 'POST',
        body: JSON.stringify(object)

    });

}