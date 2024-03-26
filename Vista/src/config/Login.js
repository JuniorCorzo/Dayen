class Login {
  constructor(form, fields) {
    this.form = form;
    this.fields = fields;

    this.validateSubmit();
  }
  // Validad las credenciales del usuario
  validateSubmit() {
    let self = this;
    let isUnauthorized = false;

    this.form.addEventListener("submit", function (e) {
      e.preventDefault();
      let error = 0;
      let data = {};

      //Todo:: Validate fields
      self.fields.forEach((field) => {
        const input = document.querySelector(`#${field}`);
        data[field] = input.value;
      });

      if (error === 0) {
        const TOKEN = "";
        fetch(`${window.HOST_API}/auth/login`, {
          method: "POST",
          headers: {
            "content-type": "application/json",
          },
          body: JSON.stringify(data),
        })
          .then((response) => {
            if (response.status === 401) isUnauthorized = true;
            return response.json();
          })
          .then((data) => {
            if (isUnauthorized) {
              self.innerMessageUnauthorized(data.message);
              isUnauthorized = false;
              return;
            }
            self.createCookie(data.jwt);
          });
      }
    });
  }

  /*Cuando son validas las credenciales dependiendo de la opción
   de recordar se configura la cookie con tiempo de expiración*/
  createCookie(jwt, userId) {
    window.location.replace("/inicio");
    if (document.querySelector('input[name="recordar"]').checked) {
      let date = new Date();
      date.setDate(new Date().getDate() + 90);
      document.cookie = `jwt=${jwt}; expires=${date.toUTCString()}; SameSite=Strict; path=/`;
      document.cookie = `userId=${userId}; expires=${date.toUTCString()}; SameSite=Strict; path=/`;
      return;
    }
    
    document.cookie = `jwt=${jwt}; SameSite=Strict; path=/`;
    document.cookie = `userId=${userId}; SameSite=Strict; path=/`;
  }

  /**
   * Si la credenciales son incorrectas se muestra un mensaje
   * @param {*} message
   */
  innerMessageUnauthorized(message) {
    const span = document.querySelector(".message-error");
    document.querySelector(".container-message").classList.remove("d-none");
    span.innerHTML = message;
  }
}

const form = document.querySelector(".login-form");
if (form) {
  const fields = ["idUsuario", "password"];
  new Login(form, fields);
}
