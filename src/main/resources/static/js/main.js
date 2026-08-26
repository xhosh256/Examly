async function showMainPage() {
  const app = document.getElementById('app');
  const subjects = await fetchAllSubjects();

  app.innerHTML = 
  `
    <div class='main-page'>
      <h2>Examly</h2>

      <div class="switch-bar">
      </div>

      <div class="type-options">
      </div>
    </div>
  `;

  const switchBar = document.querySelector('.switch-bar');
  const typeOptions = document.querySelector('.type-options');

  let index = 0;
  for(const subject of subjects) {
    const button = document.createElement('button');
    button.classList.add('switch-btn');
    button.value = subject.subjectName;
    button.textContent = subject.subjectName

    if(index == 0) {
      button.classList.add('active');
      renderTypes(typeOptions, subject.taskTypes);
    }

    index++;
    switchBar.appendChild(button);

    button.addEventListener('click', () => {
      renderTypes(typeOptions, subject.taskTypes);
    })
  }



  document.querySelectorAll('.switch-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelector('.switch-btn.active').classList.remove('active');
      btn.classList.add('active');
    });
  });


}

function renderTypes(typeOptions, taskTypes) {
  typeOptions.innerHTML = '';
  for(const type of taskTypes) {
    const button = document.createElement('button');

    button.textContent = type.number;
    button.value = type.number;

    typeOptions.appendChild(button);

    button.addEventListener('click', () => {
      showQuestions();
    })
  }
}