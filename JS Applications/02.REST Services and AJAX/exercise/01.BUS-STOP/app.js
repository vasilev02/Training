function getInfo() {

    let inputField = document.getElementById('stopId');

    let stopNameBox = document.getElementById('stopName');

    stopNameBox.innerHTML = '';

    let busesList = document.getElementById('buses');

    busesList.innerHTML = '';

    let url = 'http://localhost:8000/businfo';



    fetch(url)
        .then(response => response.json())
        .then(data => {

            let numbers = Object.keys(data);

            if (numbers.includes(inputField.value)) {

                let bus = data[inputField.value];

                stopNameBox.textContent = bus['name'];

                let busIds = Object.entries(bus['buses']);

                for (let index = 0; index < busIds.length; index++) {

                    let current = busIds[index];

                    let listElement = document.createElement('li');

                    listElement.textContent = `Bus ${current[0]} arrives in ${current[1]}`;

                    busesList.appendChild(listElement);

                }

            } else {

                stopNameBox.textContent = 'Error';

            }

        });

    inputField.value = '';

}