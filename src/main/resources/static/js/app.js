function startApp() {
  const auth = localStorage.getItem('auth');

  if(auth == null) {
    showRegistrationPage();
  }

  showMainPage();
}

startApp();