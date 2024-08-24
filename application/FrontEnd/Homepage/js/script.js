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

var slideUp = {
  distance: "150%",
  origin: "bottom",
  opacity: null,
};

ScrollReveal().reveal(".scrollreveal", slideUp);

document.addEventListener("DOMContentLoaded", () => {
  const admin = {
    name: "Bilal Sajid",
    email: "bilal.admin@gmail.com",
    password: "admin123",
    id: 101,
    role: "ADMIN",
  };

  localStorage.setItem("adminUser", JSON.stringify(admin));
});
