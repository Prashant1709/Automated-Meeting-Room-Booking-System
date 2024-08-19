let rooms = [];
document.addEventListener("DOMContentLoaded", () => {
  const roomsContainer = document.getElementById("roomsContainer");

  // load rooms from localstorage
  rooms.forEach((room) => {
    const roomCard = document.createElement("div");
    roomCard.className = "room-card";
    if (room.isBooked) {
      roomCard.classList.add("booked");
    }

    roomCard.innerHTML = `
            <img src="${room.imgSrc}" alt="Meeting Room">
            <div class="status">${room.status}</div>
            <div class="room-details">
                <h3 class="room-title">${room.title}</h3>
                <p class="room-info">Seating Capacity: ${
                  room.seatingCapacity
                }</p>
                <p class="amenities-list">Amenities: ${Object.keys(
                  room.amenities
                ).join(", ")}</p>
                <p class="room-info">Total Cost per Hour: $${Object.values(
                  room.amenities
                ).reduce((a, b) => a + b, 0)}</p>
                ${
                  room.isBooked
                    ? `<div class="timer" id="timer-${room.meetingRoomId}"></div>`
                    : ""
                }
                ${
                  !room.isBooked
                    ? `<a href="bookRoom.html?roomId=${room.meetingRoomId}" class="book-room-btn">Book Room</a>`
                    : ""
                }
            </div>
        `;

    roomsContainer.appendChild(roomCard);
    // a function is needed which calculates the remaining time of a meeting and shows in the div with the class name timer
  });
});
