document.addEventListener("DOMContentLoaded", function () {
  const meetings = JSON.parse(localStorage.getItem("meetings"));
  console.log(meetings);
  const tableBody = document.querySelector("#meetings-table tbody");

  meetings?.forEach((meeting) => {
    const row = document.createElement("tr");
    row.innerHTML = `
            <td>${meeting.room.title}</td>
            <td>${formatDuration(meeting.duration)}</td>
            <td>${
              new Date(meeting.bookingEndTime) > new Date()
                ? "Ongoing"
                : "Completed"
            }</td>
            <td>${new Date(meeting.bookingEndTime).toLocaleString()}</td>
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

// script.js

document.getElementById("backToManager").addEventListener("click", function () {
  window.location.href = "/application/FrontEnd/ManagerPage/index.html"; // Replace with the actual path to your manager page
});

// Additional JavaScript code for fetching and displaying meetings
