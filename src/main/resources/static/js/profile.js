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
        <button id="edit-profile-button">Edit profile</button>
        <button id="change-password-button">Change password</button>
        <button id="logout-button" onclick="logout()">Log out</button>
      </div>
    </div>
  `;

  const editProfileButton = document.getElementById("edit-profile-button");
  const editPasswordButton = document.getElementById('change-password-button');

  editPasswordButton.addEventListener("click", async () => {
    await showEditPasswordBlock();
  });

  editProfileButton.addEventListener("click", async () => {
    await showEditProfilePage();
  });
}

async function showEditPasswordBlock() {
  if(!(await isAuthenticated())) return;

  const oldBlock = document.querySelector('.change-password-block');

  if (oldBlock) {
    oldBlock.remove();
    return;
  }

  const div = document.createElement('div');
  div.classList.add('change-password-block');

  const currentPasswordInput = document.createElement('input');
  currentPasswordInput.type = 'password';
  currentPasswordInput.placeholder = 'Current password';

  const newPasswordInput = document.createElement('input');
  newPasswordInput.type = 'password';
  newPasswordInput.placeholder = 'New password';

  const submitButton = document.createElement('button');
  submitButton.innerHTML = 'Change';

  submitButton.addEventListener("click", async () => {
    const currentPassword = currentPasswordInput.value;
    const newPassword = newPasswordInput.value;

    const res = await changePassword(currentPassword, newPassword);

    if(res) {
      logout();
    }
  });  
  
  div.appendChild(currentPasswordInput);
  div.appendChild(newPasswordInput);
  div.appendChild(submitButton);
  document.getElementById('app').appendChild(div);

}

async function changePassword(currentPassword, newPassword) {
  const auth = localStorage.getItem('auth');

  const response = await fetch(
    'api/v1/users/change-password',
    {
      method: 'PATCH',
      headers: {
        'Content-Type' : 'application/json',
        'Authorization' : `Bearer ${auth}`
      },
      body: JSON.stringify({
        currentPassword,
        newPassword
      })
    }
  );

  if(!response.ok) {
    return false;
  }

  return true;
}

async function showEditProfilePage() {
  if(!(await isAuthenticated())) return;
  
  const app = document.getElementById("app");
  app.innerHTML = 
  `
    <div>
      <label for="firstname">Firstname</label>
      <input type="text" id="firstname">

      <label for="lastname">Lastname</label>
      <input type="text" id="lastname">

      <label for="birth-date">Birth date</label>
      <input type="date" id="birth-date">

      <button id="profile-edit-submit-button">Submit</button>
    </div>
  `;
  const profileSubmit = document.getElementById("profile-edit-submit-button");
  profileSubmit.addEventListener("click", async () => {
    const firstname = document.getElementById("firstname").value || null;
    const lastname = document.getElementById("lastname").value || null;
    const birthDate = document.getElementById("birth-date").value || null;

    const profileReadDto = await patchProfile({firstname, lastname, birthDate});
    console.log(profileReadDto);
    showMyProfile();
  });
}

async function patchProfile(profileInfo) {
  const auth = localStorage.getItem("auth");

  const response = await fetch(
    'api/v1/profiles/me',
    {
      method: 'PATCH',
      headers: {
        "Content-Type" : 'application/json',
        'Authorization' : `Bearer ${auth}`
      },
      body: JSON.stringify(profileInfo)
    }
  );

  if(!response.ok) {
    throw new Error(response.status);
  }

  return await response.json();
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