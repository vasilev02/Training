function loadCommits() {

    let usernameInput = document.getElementById('username');

    let repositoryInput = document.getElementById('repo');

    let list = document.getElementById('commits');

    list.innerHTML = '';

    function generateURL(username, repo) {

        return `https://api.github.com/repos/${username}/${repo}/commits`;

    }

    let url = generateURL(usernameInput.value, repositoryInput.value);

    fetch(url)

        .then(response => {

            if (response.status === 200) {

                return response.json();

            }

            return Promise.reject(response);

        })

        .then(data => {

            data.forEach(item => {

                let listItem = document.createElement('li');

                listItem.textContent = `${item.commit.author.name}: ${item.commit.message}`;

                list.appendChild(listItem);

            });

        })

        .catch(error => {

            let listItem = document.createElement('li');

            listItem.textContent = `Error: ${error.status}`;

            list.appendChild(listItem);

            return;

        })



}