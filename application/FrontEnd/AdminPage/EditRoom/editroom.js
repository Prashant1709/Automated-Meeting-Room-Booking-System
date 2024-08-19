document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("editRoomForm");
  const amenitiesContainer = document.getElementById("amenitiesContainer");
  const addAmenityBtn = document.getElementById("addAmenityBtn");

  // Retrieve the room ID from the query parameters
  const params = new URLSearchParams(window.location.search);
  const roomId = parseInt(params.get("roomId"), 10);

  // Load the room data from localStorage
  const rooms = JSON.parse(localStorage.getItem("rooms")) || [];
  const room = rooms.find((room) => room.meetingRoomId === roomId);

  if (!room) {
    alert("Room not found");
    window.location.href = "admin.html";
    return;
  }

  // Prefill the form with room details
  document.getElementById("title").value = room.title;
  document.getElementById("imgSrc").value = room.imgSrc;
  document.getElementById("seatingCapacity").value = room.seatingCapacity;
  document.getElementById("roomImage").src = room.imgSrc;

  // Prefill the amenities
  Object.entries(room.amenities).forEach(([name, cost]) => {
    const amenityDiv = document.createElement("div");
    amenityDiv.className = "amenity-input";
    amenityDiv.innerHTML = `
            <input type="text" value="${name}" required>
            <input type="number" value="${cost}" required>
            <button type="button" class="remove-btn">Remove</button>
        `;
    amenitiesContainer.appendChild(amenityDiv);

    // Remove amenity field
    amenityDiv.querySelector(".remove-btn").addEventListener("click", () => {
      amenitiesContainer.removeChild(amenityDiv);
    });
  });

  // Add new amenity fields dynamically
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

    // Update the room details
    room.title = title;
    room.imgSrc = imgSrc;
    room.seatingCapacity = parseInt(seatingCapacity);
    room.amenities = amenities;

    // Save the updated rooms array back to localStorage
    localStorage.setItem("rooms", JSON.stringify(rooms));
    window.location.href = "/application/FrontEnd/AdminPage/admin.html";
  });
});
