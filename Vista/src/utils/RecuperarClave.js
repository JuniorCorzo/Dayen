import { validarClave } from './procesos/ValidarForm'
function enviarCorreo (email) {
  fetch(`${window.HOST_API}/recuperar_clave?correo=${email}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    }
  }).then((response) => {
    if (response.ok) {
      alert('Correo enviado')
    }
  })
}

if (window.location.pathname === '/recuperar_clave') {
  document.querySelector('#recuperar_clave_form').addEventListener('submit', (e) => {
    e.preventDefault()
    enviarCorreo(document.querySelector('input[name="email"]').value)
  })
}

function cambiarClave (clave) {
  const params = new URLSearchParams(window.location.search)
  fetch(`${window.HOST_API}/recuperar_clave/${params.get('token')}?clave=${clave}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    }
  }).then((response) => {
    if (response.ok) {
      alert('Clave cambiada')
    }
  })
}

console.log(window.location.pathname)
if (window.location.pathname === '/validar-clave') {
  document.querySelector('#cambiar_clave_form').addEventListener('submit', (e) => {
    e.preventDefault()

    if (validarClave(document.querySelector('input[name="clave"]').value) !== true) {
      document.querySelector('error-clave').textContent = validarClave(document.querySelector('input[name="clave"]').value)
      return
    }
    if (document.querySelector('input[name="clave"]').value !== document.querySelector('input[name="confirmar-clave"]').value) {
      document.querySelector('error-confirmar-clave').textContent = 'Las contraseña no coinciden'
      return
    }

    cambiarClave(document.querySelector('input[name="clave"]').value)
  })
}
