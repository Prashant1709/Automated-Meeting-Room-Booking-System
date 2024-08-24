document.addEventListener("DOMContentLoaded", function () {
  const meetings = JSON.parse(localStorage.getItem("meetings"));
  console.log(meetings);
  const tableBody = document.querySelector("#meetings-table tbody");

  meetings?.forEach((meeting) => {
    const row = document.createElement("tr");
    row.classList.add("rows");
    row.innerHTML = `
            <td class="room">${meeting.room.title}</td>
            <td class="duration">${formatDuration(meeting.duration)}</td>
            <td class="status">${
              new Date(meeting.bookingEndTime) > new Date()
                ? "Ongoing"
                : "Completed"
            }</td>
            <td class="date">${new Date(
              meeting.bookingEndTime
            ).toLocaleString()}</td>
            <td class="mType">${meeting.meetingType}</td>
        `;
    row.addEventListener("click", () => openModal(meeting));
    tableBody.appendChild(row);
  });

  const modal = document.getElementById("meeting-modal");
  const closeBtn = document.querySelector(".close-btn");
  const modalBody = document.getElementById("modal-body");

  function openModal(meeting) {
    modalBody.innerHTML = `
            <p><strong>Meeting Room:</strong> ${meeting.room.title}</p>
            <p><strong>Duration:</strong> ${formatDuration(
              meeting.duration
            )}</p>
            <p><strong>Status:</strong> ${
              meeting.isBooked ? "Ongoing" : "Completed"
            }</p>
            <p><strong>End Time:</strong> ${new Date(
              meeting.bookingEndTime
            ).toLocaleString()}</p>
            <p><strong>Meeting Type:</strong> ${meeting.meetingType}</p>
            <p><strong>Members:</strong> ${meeting.members
              .map((member) => member.name)
              .join(", ")}</p>
            <p><strong>Manager:</strong> ${meeting.manager.name}</p>
        `;
    modal.style.display = "block";
  }

  closeBtn.onclick = function () {
    modal.style.display = "none";
  };

  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };

  function formatDuration(duration) {
    const [hours, minutes, seconds] = duration.split(":").map(Number);

    // Return an object with the parsed values
    // return {
    //   hours: hours || 0,
    //   minutes: minutes || 0,
    //   seconds: seconds || 0,
    // };
    return `${hours}h ${minutes}m ${seconds}s`;
  }
});

document
  .getElementById("view-meetings-btn")
  .addEventListener("click", function () {
    window.location.href = "/application/FrontEnd/ManagerPage/index.html";
  });

document.getElementById("searchBar").addEventListener("input", function () {
  const searchText = this.value.toLowerCase();
  const rows = document.querySelectorAll(".rows");

  rows.forEach((row) => {
    const roomName = row.querySelector(".room").textContent.toLowerCase();
    const status = row.querySelector(".status").textContent.toLowerCase();
    const date = row.querySelector(".date").textContent.toLowerCase();
    const type = row.querySelector(".mType").textContent.toLowerCase();

    if (
      roomName.includes(searchText) ||
      status.includes(searchText) ||
      date.includes(searchText) ||
      type.includes(searchText)
    ) {
      row.style.display = "";
    } else {
      row.style.display = "none";
    }
  });
});

let sortOrder = 0; // 0: default, 1: ascending, -1: descending

document
  .getElementById("durationHeader")
  .addEventListener("click", function () {
    const rowsArray = Array.from(document.querySelectorAll(".rows"));
    const sortIcon = document.getElementById("sortIcon");

    sortOrder = sortOrder === 0 ? 1 : sortOrder === 1 ? -1 : 0;

    rowsArray.sort((a, b) => {
      const durationA = parseDuration(a.querySelector(".duration").textContent);
      const durationB = parseDuration(b.querySelector(".duration").textContent);

      console.log(durationA, durationB);

      return sortOrder * (durationA - durationB);
    });

    const tbody = document.querySelector(".tablebody");
    tbody.innerHTML = "";

    rowsArray.forEach((row) => tbody.appendChild(row));

    sortIcon.style.transform =
      sortOrder === 1 ? "rotate(180deg)" : "rotate(0deg)";
  });

function parseDuration(durationStr) {
  const [hours, minutes, seconds] = durationStr
    .split(" ")
    .map((item) => Number(item.substr(0, 1)));
  return hours * 3600 + minutes * 60 + seconds;
}

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

const logoutHandler = () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "/application/FrontEnd/Homepage/index.html";
};
