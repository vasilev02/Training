function createArticle() {

	let titleInput = document.getElementById('createTitle');

	let contentInput = document.getElementById('createContent');

	if (titleInput.value !== '' && contentInput.value !== '') {

		let getAllArticles = document.querySelector('#articles');

		let createHead = document.createElement('h3');

		let createParagraph = document.createElement('p');

		let createArticle = document.createElement('article');

		createHead.textContent = titleInput.value;

		createParagraph.textContent = contentInput.value;

		createArticle.appendChild(createHead);
		createArticle.appendChild(createParagraph);

		getAllArticles.appendChild(createArticle);

		titleInput.value = '';
		contentInput.value = '';

	}

}