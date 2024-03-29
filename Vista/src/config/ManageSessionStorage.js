import FetchData from "../utils/FetchData";

function generalSessionStorage() {
  const usuario = sessionStorage.getItem("usuario");
  if (!usuario) {
    sessionStorage.setItem("jwt", getCookie("jwt"));
    FetchData(`/usuario/${getCookie("userId")}`).then((data) => {
      console.log(data);
      sessionStorage.setItem(
        "usuario",
        JSON.stringify({
          id: data.id,
          nombre: data.username,
          apellido: data.apellido,
          email: data.correo,
          rol: data.rol,
        })
      );

      sessionStorage.setItem("personal", JSON.stringify(data.personal));
      sessionStorage.setItem("lotes", JSON.stringify(data.lote));
    });
  }
}

function updateUsuarioSession() {
  const usuario = sessionStorage.getItem("usuario");
  if (usuario) {
    FetchData(`/usuario/${getCookie(userId)}`).then((data) => {
      sessionStorage.setItem(
        "usuario",
        JSON.stringify({
          id: data.id,
          nombre: data.username,
          apellido: data.apellido,
          email: data.correo,
          rol: data.rol,
        })
      );
    });
  }
}

function updatePersonalSession() {
  const personal = sessionStorage.getItem("personal");
  if (personal) {
    FetchData(`/personal/${getCookie(userId)}`).then((data) => {
      sessionStorage.setItem("personal", JSON.stringify(data.personal));
    });
  }
}

function updateLoteSession() {
  const lote = sessionStorage.getItem("lotes");
  if (lote) {
    FetchData(`/lotes/${getCookie(userId)}`).then((data) => {
      sessionStorage.setItem("lotes", JSON.stringify(data.lote));
    });
  }
}

function getCookie(name) {
  return document.cookie
    .split("; ")
    .find((row) => row.startsWith(name))
    .split("=")[1];
}

export {
  generalSessionStorage,
  updateUsuarioSession,
  updatePersonalSession,
  updateLoteSession,
};
