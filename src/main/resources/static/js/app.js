async function startApp() {
  const auth = localStorage.getItem('auth');

  if(auth == null || await isTokenExpired(auth)) {
    showRegistrationPage();
  }

  showMainPage();
}

async function isTokenExpired(auth) {
  const response = await fetch('api/v1/auth/ping');

  if(!response.ok) {
    return false;
  }

  return true;
}

function isAuthenticated() {
  return localStorage.getItem("auth") != null;
}

startApp();