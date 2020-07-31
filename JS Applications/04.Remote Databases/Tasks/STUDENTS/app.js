import * as data from './backend.js';

let idInput = document.getElementById('id');
let firstNameInput = document.getElementById('firstName');
let lastNameInput = document.getElementById('lastName');
let facultyInput = document.getElementById('facultyNumber');
let gradeInput = document.getElementById('grade');

let tbody = document.getElementsByTagName('tbody')[0];

let button = document.getElementsByTagName('button')[0];

button.addEventListener('click', async function (e) {

    e.preventDefault();

    let object = {

        ID: Number(idInput.value),
        FirstName: firstNameInput.value,
        LastName: lastNameInput.value,
        FacultyNumber: facultyInput.value,
        Grade: Number(gradeInput.value)

    }

    data.createStudent(object);

    tbody.innerHTML = '';

    let students = await data.getStudents();

    students.sort((a, b) => {

        return a.ID - b.ID;

    }).map(student => {


        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');

        td1.textContent = student.ID;
        td2.textContent = student.FirstName;
        td3.textContent = student.LastName;
        td4.textContent = student.FacultyNumber;
        td5.textContent = student.Grade;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);

        tbody.appendChild(tr);


    })


});

