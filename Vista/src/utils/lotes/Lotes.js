const insertLotes = () => {
  const data = JSON.parse(sessionStorage.getItem('lotes'))
    .map((data) => {
      return `
      <div>
        <div class="d-flex flex-column p-3 ">
            <a href="/procesos?idLote=${data.idLote}&nombre=${data.nombre}">
                <h3>Lote ${data.nombre}</h3>
                <img 
                  class="imagen-lotes mt-4"
                  id="imagen-lote-${data.idLote}"
                  src="/uploads/${data.tituloImagen}${data.idLote}.webp"
                  alt="Foto del lote ${data.nombre}"
                  title="Ir a los procesos del lote ${data.nombre}"
                >
            </a>
            <div class="d-flex gap-3 mt-2 justify-content-center"> 
              <button 
                type="button" 
                class="btn modificar-lote"
                data-bs-toggle="modal"
                data-bs-target="#modificar_modal"
                data-id-lote="${data.idLote}"
                data-nombre="${data.nombre}"
                data-titulo-imagen="${data.tituloImagen}"
                data-hectareas="${data.hectareas}"
                data-fase="${data.fase}"
              >
                Modificar
              </button>
              <button 
                type="button"
                class="btn eliminar-lote"
                data-bs-toggle="modal"
                data-bs-target="#eliminar_modal"
                data-id-lote="${data.idLote}"
              >
                Eliminar
              </button>
            </div>
           
        </div>
      </div>
              `
    })
    .join('\n')

  if (!data) {
    document.querySelector('.contenedor-lotes').innerHTML = `
    <div class="p-3">
        <h3>No hay lotes</h3>
    </div>
    `
    return
  }
  document.querySelector('.contenedor-lotes').innerHTML = data
}

new Promise((resolve) => {
  setInterval(() => {
    if (sessionStorage.getItem('lotes')) {
      clearInterval()
      resolve()
    }
  }, 100)
}).then(insertLotes)
