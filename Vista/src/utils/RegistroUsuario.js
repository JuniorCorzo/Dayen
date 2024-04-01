import { validarCedula, validarClave, validarCorreo, validarTexto } from './ValidarForm'

function registrarUsuario (data) {
  fetch(`${window.HOST_API}/usuario/create`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data)
      alert(`Se registro el usuario llamado ${data.username}`)
    })
}

function validarCampos (data) {
  let hasError = false
  if (validarCedula(data.idUsuario) !== true) {
    document.querySelector('.cedula-error').innerHTML = validarCedula(data.idUsuario)
    hasError = true
  }

  if (validarTexto(data.nombre) !== true) {
    document.querySelector('.nombre-error').innerHTML = validarTexto(data.nombre)
    hasError = true
  }

  if (validarTexto(data.apellido) !== true) {
    document.querySelector('.apellido-error').innerHTML = validarTexto(data.apellidos)
    hasError = true
  }

  if (validarCorreo(data.correo) !== true) {
    document.querySelector('.correo-error').innerHTML = validarCorreo(data.correo)
    hasError = true
  }

  if (validarClave(data.clave) !== true) {
    const message = validarClave(data.clave)
    document.querySelector('.clave-error').innerHTML = message
    hasError = true
  }

  if (data.clave !== document.querySelector('input[name="confirmar-clave"]').value) {
    console.log('hola')
    document.querySelector('.confirmar-clave-error').innerHTML = 'Las contraseñas no coinciden'
    hasError = true
  }

  return hasError
}

document.querySelector('form').addEventListener('submit', (event) => {
  event.preventDefault()
  const data = {
    idUsuario: document.querySelector('input[name="cedula"]').value,
    nombre: document.querySelector('input[name="nombre"]').value,
    apellido: document.querySelector('input[name="apellido"]').value,
    rol: 'ADMINISTRADOR',
    correo: document.querySelector('input[name="correo"]').value,
    clave: document.querySelector('input[name="clave"]').value
  }

  if (!validarCampos(data)) {
    registrarUsuario(data)
  }
})
