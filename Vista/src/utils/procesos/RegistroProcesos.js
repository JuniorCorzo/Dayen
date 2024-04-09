import { pushData } from '../FetchData'
import insertsSelectInfo from './FormProcesos'
/**
 * Registra los procesos en la base de datos median el uso de la API.
 *
 * @param {Object} data - Los datos del proceso a registrar.
 */
function registrarProcesos (data) {
  pushData('/proceso/create', data).then((res) => {
    if (data?.message) {
      alert(data.message)
      return
    }
    alert('Proceso registrado con éxito')
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

document.querySelector('form').addEventListener('submit', (e) => {
  e.preventDefault()

  const data = {
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

  if (validacionForm(data)) registrarProcesos(data)
})

insertsSelectInfo()
const params = new URLSearchParams(window.location.search)
document.querySelector('.volver').setAttribute('href', `/procesos?idLote=${params.get('idLote')}&nombre=${params.get('nombre')}`)
