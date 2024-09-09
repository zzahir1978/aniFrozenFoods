// js/main.js
// Load the navbar HTML content
fetch('/css/navbar.html')
  .then(response => response.text())
  .then(data => {
    document.getElementById('navbar-container').innerHTML = data;
  });
