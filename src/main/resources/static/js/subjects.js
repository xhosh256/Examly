async function fetchAllSubjects() {
  const auth = localStorage.getItem("auth");

  const response = await fetch("api/v1/subjects",
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

  return await response.json();
}