async function showQuestions(subjectName, typeNumber) {
  if(!(await isAuthenticated())) return;

  const app = document.getElementById('app');
  
  app.innerHTML = 
  `
    <div id="selection-block">
      <div id="pagination-block">
        <input type="number" min="1" placeholder="page" id = "page" value="1">
        <select id="size">
          <option value="10">10</option>
          <option value="20">20</option>
          <option value="50">50</option>
        </select>
      </div>

      <div id="topics-dropdown">
        <button id="topics-button" type="button">
          Select topics
        </button>

        <div id="topics-block">
        </div>
      </div>

      <button id="select-button">Select</button>
    </div>

    <div id="questions-block">
    </div>
  `;

  const paginationBlock = document.getElementById("pagination-block");
  const topicsButton = document.getElementById("topics-button");
  const topicsBlock = document.getElementById("topics-block");
  const questionBlock = document.getElementById("questions-block");

  const topics = await fetchTopicsOfTaskType(subjectName, typeNumber);

  topicsButton.addEventListener("click", () => {
      topicsBlock.classList.toggle("open");
  });

  for (const topic of topics) {
    const label = document.createElement("label");

    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.value = topic.id;

    label.appendChild(checkbox);
    label.append(` ${topic.topic}`);

    topicsBlock.appendChild(label);
}

  const selectButton = document.getElementById("select-button");
  const pageInput = paginationBlock.querySelector("#page");
  const sizeSelect = paginationBlock.querySelector("#size");

  selectButton.addEventListener("click", async () => {
    const page = pageInput.value;
    const size = Number(sizeSelect.value);
    const topicIds = [...topicsBlock.querySelectorAll("input[type='checkbox']:checked")].map(checkbox => Number(checkbox.value));

    await loadQuestions(questionBlock, subjectName, typeNumber, page-1, size, topicIds);
  });

  await loadQuestions(questionBlock, subjectName, typeNumber, 0, 10, []);
}

async function fetchTopicsOfTaskType(subjectName, typeNumber) {
  const auth = localStorage.getItem("auth");

  const response = await fetch(
    `api/v1/subjects/${subjectName}/types/${typeNumber}/topics`,
    {
      method: "GET",
      headers: {
        'Authorization' : `Bearer ${auth}`
      }
    }
  );

  if(!response.ok) {
    throw new Error(response.status);
  }

  return response.json();
}

async function loadQuestions(questionBlock, subjectName, typeNumber, page, size, topicIds) {
  questionBlock.innerHTML = "";

  const questions = await fetchQuestionsBySubjectNameAndNumber(subjectName, typeNumber, page, size, topicIds);

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
    `;

    if(question.answerType === 'SHORT') {
      const answerBlock = document.createElement('div');
      answerBlock.innerHTML = 
      `
        <div class='check-answer'>
          <input type='text' class="answer">
          <button class="check-button">Check</button>
          <p class="result"></p>
        </div>
      `;
      div.appendChild(answerBlock);

      
      const answerInput = div.querySelector(".answer");
      const checkButton = div.querySelector(".check-button");
      const result = div.querySelector(".result");

      checkButton.addEventListener("click", async () => {
        const answer = answerInput.value;

        const checkInfo = await checkAnswer(question.id, answer);
        result.innerHTML = checkInfo.status;
      });
    } else {
      const answerBlock = document.createElement('div');
      answerBlock.innerHTML = `This is a self-checking task.`;
      div.appendChild(answerBlock);
    }

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

async function fetchQuestionsBySubjectNameAndNumber(subjectName, typeNumber, page, size, topicIds) {
  const auth = localStorage.getItem("auth");
  
  const params = new URLSearchParams();
  params.append("page", page);
  params.append("size", size);

  for (const topicId of topicIds) {
    params.append("topicIds", topicId);
  }

  const response = await fetch(
    `api/v1/subjects/${subjectName}/types/${typeNumber}/questions?${params}`,
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