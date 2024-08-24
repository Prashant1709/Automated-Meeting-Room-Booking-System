let rooms = [];

// Fetching the rooms from rooms.json
fetch("rooms.json")
  .then((response) => response.json())
  .then((data) => {
    // changing this line because we need to fetch rooms from localstorage.
    rooms = JSON.parse(localStorage.getItem("rooms")) || data;
    const manager = JSON.parse(localStorage.getItem("loggedInUser"));
    document.querySelector(".name").textContent = `Welcome, ${manager.name}`;

    function renderRooms() {
      const roomsContainer = document.getElementById("roomsContainer");

      // Load rooms from fetched data
      rooms.forEach((room) => {
        const roomCard = document.createElement("div");
        roomCard.className = "room-card";

        //instead of isbooked , used status to do the same task
        if (room.status === "booked") {
          roomCard.classList.add("booked");
        }

        roomCard.innerHTML = `
          <img src="${room.imgSrc}" alt="Meeting Room">
          <div class="status">${room.status}</div>
          <div class="room-details">
              <h3 class="room-title">${room.title}</h3>
              <p class="room-info">Seating Capacity: ${room.seatingCapacity}</p>
              <p class="amenities-list">Amenities: ${Object.keys(
                room.amenities
              ).join(", ")}</p>
              <p class="room-info">Total Cost per Hour: $${Object.values(
                room.amenities
              ).reduce((a, b) => a + b, 0)}</p>
              ${
                room.status === "booked"
                  ? `<div class="timer" id="timer-${room.meetingRoomId}"></div>`
                  : ""
              }
              ${
                room.status === "available"
                  ? `<a href="BookRoom/index.html?roomId=${room.meetingRoomId}" class="book-room-btn">Book Room</a>`
                  : ""
              }
          </div>
        `;

        roomsContainer.appendChild(roomCard);
      });

      // Calling updateTimer every second to update the timer in real-time
      setInterval(updateTimer, 1000);
    }

    // If the DOM is already loaded
    if (document.readyState === "loading") {
      document.addEventListener("DOMContentLoaded", renderRooms);
    } else {
      renderRooms();
    }
  })
  .catch((error) => console.error("Error fetching the rooms:", error));

// Function to calculate and display the remaining time of a meeting
// do change the time to see the timer working

function updateTimer() {
  const timers = document.querySelectorAll(".timer");

  timers.forEach((timer) => {
    const roomId = timer.id.split("-")[1];
    const room = rooms.find((r) => r.meetingRoomId === parseInt(roomId));

    if (room && room.bookingEndTime) {
      const endTime = new Date(room.bookingEndTime);
      const now = new Date();

      const timeDiff = endTime - now;
      if (timeDiff > 0) {
        const hours = Math.floor(timeDiff / (1000 * 60 * 60));
        const minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

        // displaying the timer in HH:mm:SS format
        const formattedTime =
          String(hours).padStart(2, "0") +
          ":" +
          String(minutes).padStart(2, "0") +
          ":" +
          String(seconds).padStart(2, "0");

        timer.textContent = formattedTime;
      } else {
        // If the time is up it will display  and then will change status of meeting room and set
        // bookingEndTime to null
        timer.textContent = "Meeting Ended";
        room.bookingEndTime = null;
        room.duration = 0;
        room.isBooked = false;
        room.members = [];
        room.status = "available";
        saveRooms();
      }
    }
  });
}

// Function to Save Rooms to Local Storage
function saveRooms() {
  localStorage.setItem("rooms", JSON.stringify(rooms));
}

document
  .getElementById("view-meetings-btn")
  .addEventListener("click", function () {
    window.location.href = "Meetings/meetings.html"; // Redirect to the meetings page
  });

let menu = document.querySelector("#menu-btn");
let navbar = document.querySelector(".navbar");

menu.onclick = () => {
  menu.classList.toggle("fa-times");
  navbar.classList.toggle("active");
};

window.onscroll = () => {
  menu.classList.remove("fa-times");
  navbar.classList.remove("active");
};

var slideUp = {
  distance: "150%",
  origin: "bottom",
  opacity: null,
};

const logoutHandler = () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "/application/FrontEnd/Homepage/index.html";
};

const goToHome = () => {
  window.location.href = "/application/FrontEnd/Homepage/index.html";
};
