import { pushData } from './FetchData'
import { getCookie, updateLoteSession } from '../config/ManageSessionStorage'

function registraLote (data, target) {
  pushData('/lotes/create', data)
    .then((data) => {
      console.log(data)
      guardarImagen(target, data.tituloImagen.concat(data.idLote))
      alert(`Se registro el lote llamado ${data.nombre}`)
      updateLoteSession()
    })
}

function guardarImagen (target, tituloImagen) {
  const formData = new FormData(target)
  formData.append('tituloImagen', tituloImagen)
  fetch('/subir-imagen', {
    method: 'POST',
    body: formData
  })
    .then((response) => {
      if (response.ok) {
        console.log('Imagen subida')
      }
    })
}

document.querySelector('form').addEventListener('submit', (e) => {
  e.preventDefault()
  const data = {
    idUsuario: getCookie('userId'),
    nombre: document.querySelector('input[name="nombre-lote"]').value,
    tituloImagen: `userId-${getCookie('userId')}-idLote-`,
    hectareas: document.querySelector('input[name="hectareas"]').value,
    fase: 'Preparacion'
  }
  registraLote(data, e.target)
})
