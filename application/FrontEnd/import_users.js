// Function to import users and display them in the table
function importUsers() {
    fetch('data2.xml')  // Fetch the XML file from the same directory
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(str => {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(str, "text/xml");
            displayUsers(xmlDoc);  // Display the users in the table
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

// Function to display users in the table
function displayUsers(xmlDoc) {
    const users = xmlDoc.getElementsByTagName('user');
    const tbody = document.querySelector('#users-table tbody');
    tbody.innerHTML = '';  // Clear existing data

    for (let i = 0; i < users.length; i++) {
        const name = users[i].getElementsByTagName('name')[0].textContent;
        const email = users[i].getElementsByTagName('email')[0].textContent;
        const phone = users[i].getElementsByTagName('phone')[0].textContent;
        const role = users[i].getElementsByTagName('role')[0].textContent;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${name}</td>
            <td>${email}</td>
            <td>${phone}</td>
            <td>${role}</td>
        `;
        tbody.appendChild(row);
    }
}
