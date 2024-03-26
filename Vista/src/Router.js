const express = require("express");
const path = require("path");
const app = express();

function route(url, page) {
  app.use(express.static(path.join(__dirname, "assets")));
  app.use(express.static(path.join(__dirname, "config")));
  app.use(express.static(path.join(__dirname, "utils")));

  app.get(url, (req, res) => {
    res.sendFile(path.join(__dirname, page));
  });
}

route("/", "index.html");
route("/inicio", "pages/modulo_lotes.html");
route("/login", "pages/inicio_sesion.html");
route("/registrarse", "pages/registros/registro_usuario.html");
route("/recuperar_clave", "pages/registros/recuperar_contraseña.html");
route("/registro/lote", "pages/registros/registro_hectareas.html");
route("/registro/proceso", "pages/registros/registro_procesos.html");
route("/registro/personal", "pages/registros/registro_personal.html");
route("/procesos", "pages/modulo_procesos_lotes.html");

app.listen(5050);
console.log("Server is running on port 5050");
