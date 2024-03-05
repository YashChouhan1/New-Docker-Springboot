const fetchButton = document.getElementById('fetchButton');
const dataContainer = document.getElementById('dataContainer');

fetchButton.addEventListener('click', fetchData);

function fetchData() {
    fetch('http://backend:8080/all')
        .then(response => response.json())
        .then(data => {
            displayData(data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

function displayData(data) {
    let output = '';
    data.forEach(item => {
        output += `<div>ID: ${item.id}, Name: ${item.name}</div>`;
    });
    dataContainer.innerHTML = output;
}
