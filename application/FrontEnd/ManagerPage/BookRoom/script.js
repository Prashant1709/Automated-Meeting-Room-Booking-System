document.addEventListener("DOMContentLoaded", () => {
  const queryParams = new URLSearchParams(window.location.search);
  const roomId = queryParams.get("roomId");

  const room = getRoomById(roomId);
  const manager = JSON.parse(localStorage.getItem("loggedInUser"));

  if (!room) {
    alert("Room not found");
    return;
  }

  document.getElementById("roomName").textContent = room.title;

  const amenitiesList = Object.keys(room.amenities)
    .map((amenity) => `${amenity} ($${room.amenities[amenity]}/hr)`)
    .join(", ");
  document.getElementById("roomAmenities").textContent = amenitiesList;

  const amenitiesCost = Object.values(room.amenities).reduce(
    (sum, cost) => sum + cost,
    0
  );

  // Determine the additional cost based on room capacity
  let capacityCost = 0;
  if (room.seatingCapacity >= 6 && room.seatingCapacity <= 10) {
    capacityCost = 10;
  } else if (room.seatingCapacity > 10) {
    capacityCost = 20;
  }

  document.getElementById("roomCapacity").textContent = room.seatingCapacity;
  document.getElementById("bookingCost").textContent =
    amenitiesCost + capacityCost;

  document.getElementById("roomImage").src = room.imgSrc;

  const userList =
    JSON.parse(localStorage.getItem("users")).filter(
      (u) => u.id !== manager.id
    ) || [];

  const userListContainer = document.getElementById("userList");
  userList.forEach((user) => {
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.id = `user-${user.id}`;
    checkbox.value = user.id;
    checkbox.name = "users";

    const label = document.createElement("label");
    label.htmlFor = checkbox.id;
    label.textContent = user.name;

    const div = document.createElement("div");
    div.classList.add("checkboxWrapper");
    div.appendChild(checkbox);
    div.appendChild(label);

    userListContainer.appendChild(div);
    // userListContainer.appendChild();
  });

  document
    .getElementById("bookingForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();

      const durationStr = document.getElementById("duration").value;
      const duration = parseDuration(durationStr);
      const selectedUserIds = Array.from(
        document.querySelectorAll('input[name="users"]:checked')
      ).map((checkbox) => parseInt(checkbox.value));

      if (
        !validateForm(
          durationStr,
          selectedUserIds.length,
          room.seatingCapacity,
          room.amenities
        )
      ) {
        return;
      }

      let totalCost =
        calculateTotalCost(room.amenities, convertTimeToHours(durationStr)) +
        capacityCost;

      const extraAmenities = Array.from(
        document.querySelectorAll('input[name="extraAmenity"]:checked')
      ).map((el) => el.value);
      let extraAmenitiesCost = 0;
      extraAmenities.forEach((amenity) => {
        extraAmenitiesCost += amenities[amenity];
      });
      totalCost += extraAmenitiesCost * convertTimeToHours(durationStr);
      if (totalCost > manager.credits) {
        document.getElementById("error-message").textContent =
          "Not enough credits.";
        return;
      }

      const bookingEndTime = new Date();
      bookingEndTime.setHours(bookingEndTime.getHours() + duration.hours);
      bookingEndTime.setMinutes(bookingEndTime.getMinutes() + duration.minutes);
      bookingEndTime.setSeconds(bookingEndTime.getSeconds() + duration.seconds);

      room.duration = durationStr;
      room.bookingEndTime = bookingEndTime.toISOString();
      room.status = "booked";
      room.isBooked = true;
      room.members = selectedUserIds
        .map((userId) => userList.find((user) => user.id === userId))
        .concat(manager);

      updateRoomInLocalStorage(roomId, room);
      updateManager(manager, totalCost);

      const extraNewAmenities = Array.from(
        document.querySelectorAll('input[name="extraAmenity"]:checked')
      ).map((el) => el.value);

      const meetingType = document.querySelector(
        'input[name="meetingType"]:checked'
      ).value;

      const meeting = {
        room: room,
        duration: durationStr,
        bookingEndTime: bookingEndTime.toISOString(),
        members: room.members,
        manager: manager,
        totalCost: totalCost,
        extraAmenities: extraNewAmenities,
        meetingType: meetingType,
      };

      saveMeetingToLocalStorage(meeting);
      

      window.location.href = "/application/FrontEnd/ManagerPage/index.html"; // Redirect after booking
    });
});

function getRoomById(roomId) {
  const rooms = JSON.parse(localStorage.getItem("rooms")) || [];
  return rooms.find((room) => room.meetingRoomId == roomId);
}

function updateRoomInLocalStorage(roomId, updatedRoom) {
  const rooms = JSON.parse(localStorage.getItem("rooms")) || [];
  const index = rooms.findIndex((room) => room.meetingRoomId == roomId);
  if (index !== -1) {
    rooms[index] = updatedRoom;
    localStorage.setItem("rooms", JSON.stringify(rooms));
  }
}

const updateManager = (manager, cost) => {
  const users = JSON.parse(localStorage.getItem("users")) || [];
  const index = users.findIndex((user) => user.id === manager.id);
  if (index !== -1) {
    users[index].credits = manager.credits - Number(cost);
    localStorage.setItem("users", JSON.stringify(users));
  }
};

function saveMeetingToLocalStorage(meeting) {
  let meetings = JSON.parse(localStorage.getItem("meetings")) || [];
  meetings.push(meeting);
  localStorage.setItem("meetings", JSON.stringify(meetings));
}

function parseDuration(durationStr) {
  const [hours = 0, minutes = 0, seconds = 0] = durationStr
    .split(":")
    .map(Number);
  return { hours, minutes, seconds };
}

function validateForm(durationStr, selectedUsersCount, seatingCapacity) {
  const timeRegex = /^([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/;
  const isValidTime = timeRegex.test(durationStr);
  const isValidUsers =
    selectedUsersCount > 0 && selectedUsersCount <= seatingCapacity - 1;
  const errorMessage = document.getElementById("error-message");

  errorMessage.textContent = "";

  if (!isValidTime) {
    errorMessage.textContent = "Invalid time format. Use H:M:S";
    return false;
  }

  if (!isValidUsers) {
    errorMessage.textContent = `Select maximum ${seatingCapacity - 1} users.`;
    return false;
  }

  return true;
}

function calculateTotalCost(amenities, hours) {
  return (
    Object.values(amenities).reduce((total, cost) => total + cost, 0) * hours
  );
}

const amenities = {
  Projector: 5,
  "Wifi Connection": 10,
  "Conference Call Facility": 15,
  Whiteboard: 5,
  "Water Dispenser": 5,
  TV: 10,
  "Coffee Machine": 10,
};

function convertTimeToHours(timeString) {
  const [hours, minutes, seconds] = timeString.split(":").map(Number);
  return hours + minutes / 60 + seconds / 3600;
}
