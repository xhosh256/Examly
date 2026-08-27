async function showQuestions(subjectName, typeNumber) {
  if(!isAuthenticated()) return;

  const app = document.getElementById('app');
  
  app.innerHTML = 
  `
    <div id="pagination-block">
      <input type="number" min="1" placeholder="page number..." id = "page" value="1">
      <select id="size">
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="50">50</option>
      </select>
      <button id="pagination-select-button">Select</button>
    </div>
    <div id="questions-block">
    </div>
  `;

  const paginationBlock = document.getElementById("pagination-block");
  const questionBlock = document.getElementById("questions-block");

  const paginationSelectButton = paginationBlock.querySelector("#pagination-select-button");
  const pageInput = paginationBlock.querySelector("#page");
  const sizeSelect = paginationBlock.querySelector("#size");

  paginationSelectButton.addEventListener("click", async () => {
    const page = pageInput.value;
    const size = Number(sizeSelect.value);

    await loadQuestions(questionBlock, subjectName, typeNumber, page-1, size);
  });

  await loadQuestions(questionBlock, subjectName, typeNumber, 0, 10);
}

async function loadQuestions(questionBlock, subjectName, typeNumber, page, size) {
  if (!(page >= 0)) return;

  questionBlock.innerHTML = "";

  const questions = await fetchQuestionsBySubjectNameAndNumber(subjectName, typeNumber, page, size);

  const totalElements = questions.totalElements;
  const totalPages = questions.totalPages;

  const elementsInfo = document.createElement("div");
  elementsInfo.innerHTML = `Total questions: ${totalElements}`;

  const pagesInfo = document.createElement("div");
  pagesInfo.innerHTML = `Page: ${page+1}/${totalPages}`;
  questionBlock.appendChild(elementsInfo);
  questionBlock.appendChild(pagesInfo);

  for(const question of questions.content) {
    const div = document.createElement("div");
    div.innerHTML = 
    `
      <p class="question">${question.question}</p>
      <img src="${question.imageUrl == null ? '' : question.imageUrl}">
      <div class='check-answer'>
        <input type='text' class="answer">
        <button class="check-button">Check</button>
        <p class="result"></p>
      </div>
    `;

    const answerInput = div.querySelector(".answer");
    const checkButton = div.querySelector(".check-button");
    const result = div.querySelector(".result");

    checkButton.addEventListener("click", async () => {
      const answer = answerInput.value;

      const checkInfo = await checkAnswer(question.id, answer);
      result.innerHTML = checkInfo.status;
    });

    questionBlock.appendChild(div);
  }
}

async function checkAnswer(questionId, answer) {
  const auth = localStorage.getItem("auth");

  const response = await fetch(
    `api/v1/questions/${questionId}/check`,
    {
      method: "POST",
      headers: {
        "Authorization" : `Bearer ${auth}`,
        "Content-Type" : "application/json"
      },
      body: JSON.stringify({
        answer
      })
    }
  );

  if(!response.ok) {
    throw new Error(response.status);
  }

  return await response.json();
}

async function fetchQuestionsBySubjectNameAndNumber(subjectName, typeNumber, page, size) {
  const auth = localStorage.getItem("auth");

  const response = await fetch(
    `api/v1/subjects/${subjectName}/types/${typeNumber}/questions?page=${page}&size=${size}`,
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