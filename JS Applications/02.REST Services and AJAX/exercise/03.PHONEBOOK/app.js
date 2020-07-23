function attachEvents() {

    let inputPerson = document.getElementById('person');

    let inputPhone = document.getElementById('phone');

    let url = 'http://localhost:8000/phonebook';

    let buttonLoad = document.getElementById('btnLoad');

    let buttonCreate = document.getElementById('btnCreate');

    let list = document.getElementById('phonebook');

    buttonCreate.addEventListener('click', () => {

        let person = inputPerson.value;
        let phone = inputPhone.value;


        fetch(url, {

            method: 'POST',
            body: JSON.stringify({ person, phone })

        });

    });

    buttonLoad.addEventListener('click', () => {

        list.innerHTML = '';

        fetch(url)
            .then(response => response.json())
            .then(data => {

                let keys = Object.keys(data);

                for (let index = 0; index < keys.length; index++) {

                    let key = keys[index];

                    let listItem = document.createElement('li');

                    listItem.textContent = `${data[key].person} : ${data[key].phone}`;

                    let createButton = document.createElement('button');
                    createButton.textContent = 'Delete';

                    createButton.addEventListener('click', function (e) {

                        let currentPerson = e.target.parentNode;

                        let name = currentPerson.textContent.split(' ')[0];
                        let mobile = currentPerson.textContent.split(' ')[1];

                        fetch('http://localhost:8000/phonebook')
                            .then(response => response.json())
                            .then(data => {

                                let array = Object.entries(data);

                                for (let index = 0; index < array.length; index += 2) {

                                    let current = array[index];

                                    let obj = current[1];

                                    if (obj['person'] === name && obj['phone'] === mobile) {

                                        fetch(`http://localhost:8000/phonebook/${current[0]}`,{

                                        method:'DELETE'

                                        });

                                    }




                                }


                            });





                    });

                    listItem.appendChild(createButton);

                    list.appendChild(listItem);

                }



            });

    });


}

attachEvents();