document.addEventListener("DOMContentLoaded", () => {
  const usersTable = document.querySelector("#usersTable tbody");
  const modalOverlay = document.getElementById("userModal");
  const modalContainer = document.querySelector(".modal-content");
  const closeModal = document.querySelector(".close");
  const userForm = document.getElementById("userForm");
  const addUserBtn = document.getElementById("addUserBtn");
  const saveUserBtn = document.getElementById("saveUserBtn");
  const dashboardBtn = document.getElementById("dashboard-btn");

  // Redirect to manage users page
  dashboardBtn.addEventListener("click", () => {
    window.location.href = "../admin.html";
  });

  let users = JSON.parse(localStorage.getItem("users")) || [];

  function populateTable() {
    usersTable.innerHTML = "";
    users.forEach((user) => {
      const row = document.createElement("tr");
      row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.role}</td>
                <td>
                    <span class="edit-btn" data-id="${+user.id}">&#9998;</span>
                    <span class="delete-btn" data-id="${+user.id}">&#10006;</span>
                </td>
            `;
      usersTable.appendChild(row);
    });

    document.querySelectorAll(".edit-btn").forEach((btn) => {
      btn.addEventListener("click", () => openModal(+btn.dataset.id));
    });

    document.querySelectorAll(".delete-btn").forEach((btn) => {
      btn.addEventListener("click", () => deleteUser(+btn.dataset.id));
    });
  }

  function openModal(id) {
    const user = users.find((u) => u.id === parseInt(id));
    document.getElementById("id").value = user.id;
    document.getElementById("name").value = user.name;
    document.getElementById("email").value = user.email;
    document.getElementById("phone").value = user.phone;
    document.getElementById("password").value = user.password;
    document.querySelector(`#${user.role.toLowerCase()}`).checked = true;
    document.getElementById("modalTitle").textContent = "Edit User";
    modalOverlay.classList.add("show");
    modalContainer.classList.add("show");
  }

  modalContainer.addEventListener("click", (e) => {
    e.stopPropagation();
  });

  function deleteUser(id) {
    users = users.filter((user) => user.id !== parseInt(id));
    localStorage.setItem("users", JSON.stringify(users));
    populateTable();
  }

  addUserBtn.addEventListener("click", () => {
    document.getElementById("userForm").reset();
    document.querySelector("#manager").checked = true;
    document.getElementById("modalTitle").textContent = "Add User";
    modalOverlay.classList.add("show");
    modalContainer.classList.add("show");
  });

  modalOverlay.addEventListener("click", () => {
    modalOverlay.classList.remove("show");
    modalContainer.classList.remove("show");
  });

  closeModal.addEventListener("click", () => {
    modalOverlay.classList.remove("show");
    modalContainer.classList.remove("show");
  });

  // bug with editing.
  userForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const id = parseInt(document.getElementById("id").value);
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const password = document.getElementById("password").value;
    const role = document.querySelector('input[name="role"]:checked').value;

    // if (users.some((user) => user.id === id)) {
    //   alert(`User with ID ${id} already exists.`);
    //   return;
    // }

    if (id && users.some((user) => user.id === id)) {
      const index = users.findIndex((user) => user.id === id);
      const user = users[index];
      if (user.role === "MANAGER")
        users[index] = {
          id,
          name,
          email,
          phone,
          password,
          role,
          credits: user.credits,
        };
      else {
        users[index] = {
          id,
          name,
          email,
          phone,
          password,
          role,
        };
      }
    } else {
      if (role === "MANAGER")
        users.push({ id, name, email, phone, password, role, credits: 2000 });
      else {
        users.push({ id, name, email, phone, password, role });
      }
    }

    localStorage.setItem("users", JSON.stringify(users));
    populateTable();
    modalOverlay.classList.remove("show");
    modalContainer.classList.remove("show");
  });

  populateTable();
});
