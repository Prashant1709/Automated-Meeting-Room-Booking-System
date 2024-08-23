document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("createRoomForm");

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

    const rooms = JSON.parse(localStorage.getItem("rooms")) || [];
    const newRoomId = rooms.length
      ? Math.max(...rooms.map((room) => room.meetingRoomId)) + 1
      : 1;

    const newRoom = {
      title,
      imgSrc,
      seatingCapacity: parseInt(seatingCapacity),
      amenities: selectedAmenities,
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

const amenities = {
  Projector: 5,
  "Wifi Connection": 10,
  "Conference Call Facility": 15,
  Whiteboard: 5,
  "Water Dispenser": 5,
  TV: 10,
  "Coffee Machine": 10,
};
