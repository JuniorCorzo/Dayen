import { pushData } from '../FetchData'
import { updatePersonalSession } from '../../config/ManageSessionStorage'

const postData = (data) => {
  pushData('/personal/create', data).then((data) => {
    updatePersonalSession()
    alert(`Se registro el trabajador llamado ${data.nombre}`)
  })
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

  postData(data)
})
