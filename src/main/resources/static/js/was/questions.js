async function fetchQuestionsByTaskTypeId(typeId) {
  const response = await fetch(`api/v1/types/${typeId}/questions`);

  if(!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  return await response.json();
}

async function fetchQuestionsByTaskTypeIdPageable(typeId, size, page) {
  const response = await fetch(`api/v1/types/${typeId}/questions?page=${page}&size=${size}`);

  if(!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  return await response.json();
}