async function showSubjectInfo(app, subjectName) {
  const subjectInfo = await fetchSubjectInfo(subjectName);

  app.innerHTML = 
  `
    ${subjectInfo.subjectName}
    Select type:
    <select id="task-type-select">
      <option value="">Выберите тип задания</option>
    </select>

    <button id="select-type">Select</button>
  `;

  const select = document.getElementById("task-type-select");
  const taskTypes = subjectInfo.taskTypes;

  for(const type of taskTypes) {
    const option = document.createElement("option");

    option.value = type.number;
    option.textContent = `${type.number}. ${type.name}`;

    select.appendChild(option);
  }

  const button = document.getElementById("select-type");

  button.addEventListener("click", () => {
    const taskTypeNumber = document.getElementById("task-type-select").value;
    showTaskTypeInfo(app, subjectInfo, taskTypeNumber);
  })
}

async function fetchSubjectInfo(subjectName) {
  const response = await fetch("/api/v1/subjects/" + subjectName);

  if (!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  const subjectInfo = await response.json();
  return subjectInfo;
}