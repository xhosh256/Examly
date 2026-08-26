function showRegistrationPage() {
  const app = document.getElementById("app");

  app.innerHTML = 
  `
    <p>REGISTER NOW!</p>
    <label for="username">Username</label>
    <input id="username" type="text">

    <label for="password">Password</label>
    <input id="password" type="text">

    <button class="register-button">Register</button>
    <a id="show-login-page">Log in now</a>
  `;

  const registerButton = document.querySelector('.register-button');
  const username = document.getElementById('username');
  const password = document.getElementById('password');
  const showLoginLink = document.getElementById('show-login-page');

  showLoginLink.addEventListener("click", () => {
    showLoginPage();
  })

  registerButton.addEventListener("click", async () => {
    const usernameValue = username.value;
    const passwordValue = password.value;

    await register(usernameValue, passwordValue);
  })
}

async function register(username, password) {
  const response = await fetch(
    "/api/v1/auth/register",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username,
        password
      })
    }
  );

  if (!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  const userReadDto = await response.json();

  console.log(userReadDto);
  showLoginPage();
}

function showLoginPage() {
  const app = document.getElementById("app");

  app.innerHTML = 
  `
    <p>LETS LOG IN</p>
    <label for="username">Username</label>
    <input id="username" type="text">

    <label for="password">Password</label>
    <input id="password" type="text">

    <button class="login-button">Log In</button>
  `;

  const loginButton = document.querySelector('.login-button');
  const username = document.getElementById('username');
  const password = document.getElementById('password');

  loginButton.addEventListener("click", async () => {
    const usernameValue = username.value;
    const passwordValue = password.value;

    await login(usernameValue, passwordValue);
  })
}

async function login(username, password) {
  const response = await fetch(
    "/api/v1/auth/login",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username, password
      })
    }
  );

  if (!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  startApp();
}