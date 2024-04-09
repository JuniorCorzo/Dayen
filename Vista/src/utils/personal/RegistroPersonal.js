import { pushData } from '../FetchData'
import { updatePersonalSession } from '../../config/ManageSessionStorage'

const postData = (data) => {
  pushData('/personal/create', data).then((data) => {
    updatePersonalSession()
    alert(`Se registro el trabajador llamado ${data.nombre}`)
  })
}

function validacionForm (data) {
  if (data.nombre === '') {
    document.getElementById('nombre_error_message').innerHTML = 'Ingrese el nombre del trabajador'
    return false
  }

  if (data.telefono === '') {
    document.getElementById('numero_error_message').innerHTML = 'Ingrese el número de teléfono'
    return false
  }

  if (data.telefono.length !== 10) {
    document.getElementById('numero_error_message').innerHTML = 'Ingrese un número de teléfono válido'
    return false
  }
  return true
}

const form = document.querySelector('form')
form.addEventListener('submit', (event) => {
  event.preventDefault()
  const data = {
    idUsuario: document.cookie
      .split('; ')
      .find((row) => row.startsWith('userId'))
      .split('=')[1],
    nombre: document.querySelector('input[name="nombre"]').value,
    telefono: document.querySelector('input[name="numero"]').value
  }

  if (validacionForm(data)) postData(data)
})
