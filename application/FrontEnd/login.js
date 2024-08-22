// Function to load and parse the XML file
function loadUserData() {
    fetch('data.xml')  // Fetching the XML file from the same directory
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();  // Getting the text of the XML file
        })
        .then(str => {
            console.log('XML data loaded:', str); // Debugging output
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(str, "text/xml");  // Parsing the XML string
            validateLogin(xmlDoc);  // Validate login with the XML data
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

// Function to validate user login
function validateLogin(xmlDoc) {
    const username = document.getElementById('user').value;
    const password = document.getElementById('password').value;
    const users = xmlDoc.getElementsByTagName('user');

    let userFound = false;
    let userRole = '';

    for (let i = 0; i < users.length; i++) {
        const xmlUsername = users[i].getElementsByTagName('name')[0].textContent;
        const xmlPassword = users[i].getElementsByTagName('password')[0]?.textContent;
        const xmlRole = users[i].getElementsByTagName('role')[0].textContent;

        if (username === xmlUsername && (!xmlPassword || password === xmlPassword)) {
            userFound = true;
            userRole = xmlRole.toLowerCase();
            break;
        }
    }

    if (userFound) {
        redirectToRolePage(userRole);
    } else {
        alert('Invalid username or password');
    }
}

// Function to redirect the user to the appropriate page based on their role
function redirectToRolePage(role) {
    console.log('Redirecting to role:', role); // Debugging output
    if (role === 'admin') {
        window.location.href = 'admin.html';
    } else if (role === 'manager') {
        window.location.href = 'manager.html';
    } else if (role === 'member') {
        window.location.href = 'member.html';
    } else {
        alert('Unknown role');
    }
}

// Event listener for form submission
document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the form from submitting normally
    loadUserData();  // Load and validate user data
});

