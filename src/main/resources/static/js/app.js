async function startApp() {
  const auth = localStorage.getItem('auth');

  if(auth == null || await isTokenExpired(auth)) {
    showRegistrationPage();
  }

  showMainPage();
}

async function isTokenExpired(auth) {
  const response = await fetch(
    'api/v1/auth/ping',
    {
      method: "GET",
      headers: {
        "Authorization" : `Bearer ${auth}`
      }
    }
  );

  return !response.ok;
}

async function isAuthenticated() {
  const auth = localStorage.getItem('auth');

  if(auth == null || await isTokenExpired(auth)) {
    return false;
  }

  return true;
}

startApp();