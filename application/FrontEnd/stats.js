// Function to populate the table with room data
function populateTable(data) {
    const tableBody = document.getElementById('roomTableBody');
    tableBody.innerHTML = ''; // Clear any existing content

    data.rooms.forEach(room => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${room.name}</td>
            <td>${room.location}</td>
            <td>${room.bookings}</td>
            <td>${room.hours}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Function to create a bar chart for top usage
function createBarChart(data) {
    const ctx = document.getElementById('topUsageChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.rooms.map(room => room.name),
            datasets: [{
                label: 'Bookings',
                data: data.rooms.map(room => room.bookings),
                backgroundColor: 'rgba(52, 152, 219, 0.6)',
                borderColor: 'rgba(52, 152, 219, 1)',
                borderWidth: 1
            }, {
                label: 'Spent Hours',
                data: data.rooms.map(room => room.hours),
                backgroundColor: 'rgba(46, 204, 113, 0.6)',
                borderColor: 'rgba(46, 204, 113, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Function to fetch data from JSON file and initialize the UI
function loadData() {
    fetch('data.json')
        .then(response => response.json())
        .then(data => {
            populateTable(data);
            createBarChart(data);
        })
        .catch(error => console.error('Error fetching the data:', error));
}

// Load data and initialize the UI when the page loads
window.onload = loadData;

