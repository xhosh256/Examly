async function showTaskTypeInfo(app, subjectInfo, taskTypeNumber) {
  const typeInfo = await fetchTaskTypeInfo(subjectInfo.subjectName, taskTypeNumber);
  
  app.innerHTML = 
  `
    Type. ${taskTypeNumber}, Subject. ${subjectInfo.subjectName}

    <div>
      <label for='page-number'>Page</label>
      <input id='page-number' type='number' contentholder>

      <label for='page-size'>Size</label>
      <input id='page-size' type='number' contentholder>

      <button id='pageable-button'>OK</button>
    </div>

    <div id="questions">
    </div>
  `;

  const pageNumber = document.getElementById('page-number');
  const pageSize = document.getElementById('page-size');
  const pageButton = document.getElementById('pageable-button');

  pageButton.addEventListener("click", async () => {
    const pageNumberValue = Number(pageNumber.value);
    const pageSizeValue = Number(pageSize.value);

    await loadQuestions(
      typeInfo,
      pageNumberValue,
      pageSizeValue
    );
  });

  await loadQuestions(typeInfo, 0, 5);
  
}

async function loadQuestions(typeInfo, pageSizeValue, pageNumberValue) {
  const questionsPage = await fetchQuestionsByTaskTypeIdPageable(typeInfo.id, pageNumberValue, pageSizeValue);

  const questions = document.getElementById('questions');

  questions.innerHTML  = '';

  for(const question of questionsPage.content) {
    const div = document.createElement("div");


    div.innerHTML = 
    `
      <div>${question.question}</div>
      <img src="${question.imageUrl == null ? '' : question.imageUrl}">
      <input type='text' class="input-text">
      <button class="submit-button">Submit</button>
      <div class='result'></div>
    `;

    const input = div.querySelector('.input-text');
    const checkButton = div.querySelector('.submit-button');
    const result = div.querySelector('.result');

    checkButton.addEventListener("click", async () => {
      const res = await checkAnswer(question.id, input.value, question);
      result.innerHTML = res.status;
    })

    questions.appendChild(div);
  }
}

async function fetchTaskTypeInfo(subject, type) {
  const response = await fetch(`api/v1/subjects/${subject}/types/${type}`);

  if(!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  return await response.json();
}