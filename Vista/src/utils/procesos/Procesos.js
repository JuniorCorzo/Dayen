import DataTable from 'datatables.net-bs5'
import 'datatables.net-responsive-bs5'
import 'datatables.net-responsive-bs5/css/responsive.bootstrap5.min.css'

import insertsSelectInfo from './FormProcesos'
import { HOST_API } from '../Env'

const params = new URLSearchParams(window.location.search)
document.querySelector('.title').innerHTML = `Procesos del lote ${params.get('nombre')}`

function prepararModificar () {
  document.querySelectorAll('.modificar-proceso').forEach((element) => {
    element.addEventListener('click', function (event) {
      event.preventDefault()

      choicesReturn.productos.setChoiceByValue(this.getAttribute('data-productos').split(','))
      choicesReturn.personal.setChoiceByValue(this.getAttribute('data-personal').split(','))
      choicesReturn.tipoProceso.setChoiceByValue(this.getAttribute('data-id-tipo'))
      document.querySelector('.descripcion').value = this.getAttribute('data-descripcion')
      document.querySelector('.calen').value = this.getAttribute('data-realizado-en')

      document.querySelector('.modificar-proceso').setAttribute('data-id-proceso', this.getAttribute('data-id-proceso'))
    })
  })
}

function prepararEliminar () {
  document.querySelectorAll('.eliminar-proceso').forEach((element) => {
    element.addEventListener('click', function (event) {
      event.preventDefault()
      document.querySelector('#eliminar_boton').setAttribute('data-id-proceso', this.getAttribute('data-id-proceso'))
    })
  })
}

const tablaProceso = new DataTable('#tablaprocesos', {
  responsive: true,
  lengthChange: false,
  pageLength: 10,
  ordering: true,
  order: [[5, 'desc']],
  info: false,
  ajax: {
    url: `${HOST_API}/proceso/${params.get('idLote')}`,
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
    { data: 'idProceso', title: 'ID', visible: false },
    { data: 'idTipo.tipoProceso', title: 'Tipo de Proceso' },
    {
      data: 'idProducto',
      title: 'Productos',
      orderable: false,
      render: function (data) {
        if (!data || data.length === 0) return 'No hay productos asociados'
        return data.map((producto) => producto.nombre).join(', ')
      }
    },
    { data: 'descripcion', title: 'Descripción', orderable: false },
    {
      data: 'personal',
      title: 'Personal',
      orderable: false,
      render: function (data) {
        if (!data || data.length === 0) return 'No hay personal asociado'
        return data.map((personal) => personal.nombre).join(', ')
      }
    },
    { data: 'realizadoEn', title: 'Realizado en' },
    {
      data: null,
      title: 'Acciones',
      orderable: false,
      render: function (data) {
        return `
        <div class="d-flex justify-content-center gap-3">
          <a
            class="modificar-proceso"
            data-bs-toggle="modal" 
            data-bs-target="#modificar_modal"
            data-id-proceso="${data.idProceso}"  
            data-id-tipo="${data.idTipo.idTipo}"
            data-productos="${data.idProducto.map((producto) => producto.idProducto).join(',')}"
            data-descripcion="${data.descripcion}"
            data-personal="${data.personal.map((personal) => personal.idPersonal).join(',')}"
            data-realizado-en="${data.realizadoEn}"
          >
            <i class="bi-pencil-square"></i>
          </a>
          <a
            class="eliminar-proceso"
            data-bs-toggle="modal" 
            data-bs-target="#eliminar_modal"
            data-id-proceso="${data.idProceso}"
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
  drawCallback: function () {
    prepararModificar()
    prepararEliminar()
  }
})
const choicesReturn = insertsSelectInfo()
const containerSearch = document.querySelector('.dt-container .row')
containerSearch.classList.remove('row')
document.querySelector('.crear-proceso').href = `/registro/proceso?idLote=${params.get('idLote')}&nombre=${params.get('nombre')}`

function actualizarTabla () {
  tablaProceso.ajax.reload()
}

export default actualizarTabla
