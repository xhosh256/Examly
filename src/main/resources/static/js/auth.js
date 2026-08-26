function showRegistrationPage() {
  const app = document.getElementById('app');

  app.innerHTML = 
  `
    <h2>Registration</h2>

    <div class="registration-page">
      <label for="username-input">Username</label>
      <input id="username-input" type="text">

      <label for="password-input">Password</label>
      <input id="password-input" type="password">

      <button id="register-button">Registration</button>

      <a class="login-link">Log in</a>
    </div>
  `;

  const registerButton = document.getElementById("register-button");
  const loginLink = document.querySelector(".login-link");

  const usernameInput = document.getElementById('username-input');
  const passwordInput = document.getElementById('password-input');

  registerButton.addEventListener("click", async () => {
    const username = usernameInput.value;
    const password = passwordInput.value;

    await register({username, password});
  });

  loginLink.addEventListener("click", () => {
    showLoginPage();
  });
}

async function register(registerDto) {
  const response = await fetch(
    "api/v1/auth/register",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(registerDto)
    }
  );

  if(!response.ok) {
    throw new Error(response.status);
  }

  console.log(await response.json());
  showLoginPage();
}

function showLoginPage() {
  const app = document.getElementById('app');

  app.innerHTML = 
  `
    <h2>Log in</h2>

    <div class="login-page">
      <label for="username-input">Username</label>
      <input id="username-input" type="text">

      <label for="password-input">Password</label>
      <input id="password-input" type="password">

      <button id="login-button">Login</button>

      <a class="register-link">Register</a>
    </div>
  `;

  const loginButton = document.getElementById("login-button");
  const registerLink = document.querySelector(".register-link");

  const usernameInput = document.getElementById('username-input');
  const passwordInput = document.getElementById('password-input');

  loginButton.addEventListener("click", async () => {
    const username = usernameInput.value;
    const password = passwordInput.value;

    await login({username, password});
  });

  registerLink.addEventListener("click", () => {
    showRegistrationPage();
  });
}

async function login(loginDto) {
  const response = await fetch(
    "api/v1/auth/login",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(loginDto)
    }
  );

  if(!response.ok) {
    throw new Error(response.status);
  }

  console.log(response);

  const auth = await response.text();
  localStorage.setItem("auth", auth);

  showMainPage();
}