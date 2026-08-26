async function checkAnswer(questionId, answer, questionReadDto) {
  const response = await fetch(
    `/api/v1/questions/${questionId}/check`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        answer
      })
    }
  );

  if (!response.ok) {
    throw new Error(`HTTP error: ${response.status}`);
  }

  return await response.json();
}