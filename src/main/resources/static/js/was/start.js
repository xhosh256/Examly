function startApp() {
  const app = document.getElementById("app");
  app.innerHTML = 
  `
    <div>
      Добро пожаловать в Examly
    </div>
    <label for="subject">Предмет:</label>

    <select id="subject" name="subject">
        <option value="">...</option>
        <option value="PROFILE_MATH">Профильная математика</option>
        <option value="BASE_MATH">Базовая математика</option>
    </select>

    <button id="select-subject">Выбрать</button>
  `;

  const selectButton = document.getElementById('select-subject');


  selectButton.addEventListener("click", () => {
    const subjectName = document.getElementById("subject").value;
    showSubjectInfo(app, subjectName)
  })
}

showRegistrationPage();