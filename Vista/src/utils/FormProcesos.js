import Choices from 'choices.js'
import '../../public/css/formularios/choices.css'

function insertsSelectInfo () {
  const choicesReturn = {}
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
  }).then(choicesReturn.productos = insertProducto())

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
  }).then(choicesReturn.personal = insertPersonal())

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
  }).then(choicesReturn.tipoProceso = insertTipoProcesos())

  return choicesReturn
}

export default insertsSelectInfo
