async function showMyProfile() {
  if(!(await isAuthenticated())) return;

  const profileInfo = await fetchMyProfileInfo();

  const app = document.getElementById('app');

  app.innerHTML = 
  `
    <div id="profile-page">
      <div id="profile-info">
        <div class="firstname">${profileInfo.firstname}</div>
        <div class="lastname">${profileInfo.lastname}</div>
        <div class="birth-date">Birth date: ${profileInfo.birthDate}</div>
      </div>

      <div id="actions">
        <button>Edit profile</button>
        <button>Change password</button>
        <button id="logout-button" onclick="logout()">Log out</button>
      </div>
    </div>
  `;
}

function logout() {
  localStorage.removeItem("auth");
  showRegistrationPage();
}

async function fetchMyProfileInfo() {
  const auth = localStorage.getItem("auth");

  const response = await fetch(
    'api/v1/profiles/me',
    {
      method: "GET",
      headers: {
        "Authorization" : `Bearer ${auth}`
      }
    }
  );

  if(!response.ok) {
    throw new Error(response.status);
  }

  return await response.json();
}