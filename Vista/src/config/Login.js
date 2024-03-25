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
              self.innerMesaggeUnauthorized(data.message);
              isUnauthorized = false;
              return;
            }
            self.authorize(data.jwt);
          });
      }
    });
  }

  /*Cuando son validas las credenciales dependiendo de la opcion
   de recordar se configura la cookie con tiempo de expiracion*/
  authorize(jwt) {
    if (document.querySelector('input[name="recordar"]').checked) {
      let date = new Date();
      date.setDate(new Date().getDate() + 90);
      document.cookie = `jwt=${jwt}; expires=${date.toUTCString()}; path=/`;
      window.location.href = "modulo_lotes.html";
      return;
    }

    document.cookie = `jwt=${jwt}; path=/`;
    window.location.href = "modulo_lotes.html";
  }

  /**
   * Si la credenciales son incorrectas se muestra un mensaje
   * @param {*} message
   */
  innerMesaggeUnauthorized(message) {
    const span = document.querySelector(".message-error");
    document.querySelector(".container-message").classList.remove("d-none");
    span.innerHTML = message;
  }
}

const form = document.querySelector(".login-form");
if (form) {
  const fields = ["username", "password"];
  new Login(form, fields);
}
