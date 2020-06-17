function addItem() {

    let inputText = document.getElementById('newItemText').value;

    let inputValue = document.getElementById('newItemValue').value;

    let selectSection = document.getElementById('menu');

    let createOption = document.createElement('option');

    createOption.textContent = inputText;
    createOption.value = inputValue;

    selectSection.appendChild(createOption);

    document.getElementById('newItemText').value = '';

    document.getElementById('newItemValue').value = '';

}