async function FetchData(url) {
  const response = await fetch(`${window.HOST_API}${url}`, {
    method: "GET",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("jwt")}`,
    },
  });

  if (!response.ok) throw Error(response.statusText);

  const data = await response.json();
  return data;
}

export default FetchData;
