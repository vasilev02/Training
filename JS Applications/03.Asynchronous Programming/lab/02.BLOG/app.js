function attachEvents() {

    let loadButton = document.getElementById('btnLoadPosts');

    let viewButton = document.getElementById('btnViewPost');

    let postsOptions = document.getElementById('posts');

    let title = document.getElementById('post-title');

    let body = document.getElementById('post-body');

    let comments = document.getElementById('post-comments');


    let urlPosts = 'https://blog-apps-c12bf.firebaseio.com/posts.json';

    loadButton.addEventListener('click', () => {

        fetch(urlPosts)

            .then(response => response.json())

            .then(data => {

                Object.entries(data).forEach(([key, value]) => {

                    let createOption = document.createElement('option');

                    createOption.value = key;

                    createOption.textContent = value.title;

                    postsOptions.appendChild(createOption);

                });

            })

    });

    viewButton.addEventListener('click', () => {

        let urlComments = `https://blog-apps-c12bf.firebaseio.com/posts/${postsOptions.value}.json`


        fetch(urlComments)

            .then(response => response.json())

            .then(data => {

                title.textContent = data.title;

                body.textContent = data.body;

                comments.innerHTML = '';

                if (!data.comments) {
                    return;
                }

                data.comments.forEach(current => {

                    let listItem = document.createElement('li');

                    listItem.textContent = current.comment;

                    comments.appendChild(listItem);

                });

            });




    });

}

attachEvents();