function loadRepos() {

	let user = document.getElementById('username');

	let list = document.getElementById('repos');

	list.innerHTML = '';

	let url = `https://api.github.com/users/${user.value}/repos`;

	fetch(url)
		.then(response => response.json())
		.then(data => {

			data.forEach(element => {

				let createLi = document.createElement('li');
				let createAnchor = document.createElement('a');

				createAnchor.href = element.html_url;
				createAnchor.textContent = element.full_name;

				createLi.appendChild(createAnchor);

				list.appendChild(createLi);

			});

		});

}