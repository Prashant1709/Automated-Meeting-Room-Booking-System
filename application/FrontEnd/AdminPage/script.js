import { newRooms } from "./Rooms.js";
let rooms = JSON.parse(localStorage.getItem("rooms")) || newRooms;

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
        document.getElementById(`timer-${index}`),
        index
      );
    }
  });
}

// Function to Calculate Total Cost of Amenities
function calculateTotalCost(amenities) {
  return Object.values(amenities).reduce((acc, curr) => acc + curr, 0);
}

function startTimer(endTime, timerElement, index) {
  const updateTimer = () => {
    const now = new Date();
    const timeLeft = new Date(endTime) - now;

    if (timeLeft > 0) {
      const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);
      timerElement.textContent = `Time left: ${minutes}m ${seconds}s`;
    } else {
      console.log(index);
      rooms[index].status = "Available";
      rooms[index].bookingEndTime = null;
      saveRooms();
      timerElement.textContent = ``;
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
