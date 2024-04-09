import DataTable from 'datatables.net-bs5'
import 'datatables.net-bs5/css/dataTables.bootstrap5.min.css'
import 'datatables.net-responsive-bs5'

import { getCookie } from '../../config/ManageSessionStorage'
import { HOST_API } from '../Env'

const prepararModificar = () => {
  document.querySelectorAll('.modificar-personal').forEach((button) => {
    button.addEventListener('click', () => {
      const { idPersonal, nombre, telefono } = button.dataset

      document.querySelector('input[name="nombre"]').value = nombre
      document.querySelector('input[name="numero"]').value = telefono
      document.querySelector('#modificar_form').setAttribute('data-id-personal', idPersonal)
    })
  })
}

const prepararEliminar = () => {
  document.querySelectorAll('.eliminar-personal').forEach((button) => {
    const { idPersonal, nombre } = button.dataset
    button.addEventListener('click', () => {
      document.querySelector('.text-modal').innerHTML = `<p>Estas seguro de borrar a ${nombre}, ten en cuenta que esto también lo borrar de los procesos a lo que este asociado</p>`
      document
        .querySelector('#eliminar_boton')
        .setAttribute('data-id-personal', idPersonal)
    })
  })
}

const tablaPersona = new DataTable('#tabla_personal', {
  responsive: true,
  lengthChange: false,
  pageLength: 10,
  ordering: true,
  order: [[1, 'asc']],
  info: false,
  ajax: {
    url: `${HOST_API}/personal/${getCookie('userId')}`,
    method: 'GET',
    dataSrc: '',
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        'Authorization',
      `Bearer ${sessionStorage.getItem('jwt')}`
      )
      xhr.setRequestHeader('Content-Type', 'application/json')
    }
  },
  columns: [
    { data: 'idPersonal', title: 'ID', visible: false },
    { data: 'nombre', title: 'Nombre' },
    { data: 'telefono', title: 'Numero de Telefono', orderable: false },
    {
      data: null,
      title: 'Acciones',
      orderable: false,
      render: function (data) {
        return `
      <div class="d-flex justify-content-center gap-3">
        <a
          class="modificar-personal"
          data-bs-toggle="modal" 
          data-bs-target="#modificar_modal"
          data-id-personal="${data.idPersonal}"
          data-nombre="${data.nombre}"
          data-telefono="${data.telefono}"
        >
          <i class="bi-pencil-square"></i>
        </a>
        <a
          class="eliminar-personal"
          data-bs-toggle="modal" 
          data-bs-target="#eliminar_modal"
          data-id-personal=${data.idPersonal}
          data-nombre="${data.nombre}"
        >
          <i class="bi-trash"></i>
        </a>
      </div>
  `
      }
    }
  ],
  language: {
    zeroRecords: 'No existen procesos asociados a este lote',
    emptyTable: 'No existen procesos asociados a este lote',
    search: 'Buscar:'
  },
  drawCallback: function (data) {
    prepararModificar()
    prepararEliminar()
  }
})

const containerSearch = document.querySelector('.dt-container .row')
containerSearch.classList.remove('row')

export const recargarTabla = () => {
  tablaPersona.ajax.reload()
}
