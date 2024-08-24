function loadUserData() {
  const users = JSON.parse(localStorage.getItem("users")) || [];
  const adminUser = JSON.parse(localStorage.getItem("adminUser"));

  const validateAdminUser = validateAdmin(adminUser);
  if (!validateAdminUser) {
    const enteredEmail = document.getElementById("email").value;
    const enteredPassword = document.getElementById("password").value;
    for (let i = 0; i < users.length; i++) {
      const email = users[i].email;
      const password = users[i].password;
      const role = users[i].role;

      if (email === enteredEmail && password === enteredPassword) {
        localStorage.setItem("loggedInUser", JSON.stringify(users[i]));
        redirectToRolePage(users[i].role);
        break;
      }
    }
  }
}

const validateAdmin = (adminUser) => {
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  if (email === adminUser.email && password === adminUser.password) {
    localStorage.setItem("loggedInUser", JSON.stringify(adminUser));
    window.location.href = "/application/FrontEnd/AdminPage/admin.html";
    return true;
  } else {
    alert("Something went wrong");
  }
  return false;
};

// Function to redirect the user to the appropriate page based on their role
function redirectToRolePage(role) {
  console.log("Redirecting to role:", role); // Debugging output
  if (role === "MANAGER") {
    window.location.href = "/application/FrontEnd/ManagerPage/index.html";
  } else if (role === "MEMBER") {
    window.location.href = "/application/FrontEnd/MemberPage/member.html";
  } else {
    alert("Unknown role");
  }
}

// Event listener for form submission
document.querySelector("form").addEventListener("submit", function (event) {
  event.preventDefault(); // Prevent the form from submitting normally
  loadUserData(); // Load and validate user data
});
