const changeUsername = (usuario) => {
  if (usuario === null) {
    setTimeout(() => {
      usuario = JSON.parse(sessionStorage.getItem("usuario"));
      changeUsername(usuario);
    });
    return;
  }

  let username = document.querySelector(".username");
  username.innerHTML = `Hola, ${usuario.nombre} ${usuario.apellido}🌱`;
};

let usuario = JSON.parse(sessionStorage.getItem("usuario"));
changeUsername(usuario);

setTimeout(() => {
  const data = JSON.parse(sessionStorage.getItem("lotes"))
    .map((data) => {
      return `
      <div>
        <div class="p-3">
            <a href="/procesos?idLote=${data.idLote}">
                <h3>Lote ${data.idLote}</h3>
                <img class="IMG-27 mt-4" src="/img/IMG-27.jpg" alt="img">
            </a>
        </div>
      </div>
              `;
    })
    .join("\n");

  if (!data) {
    document.querySelector(".contenedor-lotes").innerHTML = `
    <div class="p-3">
        <h3>No hay lotes</h3>
    </div>
    `;
    return;
  }
  document.querySelector(".contenedor-lotes").innerHTML = data;
}, 300);
