document.addEventListener("DOMContentLoaded", () => {
  const usersTable = document.querySelector("#usersTable tbody");
  const userModal = document.getElementById("userModal");
  const closeModal = document.querySelector(".close");
  const userForm = document.getElementById("userForm");
  const addUserBtn = document.getElementById("addUserBtn");
  const saveUserBtn = document.getElementById("saveUserBtn");

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
    userModal.style.display = "block";
  }

  function deleteUser(id) {
    users = users.filter((user) => user.id !== parseInt(id));
    localStorage.setItem("users", JSON.stringify(users));
    populateTable();
  }

  addUserBtn.addEventListener("click", () => {
    document.getElementById("userForm").reset();
    document.querySelector("#manager").checked = true;
    document.getElementById("modalTitle").textContent = "Add User";
    userModal.style.display = "block";
  });

  closeModal.addEventListener("click", () => {
    userModal.style.display = "none";
  });

  userForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const id = parseInt(document.getElementById("id").value);
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const password = document.getElementById("password").value;
    const role = document.querySelector('input[name="role"]:checked').value;

    if (users.some((user) => user.id === id && user.id !== id)) {
      alert("ID already exists.");
      return;
    }

    if (id && users.some((user) => user.id === id)) {
      const index = users.findIndex((user) => user.id === id);
      users[index] = { id, name, email, phone, password, role };
    } else {
      users.push({ id, name, email, phone, password, role });
    }

    localStorage.setItem("users", JSON.stringify(users));
    populateTable();
    userModal.style.display = "none";
  });

  populateTable();
});
