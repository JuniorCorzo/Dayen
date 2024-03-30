import DataTable from "datatables.net-dt";

const idLote = new URLSearchParams(window.location.search);
new DataTable("#tablaprocesos", {
  ajax: {
    url: `${window.HOST_API}/proceso/${idLote.get("idLote")}`,
    method: "GET",
    dataSrc: "",
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        "Authorization",
        `Bearer ${sessionStorage.getItem("jwt")}`
      );
      xhr.setRequestHeader("Content-Type", "application/json");
    },
  },
  columns: [
    { data: "idProceso", title: "ID" },
    { data: "idTipo.tipoProceso", title: "Tipo de Proceso" },
    {
      data: "idProducto",
      title: "Productos",
      render: function (data) {
        if (!data || data.length === 0) return "No hay productos asociados";
        return data.map((producto) => producto.nombre).join(", ");
      },
    },
    { data: "descripcion", title: "Descripción" },
    {
      data: "personal",
      title: "Personal",
      render: function (data) {
        if (!data || data.length === 0) return "No hay personal asociado";
        return data.map((personal) => personal.nombre).join(", ");
      },
    },
    { data: "realizadoEn", title: "Realizado en" },
    {
      data: null,
      title: "Acciones",
      render: function () {
        return `
      <a data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        <i class="bi bi-pencil-square"></i>
      </a>
      <a data-bs-toggle="modal" data-bs-target="#exampleModal">
        <i class="bi bi-trash"></i>
      </a>
    `;
      },
    },
  ],
});
