document.addEventListener("DOMContentLoaded", () => {
  const roomsContainer = document.getElementById("roomsContainer");
  const rooms = JSON.parse(localStorage.getItem("rooms")) || [];

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
                    ? `<div class="timer">Available in: ${calculateTimeLeft(
                        room.bookingEndTime
                      )}</div>`
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
  });
});

function calculateTimeLeft(bookingEndTime) {
  if (!bookingEndTime) return "N/A";

  const now = new Date();
  const endTime = new Date(bookingEndTime);
  const timeLeft = Math.max(0, endTime - now);

  const hours = Math.floor(timeLeft / (1000 * 60 * 60));
  const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
  const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

  return `${hours}h ${minutes}m ${seconds}s`;
}
