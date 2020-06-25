class Article {

    constructor(title, creator) {
        this.title = title;
        this.creator = creator;
        this._comments = [];
        this._likes = [];
    }

    get likes() {

        if (this._likes.length === 0) {

            return `${this.title} has 0 likes`;

        } else if (this._likes.length === 1) {

            return `${this._likes[0].name} likes this article!`;

        } else {

            return `${this._likes[0].name} and ${this._likes.length - 1} others like this article!`;

        }

    }

    like(username) {

        for (let index = 0; index < this._likes.length; index++) {

            let current = this._likes[index];

            if (current.name === username) {
                throw new Error("You can't like the same article twice!");
            }
            if (current.name === this.creator) {
                throw new Error("You can't like your own articles!");
            }

        }

        let objectPerson = {

            name: username

        }

        this._likes.push(objectPerson);

        return `${username} liked ${this.title}!`;

    }

    dislike(username) {

        for (let index = 0; index < this._likes.length; index++) {

            let current = this._likes[index];

            if (current.name === username) {

                this._likes[index] = null;

                let newArray = [];

                for (let index1 = 0; index1 < this._likes.length; index1++) {

                    if (this._likes[index1] !== null) {
                        newArray.push(this._likes[index1]);
                    }

                }

                this._likes = newArray;

                return `${username} disliked ${this.title}`;

            }

        }

        throw new Error("You can't dislike this article!");

    }

    comment(username, content, id) {

        let result = this._comments.find(c => {

            if (c.Id === id) {
                return c;
            }

        })

        if (id === undefined || result === undefined) {

            let createComment = {

                Username: username,
                Content: content,
                Id: this._comments.length + 1,
                Replies: []

            }

            this._comments.push(createComment);

            return `${username} commented on ${this.title}`;

        } else {

            let createId = `${result.Id}.${result.Replies.length + 1}`;

            let createReply = {

                Username: username,
                Content: content,
                Id: createId

            }

            result.Replies.push(createReply);

            return `You replied successfully`;

        }

    }

    toString(sortingType) {

        let string = '';

        if (sortingType === 'asc') {

            string += `Title: ${this.title}\n`;
            string += `Creator: ${this.creator}\n`;
            string += `Likes: ${this._likes.length}\n`;
            string += `Comments:\n`;

            this._comments.sort((a, b) => {

                return a.Id - b.Id;

            }).forEach(c => {

                string += `-- ${c.Id}. ${c.Username}: ${c.Content}\n`;

                c.Replies.sort((a, b) => {

                    return a.Id.localeCompare(b.Id);

                }).forEach(r => {

                    string += `--- ${r.Id}. ${r.Username}: ${r.Content}\n`;

                });

            });

        } else if (sortingType === 'desc') {

            string += `Title: ${this.title}\n`;
            string += `Creator: ${this.creator}\n`;
            string += `Likes: ${this._likes.length}\n`;
            string += `Comments:\n`;

            this._comments.sort((a, b) => {

                return b.Id - a.Id;

            }).forEach(c => {

                string += `-- ${c.Id}. ${c.Username}: ${c.Content}\n`;

                c.Replies.sort((a, b) => {

                    return b.Id.localeCompare(a.Id);

                }).forEach(r => {

                    string += `--- ${r.Id}. ${r.Username}: ${r.Content}\n`;

                });

            });



        } else if (sortingType === 'username') {

            string += `Title: ${this.title}\n`;
            string += `Creator: ${this.creator}\n`;
            string += `Likes: ${this._likes.length}\n`;
            string += `Comments:\n`;

            this._comments.sort((a, b) => {

                return a.Username.localeCompare(b.Username);

            }).forEach(c => {

                string += `-- ${c.Id}. ${c.Username}: ${c.Content}\n`;

                c.Replies.sort((a, b) => {

                    return a.Username.localeCompare(b.Username);

                }).forEach(r => {

                    string += `--- ${r.Id}. ${r.Username}: ${r.Content}\n`;

                });

            });



        }

        return string.trim();

    }

}

let art = new Article("My Article", "Anny");
art.like("John");
console.log(art.likes);
art.dislike("John");
console.log(art.likes);
art.comment("Sammy", "Some Content");
console.log(art.comment("Ammy", "New Content"));
art.comment("Zane", "Reply", 1);
art.comment("Jessy", "Nice :)");
console.log(art.comment("SAmmy", "Reply@", 1));
console.log()
console.log(art.toString('username'));
console.log()
art.like("Zane");
console.log(art.toString('desc'));
