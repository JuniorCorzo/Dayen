import { pushData, deleteData } from '../FetchData'
import { getCookie, updateLoteSession } from '../../config/ManageSessionStorage'

const guardarImagen = (target, tituloImagen) => {
  const formData = new FormData(target)
  formData.append('tituloImagen', tituloImagen)
  fetch('/subir-imagen', {
    method: 'POST',
    body: formData
  })
    .then((response) => {
      if (response.ok) {
        window.location.reload()
        console.log('Imagen subida')
      }
    })
}

const modificaLote = (data, target) => {
  pushData('/lotes/update', data, 'PUT')
    .then((data) => {
      if (document.querySelector('input[name="tituloImagen"]').files.length > 0) {
        console.log(data.tituloImagen)
        guardarImagen(target, `${data.tituloImagen}${data.idLote}`)
        document.querySelector('input[type="file"]').value = ''
      }
      updateLoteSession()
    })
}

document.querySelector('#modificar_form').addEventListener('submit', (e) => {
  e.preventDefault()

  const { idLote, tituloImagen, fase } = document.querySelector('#modificar_modal').dataset
  const data = {
    idUsuario: getCookie('userId'),
    idLote,
    nombre: document.querySelector('input[name="nombre-lote"]').value,
    tituloImagen,
    hectareas: document.querySelector('input[name="hectareas"]').value,
    fase
  }
  console.log(tituloImagen)
  modificaLote(data, e.target)
})

const eliminarLote = (idLote) => {
  deleteData(`/lotes/delete/${idLote}`).then((response) => {
    if (response.ok) {
      alert('Se elimino el lote')
      updateLoteSession()
    }
  }
  )
}

document.querySelector('#eliminar_lote').addEventListener('click', (e) => {
  eliminarLote(e.target.dataset.idLote)
})

setTimeout(() => {
  document.querySelectorAll('.modificar-lote').forEach((button) => {
    button.addEventListener('click', () => {
      const { idLote, nombre, tituloImagen, hectareas, fase } = button.dataset
      document.querySelector('input[name="nombre-lote"]').value = nombre
      document.querySelector('input[name="hectareas"]').value = hectareas

      const modificarModal = document.querySelector('#modificar_modal')
      modificarModal.setAttribute('data-id-lote', idLote)
      modificarModal.setAttribute('data-titulo-imagen', tituloImagen)
      modificarModal.setAttribute('data-fase', fase)
    })

    document.querySelectorAll('.eliminar-lote').forEach((button) => {
      button.addEventListener('click', (event) => {
        document.querySelector('#eliminar_lote').setAttribute('data-id-lote', event.target.dataset.idLote)
      })
    })
  })
}, 100)
