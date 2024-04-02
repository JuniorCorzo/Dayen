import Choices from 'choices.js'
import { pushData } from './FetchData'

/**
 * Registra los procesos en la base de datos median el uso de la API.
 *
 * @param {Object} data - Los datos del proceso a registrar.
 */
function registrarProcesos (data) {
  pushData('/proceso/create', data).then((res) => {
    if (res.status === 200) {
      alert('Proceso registrado con éxito')
    } else {
      alert('Error al registrar el proceso')
    }
  })
}

document.querySelector('form').addEventListener('submit', (e) => {
  e.preventDefault()

  const data = {
    idLote: parseInt(new URLSearchParams(window.location.search).get('idLote')),
    idTipo: document.querySelector('.select-tipo').value,
    idProducto: [],
    idPersonal: [],
    descripcion: document.querySelector('.descripcion').value,
    realizadoEn: `${document.querySelector('.calen').value}T20:02:00`
  }

  document.querySelectorAll('.productos-values .choices__item').forEach((producto) => {
    data.idProducto.push(parseInt(producto.getAttribute('data-value')))
  })

  document.querySelectorAll('.personal-values .choices__item').forEach((personal) => {
    data.idPersonal.push(parseInt(personal.getAttribute('data-value')))
  })

  registrarProcesos(data)
})

/**
 * Inserta los productos en un elemento select y devuelve una instancia de Choices.
 *
 * @returns {Choices} - Una instancia de la clase Choices.
 */
const insertProducto = () => {
  JSON.parse(sessionStorage.getItem('productos')).forEach((producto) => {
    const option = document.createElement('option')
    option.value = producto.idProducto
    option.text = producto.nombre
    document.querySelector('.select-productos').appendChild(option)
  })

  return new Choices(document.querySelector('.select-productos'), {
    allowHTML: false,
    removeItems: true,
    removeItemButton: true,
    placeholder: true,
    placeholderValue: 'Productos',
    searchPlaceholderValue: 'Buscar producto',
    itemSelectText: 'Presione para seleccionar',
    noChoicesText: 'No hay más productos',
    searchEnabled: true,
    classNames: {
      listItems: 'productos-values choices__list--multiple'
    }
  })
}

new Promise((resolve) => {
  setInterval(() => {
    if (sessionStorage.getItem('productos')) {
      clearInterval()
      resolve()
    }
  }, 0)
}).then(insertProducto)

/**
 * Inserta opciones de personal en un elemento select y devuelve una instancia de Choices.
 *
 * @returns {Choices} - Instancia de Choices.
 */
const insertPersonal = () => {
  JSON.parse(sessionStorage.getItem('personal')).forEach((personal) => {
    const option = document.createElement('option')
    option.value = personal.idPersonal
    option.text = `${personal.nombre}`
    document.querySelector('.select-personal').appendChild(option)
  })

  return new Choices(document.querySelector('.select-personal'), {
    allowHTML: false,
    removeItems: true,
    removeItemButton: true,
    placeholder: true,
    placeholderValue: 'Personal',
    searchPlaceholderValue: 'Buscar personal',
    itemSelectText: 'Presione para seleccionar',
    noChoicesText: 'No hay más personal',
    searchEnabled: true,
    classNames: {
      listItems: 'personal-values choices__list--multiple'
    }
  })
}

new Promise((resolve) => {
  setInterval(() => {
    if (sessionStorage.getItem('personal')) {
      clearInterval()
      resolve()
    }
  }, 0)
}).then(insertPersonal)

/**
 * Inserta opciones de tipo de procesos en un elemento select y devuelve una instancia de Choices.
 *
 * @returns {Choices} - Instancia de Choices.
 */
const insertTipoProcesos = () => {
  JSON.parse(sessionStorage.getItem('tipo_procesos')).forEach((tipo) => {
    const option = document.createElement('option')
    option.value = tipo.idTipo
    option.text = tipo.tipoProceso
    document.querySelector('.select-tipo').appendChild(option)
  })

  return new Choices(document.querySelector('.select-tipo'), {
    allowHTML: false,
    searchEnabled: false,
    placeholder: true,
    placeholderValue: 'Tipo de proceso',
    itemSelectText: 'Presione para seleccionar'
  })
}

new Promise((resolve) => {
  setInterval(() => {
    if (sessionStorage.getItem('tipo_procesos')) {
      clearInterval()
      resolve()
    }
  }, 0)
}).then(insertTipoProcesos)

document.querySelector('.volver').setAttribute('href', `/procesos?idLote=${new URLSearchParams(window.location.search).get('idLote')}`)
