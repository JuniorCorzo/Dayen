import DataTable from 'datatables.net-bs5'

const idLote = new URLSearchParams(window.location.search)
// eslint-disable-next-line no-new
new DataTable('#tablaprocesos', {
  lengthChange: false,
  pageLength: 10,
  ordering: true,
  order: [[5, 'desc']],
  info: false,
  ajax: {
    url: `${window.HOST_API}/proceso/${idLote.get('idLote')}`,
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
      render: function () {
        return `
      <a data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        <i class="bi bi-pencil-square"></i>
      </a>
      <a data-bs-toggle="modal" data-bs-target="#exampleModal">
        <i class="bi bi-trash"></i>
      </a>
    `
      }
    }
  ],
  language: {
    zeroRecords: 'No existen procesos asociados a este lote',
    emptyTable: 'No existen procesos asociados a este lote',
    search: 'Buscar:'
  }
})

const containerSearch = document.querySelector('.dt-container .row')
containerSearch.classList.remove('row')

document.querySelector('.crear-proceso').href = `/registro/proceso?${idLote}`
