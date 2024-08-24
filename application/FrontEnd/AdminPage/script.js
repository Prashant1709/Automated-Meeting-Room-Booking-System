let rooms = JSON.parse(localStorage.getItem("rooms")) || [
  {
    title: "Executive Room A",
    imgSrc:
      "https://media.istockphoto.com/id/1323139676/photo/outside-view-of-empty-meeting-room-with-table-and-office-chairs.jpg?s=612x612&w=0&k=20&c=nqEWkNlKAfpdTUl_jiky8YbxiO4jBYcr0gCA5z4MvA0=",
    seatingCapacity: 10,
    amenities: {
      Projector: 5,
      "Conference Call Facility": 15,
      Whiteboard: 5,
      "Wifi Connection": 10,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 1,
  },
  {
    title: "Conference Room B",
    imgSrc:
      "https://m.media-amazon.com/images/I/61b9u0Q0uJL._AC_UF894,1000_QL80_.jpg",
    seatingCapacity: 12,
    amenities: {
      TV: 10,
      "Coffee Machine": 10,
      Whiteboard: 5,
      "Wifi Connection": 10,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 2,
  },
  {
    title: "Boardroom C",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdNKexCUl0f2Jr8-5gSxjGYhhOAbrQnmaavw&s",
    seatingCapacity: 8,
    amenities: {
      Projector: 5,
      "Conference Call Facility": 15,
      "Wifi Connection": 10,
      "Water Dispenser": 5,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 3,
  },
  {
    title: "Training Room D",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzAXr4DN7tUdc2A0lTrLJ1ZB7tdqKvqzVuWfzSL6uy61Sl7xZww_IOss-Tl1ZToAnfWQE&usqp=CAU",
    seatingCapacity: 20,
    amenities: {
      Projector: 5,
      TV: 10,
      "Wifi Connection": 10,
      Whiteboard: 5,
      "Coffee Machine": 10,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 4,
  },
  {
    title: "Meeting Room E",
    imgSrc:
      "https://www.dgicommunications.com/wp-content/uploads/2018/03/DSC_2917.jpg",
    seatingCapacity: 6,
    amenities: {
      Projector: 5,
      "Wifi Connection": 10,
      "Water Dispenser": 5,
      TV: 10,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 5,
  },
  {
    title: "Huddle Room F",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMT7Hf11Opv4HIbXpHdkIpTkECiqL6kUx_hRrXuOs5RXTZeihrvW8XzkCghhbBheDjP8c&usqp=CAU",
    seatingCapacity: 4,
    amenities: {
      "Wifi Connection": 10,
      Whiteboard: 5,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 6,
  },
  {
    title: "Executive Suite G",
    imgSrc:
      "https://media.istockphoto.com/id/1323139676/photo/outside-view-of-empty-meeting-room-with-table-and-office-chairs.jpg?s=612x612&w=0&k=20&c=nqEWkNlKAfpdTUl_jiky8YbxiO4jBYcr0gCA5z4MvA0=",
    seatingCapacity: 14,
    amenities: {
      Projector: 5,
      "Conference Call Facility": 15,
      "Coffee Machine": 10,
      "Wifi Connection": 10,
      TV: 10,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 7,
  },
  {
    title: "Open Space H",
    imgSrc:
      "https://m.media-amazon.com/images/I/61b9u0Q0uJL._AC_UF894,1000_QL80_.jpg",
    seatingCapacity: 15,
    amenities: {
      "Wifi Connection": 10,
      Whiteboard: 5,
      "Water Dispenser": 5,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 8,
  },
  {
    title: "Team Room I",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdNKexCUl0f2Jr8-5gSxjGYhhOAbrQnmaavw&s",
    seatingCapacity: 9,
    amenities: {
      Projector: 5,
      "Wifi Connection": 10,
      "Coffee Machine": 10,
      TV: 10,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 9,
  },
  {
    title: "Strategy Room J",
    imgSrc:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzAXr4DN7tUdc2A0lTrLJ1ZB7tdqKvqzVuWfzSL6uy61Sl7xZww_IOss-Tl1ZToAnfWQE&usqp=CAU",
    seatingCapacity: 7,
    amenities: {
      "Conference Call Facility": 15,
      "Wifi Connection": 10,
      Whiteboard: 5,
      "Water Dispenser": 5,
    },
    status: "available",
    isBooked: false,
    bookingEndTime: null,
    members: [],
    duration: 0,
    meetingRoomId: 10,
  },
];

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
    if (room.status === "booked") {
      card.classList.add("bookedRoom");
    } else card.classList.remove("bookedRoom");
    card.innerHTML = `
            <img src="${room.imgSrc}" alt="${room.title}">
            <div class="status">${room.status.toUpperCase()}</div>
            <div class="icons">
                <img src="https://cdn-icons-png.flaticon.com/512/5935/5935145.png" alt="Edit" onclick="editRoom(${
                  room.meetingRoomId
                })">
                <img src="https://cdn-icons-png.flaticon.com/512/6048/6048190.png" alt="Delete" onclick="deleteRoom(${
                  room.meetingRoomId
                })">
            </div>
            <div class="card-content">
                <h3>${room.title}</h3>
                <div class="amenities">
                    ${amenitiesList}
                </div>
                <div class="total-cost">Total Cost: $${totalCost}/hr</div>
                ${
                  room.status === "booked"
                    ? `<div class="timer" id="timer-${room?.meetingRoomId}"></div>`
                    : ""
                }
            </div>
        `;

    cardsGrid.appendChild(card);

    if (room.status === "booked" || room.isBooked === true) {
      startTimer(
        room.bookingEndTime,
        document.getElementById(`timer-${room?.meetingRoomId}`),
        room?.meetingRoomId
      );
    }
  });
}

// Function to Calculate Total Cost of Amenities
function calculateTotalCost(amenities) {
  return Object.values(amenities).reduce((acc, curr) => acc + curr, 0);
}

function startTimer(endTime, timerElement, roomId) {
  const updateTimer = () => {
    const now = new Date();
    const timeLeft = new Date(endTime) - now;

    if (timeLeft > 0) {
      const hours = Math.floor(timeLeft / (1000 * 60 * 60));
      const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);
      timerElement.textContent = `Time left: ${hours}h ${minutes}m ${seconds}s`;
    } else {
      rooms?.forEach((room) => {
        if (room?.meetingRoomId === roomId) {
          room.bookingEndTime = null;
          room.duration = 0;
          room.isBooked = false;
          room.members = [];
          room.status = "available";
        }
      });
      saveRooms();
      if (timerElement) timerElement.textContent = ``;
    }
  };

  updateTimer();
  setInterval(updateTimer, 1000);
}

function deleteRoom(roomId) {
  const index = rooms?.findIndex((room) => room?.meetingRoomId === roomId);
  rooms.splice(index, 1);
  saveRooms(); // Save updated array to Local Storage
  renderCards(); // Re-render cards
}

// Function to Edit a Room (Stub function for now)
function editRoom(roomId) {
  // alert("Edit room functionality for room roomId: " + roomId);
  window.location.href = `/application/FrontEnd/AdminPage/EditRoom/editRoom.html?roomId=${roomId}`;
}

// Load Data from Local Storage and Render on Page Load
window.onload = function () {
  const user = JSON.parse(localStorage.getItem("loggedInUser"));

  if (user)
    document.querySelector(".nameTitle").textContent = `Welcome, ${user.name}`;

  // Check if the user exists and has the role of 'ADMIN'
  if (!user || user.role !== "ADMIN") {
    // Redirect to the homepage if the user is not an admin
    window.location.href = "/application/FrontEnd/Homepage/index.html";
  }
  if (user) renderCards(); // Render cards on page load
  const users = localStorage.getItem("users");
  const importUsersBtn = document.getElementById("importUsersBtn");

  if (users) {
    importUsersBtn.style.display = "none";
  } else {
    importUsersBtn.addEventListener("click", importUsers);
  }

  const manageUsersBtn = document.getElementById("manage-users-btn");
  if (localStorage.getItem("users")) {
    manageUsersBtn.classList.add("show");
  }

  // Redirect to manage users page
  manageUsersBtn.addEventListener("click", () => {
    window.location.href = "ManageUsers/manageusers.html";
  });
};

document
  .getElementById("view-meetings-btn")
  .addEventListener("click", function () {
    window.location.href = "CreateRoom/createRoom.html"; // Redirect to the meetings page
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

// Function to parse XML and convert it to a JavaScript object
function parseXMLToUsers(xml) {
  const parser = new DOMParser();
  const xmlDoc = parser.parseFromString(xml, "application/xml");
  const users = [];
  const userElements = xmlDoc.getElementsByTagName("user");

  for (let i = 0; i < userElements.length; i++) {
    const userElement = userElements[i];
    const id = Number(userElement.getElementsByTagName("id")[0].textContent);
    const name = userElement.getElementsByTagName("name")[0].textContent;
    const email = userElement.getElementsByTagName("email")[0].textContent;
    const phone = userElement.getElementsByTagName("phone")[0].textContent;
    const password =
      userElement.getElementsByTagName("password")[0].textContent;
    const role = userElement.getElementsByTagName("role")[0].textContent;
    const credits = Number(
      userElement.getElementsByTagName("credits")[0]?.textContent
    );
    if (role === "MEMBER")
      users.push({ id, name, email, phone, password, role });
    else {
      users.push({ id, name, email, phone, password, role, credits });
    }
  }

  return users;
}

// Function to handle importing users from the XML file
function importUsers() {
  fetch("data.xml")
    .then((response) => response.text())
    .then((data) => {
      const users = parseXMLToUsers(data);
      localStorage.setItem("users", JSON.stringify(users));
      document.getElementById("importUsersBtn").style.display = "none";
      alert("Users have been imported successfully.");
    })
    .catch((error) => console.error("Error importing users:", error));
}

const logoutHandler = () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "/application/FrontEnd/Homepage/index.html";
};

const goToHome = () => {
  window.location.href = "/application/FrontEnd/Homepage/index.html";
};
