document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("createRoomForm");
  const amenitiesContainer = document.getElementById("amenitiesContainer");
  const addAmenityBtn = document.getElementById("addAmenityBtn");

  // Add amenity fields dynamically
  addAmenityBtn.addEventListener("click", () => {
    const amenityDiv = document.createElement("div");
    amenityDiv.className = "amenity-input";
    amenityDiv.innerHTML = `
            <input type="text" placeholder="Amenity Name" required>
            <input type="number" placeholder="Cost per Hour" required>
            <button type="button" class="remove-btn">Remove</button>
        `;
    amenitiesContainer.appendChild(amenityDiv);

    // Remove amenity field
    amenityDiv.querySelector(".remove-btn").addEventListener("click", () => {
      amenitiesContainer.removeChild(amenityDiv);
    });
  });

  // Form submission
  form.addEventListener("submit", (event) => {
    event.preventDefault();

    const title = document.getElementById("title").value;
    const imgSrc = document.getElementById("imgSrc").value;
    const seatingCapacity = document.getElementById("seatingCapacity").value;

    const amenities = {};
    document.querySelectorAll(".amenity-input").forEach((inputDiv) => {
      const amenityName = inputDiv.querySelector('input[type="text"]').value;
      const amenityCost = inputDiv.querySelector('input[type="number"]').value;
      amenities[amenityName] = parseInt(amenityCost);
    });

    const rooms = JSON.parse(localStorage.getItem("rooms")) || [];
    const newRoomId = rooms.length
      ? Math.max(rooms.map((room) => room.meetingRoomId)) + 1
      : 1;

    const newRoom = {
      title,
      imgSrc,
      seatingCapacity: parseInt(seatingCapacity),
      amenities,
      status: "available",
      isBooked: false,
      bookingEndTime: null,
      members: [],
      duration: 0,
      meetingRoomId: newRoomId,
    };

    rooms.push(newRoom);
    localStorage.setItem("rooms", JSON.stringify(rooms));
    window.location.href = "/application/FrontEnd/AdminPage/admin.html";
  });
});
