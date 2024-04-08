import { pushData, deleteData } from '../FetchData'
import { getCookie } from '../../config/ManageSessionStorage'
import { recargarTabla } from './Personal'

const modificarData = (data) => {
  pushData('/personal/update', data, 'PUT')
    .then((data) => {
      if (data && !data.message) {
        recargarTabla()
        alert('Información del trabajador modificado correctamente')
        return
      }

      alert('Ocurrió un problema vuelve a intentar, por favor')
    })
}

document.querySelector('#modificar_form').addEventListener('submit', (e) => {
  e.preventDefault()
  const data = {
    idPersonal: parseInt(e.target.dataset.idPersonal),
    idUsuario: getCookie('userId'),
    nombre: document.querySelector('input[name="nombre"]').value,
    telefono: document.querySelector('input[name="numero"]').value
  }

  modificarData(data)
})

const eliminarPersonal = (idPersonal) => {
  deleteData(`/personal/delete/${idPersonal}`).then(() => {
    recargarTabla()
    alert('Se elimino el trabajador correctamente')
  })
}

document.querySelector('#eliminar_boton').addEventListener('click', (e) => {
  eliminarPersonal(e.target.dataset.idPersonal)
})
