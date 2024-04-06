const insertLotes = () => {
  const data = JSON.parse(sessionStorage.getItem('lotes'))
    .map((data) => {
      return `
      <div>
        <div class="d-flex flex-column p-3 ">
            <a href="/procesos?idLote=${data.idLote}">
                <h3>Lote ${data.nombre}</h3>
                <img 
                  class="IMG-27 mt-4"
                  src="/uploads/${data.tituloImagen}${data.idLote}.webp"
                  alt="Foto del lote ${data.nombre}"
                  title="Ir a los procesos del lote ${data.nombre}"
                >
            </a>

            <div class="d-flex gap-3 mt-3 justify-content-center"> 
            
              <button class="btn">Modificar</button>

              <button class="btn" data-toggle="modal" data-target="#exampleModal">Eliminar</button>
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
