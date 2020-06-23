class Forum {

    constructor() {

        this._users = [];
        this._questions = [];
        this._id = 1;

    }

    register(username, password, repeatPassword, email) {

        this.checkForEmptyString(username);
        this.checkForEmptyString(password);
        this.checkForEmptyString(repeatPassword);
        this.checkForEmptyString(email);

        this.checkEqualPasswords(password, repeatPassword);

        this.checkUserExist(username, email)

        let newUser = {

            username,
            password,
            email,
            log: false

        }

        this._users.push(newUser);

        return `${username} with ${email} was registered successfully!`;

    }

    login(username, password) {

        let person = this.checkSingleUser(username);

        if (person.password === password) {

            person.log = true;

            return "Hello! You have logged in successfully";

        }

    }

    logout(username, password) {

        let person = this.checkSingleUser(username);

        if (person.password === password) {

            person.log = false;

            return "Hello! You have logged out successfully";

        }

    }

    postQuestion(username, question) {

        //attention!!!!
        let person = this._users.find(user => {

            if (user.username === username) {
                return user;
            }

        });

        if (person === undefined || person.log === false) {
            throw new Error('You should be logged in to post questions');
        }
        if (question === '') {
            throw new Error('Invalid question');
        }

        let singleQuestion = {

            id: this._id,
            person,
            question,
            answers: []

        }

        this._id++;

        this._questions.push(singleQuestion);

        return 'Your question has been posted successfully';


    }

    postAnswer(username, questionId, answer) {

        let person = this._users.find(user => {

            if (user.username === username) {
                return user;
            }

        });

        if (person === undefined || person.log === false) {
            throw new Error('You should be logged in to post answers');
        }
        if (answer === '') {
            throw new Error('Invalid answer');
        }

        let currentQuestion = this._questions.find(q => {

            if (q.id === questionId) {
                return q;
            }

        });
        if (currentQuestion === undefined) {
            throw new Error("There is no such question");
        }

        let singleAnswer = {

            person,
            answer

        }

        currentQuestion.answers.push(singleAnswer);

        return 'Your answer has been posted successfully';


    }

    showQuestions() {

        let text = '';

        for (let index = 0; index < this._questions.length; index++) {

            let current = this._questions[index];

            text += `Question ${current.id} by ${current.person.username}: ${current.question}\n`;

            let allAnswers = current.answers;

            for (let index = 0; index < allAnswers.length; index++) {

                let answ = allAnswers[index];

                text += `---${answ.person.username}: ${answ.answer}\n`;

            }

        }

        return text.trim();

    }



    //------------------------Help Methods--------------------------

    checkForEmptyString(input) {
        if (input === '') {
            throw new Error('Input can not be empty');
        }
    }

    checkEqualPasswords(pass, repeatPass) {
        if (pass !== repeatPass) {
            throw new Error('Passwords do not match');
        }
    }

    checkUserExist(username, email) {

        let result = this._users.find(user => {

            if (user.username === username || user.email === email) {
                return user;
            }
        });

        if (result !== undefined) {
            throw new Error('This user already exists!');
        }

    }

    checkSingleUser(name) {

        let result = this._users.find(user => {

            if (user.username === name) {
                return user;
            }
        });

        if (result === undefined) {
            throw new Error('There is no such user');
        } else {
            return result;
        }
    }

    
}



let forum = new Forum();

forum.register('Jonny', '12345', '12345', 'jonny@abv.bg');
forum.register('Peter', '123ab7', '123ab7', 'peter@gmail@.com');
forum.login('Jonny', '12345');
forum.login('Peter', '123ab7');

forum.postQuestion('Jonny', "Do I need glasses for skiing?");
forum.postAnswer('Peter',1, "Yes, I have rented one last year.");
forum.postAnswer('Jonny',1, "What was your budget");
forum.postAnswer('Peter',1, "$50");
forum.postAnswer('Jonny',1, "Thank you :)");

console.log(forum.showQuestions());
