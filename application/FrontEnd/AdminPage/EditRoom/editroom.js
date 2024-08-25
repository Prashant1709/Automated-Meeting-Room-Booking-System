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

  // Prefill the amenities checkboxes
  const amenitiesCheckboxes = document.querySelectorAll(
    'input[name="amenities"]'
  );

  amenitiesCheckboxes.forEach((checkbox) => {
    // Check if the current checkbox corresponds to an amenity in the room
    if (room.amenities.hasOwnProperty(checkbox.value)) {
      // keep changing this to lowercase
      checkbox.checked = true;
    }
  });

  // Form submission
  form.addEventListener("submit", (event) => {
    event.preventDefault();

    const title = document.getElementById("title").value;
    const imgSrc = document.getElementById("imgSrc").value;
    const seatingCapacity = document.getElementById("seatingCapacity").value;

    const selectedAmenities = {};
    const checkboxes = document.querySelectorAll(
      'input[name="amenities"]:checked'
    );

    if (checkboxes.length === 0) {
      alert("Please select at least one amenity.");
      return;
    }

    checkboxes.forEach((checkbox) => {
      const amenity = checkbox.value;
      selectedAmenities[amenity] = amenities[amenity];
    });

    // Update the room details
    room.title = title;
    room.imgSrc = imgSrc;
    room.seatingCapacity = parseInt(seatingCapacity);
    room.amenities = selectedAmenities;

    // Save the updated rooms array back to localStorage
    localStorage.setItem("rooms", JSON.stringify(rooms));
    window.location.href = "/application/FrontEnd/AdminPage/admin.html";
  });
});

const amenities = {
  Projector: 5,
  "Wifi Connection": 10,
  "Conference Call Facility": 15,
  Whiteboard: 5,
  "Water Dispenser": 5,
  TV: 10,
  "Coffee Machine": 10,
};
