document.addEventListener("DOMContentLoaded", () => {
  const queryParams = new URLSearchParams(window.location.search);
  const roomId = queryParams.get("roomId");

  // need to clear fields in the rooms array once meeting ends, need to show the meetings on the manager page.

  const room = getRoomById(roomId);
  const manager = {
    id: 1,
    name: "John Doe",
    role: "MANAGER",
    credits: 50,
  };

  if (!room) {
    alert("Room not found");
    return;
  }

  document.getElementById("roomImage").src = room.imgSrc;

  const userList = [
    { id: 1, name: "Alice", role: "USER" },
    { id: 2, name: "Bob", role: "USER" },
    { id: 3, name: "Charlie", role: "USER" },
    { id: 4, name: "David", role: "USER" },
    { id: 5, name: "Eve", role: "USER" },
    { id: 6, name: "Frank", role: "USER" },
    { id: 7, name: "Grace", role: "USER" },
    { id: 8, name: "Hannah", role: "USER" },
    { id: 9, name: "Isaac", role: "USER" },
    { id: 10, name: "Jack", role: "USER" },
  ];

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

    userListContainer.appendChild(checkbox);
    userListContainer.appendChild(label);
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

      const totalCost = calculateTotalCost(room.amenities, duration.hours);
      if (totalCost > manager.credits) {
        document.getElementById("error-message").textContent =
          "Not enough credits.";
        return;
      }

      const meetingEndTime = new Date();
      meetingEndTime.setHours(meetingEndTime.getHours() + duration.hours);
      meetingEndTime.setMinutes(meetingEndTime.getMinutes() + duration.minutes);
      meetingEndTime.setSeconds(meetingEndTime.getSeconds() + duration.seconds);

      room.duration = durationStr;
      room.meetingEndTime = meetingEndTime.toISOString();
      room.status = "booked";
      room.isBooked = true;
      room.members = selectedUserIds
        .map((userId) => userList.find((user) => user.id === userId))
        .concat(manager);

      updateRoomInLocalStorage(roomId, room);

      const meeting = {
        room: room,
        duration: durationStr,
        meetingEndTime: meetingEndTime.toISOString(),
        members: room.members,
        manager: manager,
      };

      saveMeetingToLocalStorage(meeting);

      window.location.href = "admin.html"; // Redirect after booking
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
    errorMessage.textContent = `Select exactly ${seatingCapacity - 1} users.`;
    return false;
  }

  return true;
}

function calculateTotalCost(amenities, hours) {
  return (
    Object.values(amenities).reduce((total, cost) => total + cost, 0) * hours
  );
}
