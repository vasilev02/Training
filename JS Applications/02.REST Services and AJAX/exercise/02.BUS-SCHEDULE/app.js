function solve() {

    let url = 'http://localhost:8000/schedule/depot';

    let informationBox = document.getElementById('info');

    let departButton = document.getElementById('depart');

    let arriveButton = document.getElementById('arrive');


    function depart() {

        fetch(url)
            .then(response => response.json())
            .then(data => {

                informationBox.textContent = `Next stop ${data['name']}`;

                url = `http://localhost:8000/schedule/${data['next']}`;

                departButton.disabled = true;
                arriveButton.disabled = false;

            });

    }

    function arrive() {

        let stopName = informationBox.textContent.split(' ')[2];

        informationBox.textContent = `Arriving at ${stopName}`;

        departButton.disabled = false;
        arriveButton.disabled = true;

    }

    return {
        depart,
        arrive
    };
}

let result = solve();