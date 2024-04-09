import { pushData, deleteData } from '../FetchData'
import actualizarTabla from './Procesos'

function modificarProceso (data) {
  pushData('/proceso/update', data, 'PUT').then((res) => {
    alert('Proceso modificado con éxito')
    actualizarTabla()
  })
}

function validacionForm (data) {
  if (data.idTipo === '') {
    document.getElementById('tipo_error_message').innerHTML = 'Seleccione un tipo de proceso'
    return false
  }

  if (data.realizadoEn === '') {
    document.getElementById('calen_error_message').innerHTML = 'Ingrese la fecha del proceso'
    return false
  }

  if (data.descripcion === '') {
    document.getElementById('descripcion_error_message').innerHTML = 'Ingrese la descripción del proceso'
    return false
  }
  return true
}

document.querySelector('#modificar_form').addEventListener('submit', (e) => {
  e.preventDefault()

  const data = {
    idProceso: parseInt(document.querySelector('.modificar-proceso').getAttribute('data-id-proceso')),
    idLote: parseInt(new URLSearchParams(window.location.search).get('idLote')),
    idTipo: document.querySelector('.select-tipo').value,
    idProducto: [],
    idPersonal: [],
    descripcion: document.querySelector('.descripcion').value,
    realizadoEn: `${document.querySelector('.calen').value}`
  }

  document.querySelectorAll('.productos-values .choices__item').forEach((producto) => {
    data.idProducto.push(parseInt(producto.getAttribute('data-value')))
  })

  document.querySelectorAll('.personal-values .choices__item').forEach((personal) => {
    data.idPersonal.push(parseInt(personal.getAttribute('data-value')))
  })

  if (validacionForm(data)) modificarProceso(data)
})

function eliminarProceso (idProceso) {
  deleteData(`/proceso/delete/${idProceso}`).then((res) => {
    actualizarTabla()
    alert('Proceso eliminado con éxito')
  })
}

document.querySelector('#eliminar_boton').addEventListener('click', (e) => {
  eliminarProceso(e.target.getAttribute('data-id-proceso'))
})
