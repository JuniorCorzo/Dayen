import getData from '../utils/FetchData'

function generalSessionStorage () {
  const usuario = sessionStorage.getItem('usuario')
  if (!usuario) {
    sessionStorage.setItem('jwt', getCookie('jwt'))
    getData(`/usuario/${getCookie('userId')}`).then((data) => {
      sessionStorage.setItem(
        'usuario',
        JSON.stringify({
          id: data.id,
          nombre: data.username,
          apellido: data.apellido,
          email: data.correo,
          rol: data.rol
        })
      )

      sessionStorage.setItem('lotes', JSON.stringify(data.lote))
      sessionStorage.setItem('personal', JSON.stringify(data.personal))
    })

    getData('/producto/all').then((productos) => {
      sessionStorage.setItem('productos', JSON.stringify(productos))
    })

    getData(`/personal/${getCookie('userId')}`).then((personal) => {
      sessionStorage.setItem('personal', JSON.stringify(personal))
    })

    getData('/tipoProcesos/all').then((procesos) => {
      sessionStorage.setItem('tipo_procesos', JSON.stringify(procesos))
    })
  }
}

function updateUsuarioSession () {
  const usuario = sessionStorage.getItem('usuario')
  if (usuario) {
    getData(`/usuario/${getCookie('userId')}`).then((data) => {
      sessionStorage.setItem(
        'usuario',
        JSON.stringify({
          id: data.id,
          nombre: data.username,
          apellido: data.apellido,
          email: data.correo,
          rol: data.rol
        })
      )
    })
  }
}

function updatePersonalSession () {
  const personal = sessionStorage.getItem('personal')
  if (personal) {
    getData(`/personal/${getCookie('userId')}`).then((data) => {
      sessionStorage.setItem('personal', JSON.stringify(data))
    })
  }
}

function updateLoteSession () {
  const lote = sessionStorage.getItem('lotes')
  if (lote) {
    getData(`/lotes/${getCookie('userId')}`).then((data) => {
      sessionStorage.setItem('lotes', JSON.stringify(data))
    })
  }
}

function getCookie (name) {
  return document.cookie
    .split('; ')
    .find((row) => row.startsWith(name))
    .split('=')[1]
}

export {
  generalSessionStorage,
  updateUsuarioSession,
  updatePersonalSession,
  updateLoteSession,
  getCookie
}
