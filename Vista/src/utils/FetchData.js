async function getData(url) {
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

export function pushData(url, data, method = "POST") {
  return fetch(`${window.HOST_API}${url}`, {
    method: method.toUpperCase(),
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${sessionStorage.getItem("jwt")}`,
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      return response.json();
    })
    .catch((err) => {
      throw Error(err);
    });
}

export default getData;
