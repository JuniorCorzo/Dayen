import { pushData } from '../FetchData'
import { getCookie, updateLoteSession } from '../../config/ManageSessionStorage'

function registraLote (data, target) {
  pushData('/lotes/create', data)
    .then((data) => {
      guardarImagen(target, data.tituloImagen.concat(data.idLote))
      updateLoteSession()
      alert(`Se registro el lote llamado ${data.nombre}`)
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

function validacionForm (data) {
  if (document.querySelector('input[name="tituloImagen"]').files.length === 0) {
    document.getElementById('file_error_message').innerHTML = 'Ingrese una imagen'
    return false
  }

  if (data.nombre === '') {
    document.getElementById('nombre_error_message').innerHTML = 'Ingrese el nombre del lote'
    return false
  }

  if (data.hectareas === '') {
    document.getElementById('hectareas_error_message').innerHTML = 'Ingrese las hectareas del lote'
    return false
  }
  return true
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

  if (validacionForm(data)) registraLote(data, e.target)
})

export { guardarImagen }
