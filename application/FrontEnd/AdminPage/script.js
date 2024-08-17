let rooms = JSON.parse(localStorage.getItem("rooms")) || [
  {
    title: "Executive Room A",
    imgSrc:
      "https://media.istockphoto.com/id/1323139676/photo/outside-view-of-empty-meeting-room-with-table-and-office-chairs.jpg?s=612x612&w=0&k=20&c=nqEWkNlKAfpdTUl_jiky8YbxiO4jBYcr0gCA5z4MvA0=",
    seatingCapacity: 10,
    amenities: {
      projector: 20,
      "conference call facility": 15,
      whiteboard: 5,
      wifi: 10,
    },
    status: "available",
    bookingEndTime: null,
  },
  {
    title: "Conference Room B",
    imgSrc:
      "https://m.media-amazon.com/images/I/61b9u0Q0uJL._AC_UF894,1000_QL80_.jpg",
    seatingCapacity: 12,
    amenities: {
      tv: 25,
      "coffee machine": 10,
      whiteboard: 5,
      wifi: 10,
    },
    status: "booked",
    bookingEndTime: new Date(Date.now() + 7200000), // 2 hours from now
  },
  {
    title: "Boardroom C",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdNKexCUl0f2Jr8-5gSxjGYhhOAbrQnmaavw&s",
    seatingCapacity: 8,
    amenities: {
      projector: 20,
      "conference call facility": 15,
      wifi: 10,
      "water dispenser": 5,
    },
    status: "available",
    bookingEndTime: null,
  },
  {
    title: "Training Room D",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzAXr4DN7tUdc2A0lTrLJ1ZB7tdqKvqzVuWfzSL6uy61Sl7xZww_IOss-Tl1ZToAnfWQE&usqp=CAU",
    seatingCapacity: 20,
    amenities: {
      projector: 20,
      tv: 25,
      wifi: 10,
      whiteboard: 5,
      "coffee machine": 10,
    },
    status: "booked",
    bookingEndTime: new Date(Date.now() + 3600000), // 1 hour from now
  },
  {
    title: "Meeting Room E",
    imgSrc:
      "https://www.dgicommunications.com/wp-content/uploads/2018/03/DSC_2917.jpg",
    seatingCapacity: 6,
    amenities: {
      projector: 20,
      wifi: 10,
      "water dispenser": 5,
      tv: 25,
    },
    status: "available",
    bookingEndTime: null,
  },
  {
    title: "Huddle Room F",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMT7Hf11Opv4HIbXpHdkIpTkECiqL6kUx_hRrXuOs5RXTZeihrvW8XzkCghhbBheDjP8c&usqp=CAU",
    seatingCapacity: 4,
    amenities: {
      wifi: 10,
      whiteboard: 5,
    },
    status: "available",
    bookingEndTime: null,
  },
  {
    title: "Executive Suite G",
    imgSrc:
      "https://media.istockphoto.com/id/1323139676/photo/outside-view-of-empty-meeting-room-with-table-and-office-chairs.jpg?s=612x612&w=0&k=20&c=nqEWkNlKAfpdTUl_jiky8YbxiO4jBYcr0gCA5z4MvA0=",
    seatingCapacity: 14,
    amenities: {
      projector: 20,
      "conference call facility": 15,
      "coffee machine": 10,
      wifi: 10,
      tv: 25,
    },
    status: "booked",
    bookingEndTime: new Date(Date.now() + 5400000), // 1.5 hours from now
  },
  {
    title: "Open Space H",
    imgSrc:
      "https://m.media-amazon.com/images/I/61b9u0Q0uJL._AC_UF894,1000_QL80_.jpg",
    seatingCapacity: 15,
    amenities: {
      wifi: 10,
      whiteboard: 5,
      "water dispenser": 5,
    },
    status: "available",
    bookingEndTime: null,
  },
  {
    title: "Team Room I",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdNKexCUl0f2Jr8-5gSxjGYhhOAbrQnmaavw&s",
    seatingCapacity: 9,
    amenities: {
      projector: 20,
      wifi: 10,
      "coffee machine": 10,
      tv: 25,
    },
    status: "booked",
    bookingEndTime: new Date(Date.now() + 10800000), // 3 hours from now
  },
  {
    title: "Strategy Room J",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzAXr4DN7tUdc2A0lTrLJ1ZB7tdqKvqzVuWfzSL6uy61Sl7xZww_IOss-Tl1ZToAnfWQE&usqp=CAU",
    seatingCapacity: 7,
    amenities: {
      "conference call facility": 15,
      wifi: 10,
      whiteboard: 5,
      "water dispenser": 5,
    },
    status: "available",
    bookingEndTime: null,
  },
  {
    title: "Presentation Room K",
    imgSrc:
      "https://www.dgicommunications.com/wp-content/uploads/2018/03/DSC_2917.jpg",
    seatingCapacity: 25,
    amenities: {
      projector: 20,
      tv: 25,
      wifi: 10,
      "coffee machine": 10,
      "conference call facility": 15,
    },
    status: "booked",
    bookingEndTime: new Date(Date.now() + 7200000), // 2 hours from now
  },
  {
    title: "Creative Room L",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMT7Hf11Opv4HIbXpHdkIpTkECiqL6kUx_hRrXuOs5RXTZeihrvW8XzkCghhbBheDjP8c&usqp=CAU",
    seatingCapacity: 5,
    amenities: {
      whiteboard: 5,
      wifi: 10,
      "water dispenser": 5,
      "coffee machine": 10,
    },
    status: "available",
    bookingEndTime: null,
  },
];

// Function to Save Rooms to Local Storage
function saveRooms() {
  localStorage.setItem("rooms", JSON.stringify(rooms));
}

// Function to Render Cards on the Page
function renderCards() {
  const cardsGrid = document.getElementById("cardsGrid");
  cardsGrid.innerHTML = ""; // Clear existing cards

  if (!localStorage.getItem("rooms"))
    localStorage.setItem("rooms", JSON.stringify(rooms));

  rooms.forEach((room, index) => {
    const totalCost = calculateTotalCost(room.amenities);
    const amenitiesList = Object.keys(room.amenities)
      .map(
        (amenity) => `<span>${amenity}: $${room.amenities[amenity]}/hr</span>`
      )
      .join("");

    const card = document.createElement("div");
    card.classList.add("card");
    card.innerHTML = `
            <img src="${room.imgSrc}" alt="${room.title}">
            <div class="status">${room.status.toUpperCase()}</div>
            <div class="icons">
                <img src="https://cdn-icons-png.flaticon.com/512/5935/5935145.png" alt="Edit" onclick="editRoom(${index})">
                <img src="https://cdn-icons-png.flaticon.com/512/6048/6048190.png" alt="Delete" onclick="deleteRoom(${index})">
            </div>
            <div class="card-content">
                <h3>${room.title}</h3>
                <div class="amenities">
                    ${amenitiesList}
                </div>
                <div class="total-cost">Total Cost: $${totalCost}/hr</div>
                ${
                  room.status === "booked"
                    ? `<div class="timer" id="timer-${index}"></div>`
                    : ""
                }
            </div>
        `;

    cardsGrid.appendChild(card);

    if (room.status === "booked") {
      startTimer(
        room.bookingEndTime,
        document.getElementById(`timer-${index}`)
      );
    }
  });
}

// Function to Calculate Total Cost of Amenities
function calculateTotalCost(amenities) {
  return Object.values(amenities).reduce((acc, curr) => acc + curr, 0);
}

function startTimer(endTime, timerElement) {
  const updateTimer = () => {
    const now = new Date();
    const timeLeft = new Date(endTime) - now;

    if (timeLeft > 0) {
      const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);
      timerElement.textContent = `Time left: ${minutes}m ${seconds}s`;
    } else {
      timerElement.textContent = `Available now`;
    }
  };

  updateTimer();
  setInterval(updateTimer, 1000);
}

// Function to Delete a Room
function deleteRoom(index) {
  rooms.splice(index, 1); // Remove room from array
  saveRooms(); // Save updated array to Local Storage
  renderCards(); // Re-render cards
}

// Function to Edit a Room (Stub function for now)
function editRoom(index) {
  alert("Edit room functionality for room index: " + index);
}

// Load Data from Local Storage and Render on Page Load
window.onload = function () {
  renderCards(); // Render cards on page load
};
