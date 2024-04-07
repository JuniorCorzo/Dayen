import { generalSessionStorage, getCookie } from './ManageSessionStorage'

class Auth {
  constructor () {
    this.withoutAuth = Object.freeze({
      url: ['/', '/login', '/registrarse', '/recuperar_clave', '/validar-clave']
    })
    this.validateAuth()
  }

  validateAuth () {
    const jwt = getCookie('jwt')
    if (!jwt && !this.withoutAuth.url.find((url) => url === window.location.pathname)) {
      window.location.replace('/login')
      return
    }

    if (jwt && this.withoutAuth.url.find((url) => url === window.location.pathname)) {
      window.location.replace('/inicio')
    }

    if (jwt) {
      generalSessionStorage()
    }
  }

  cambiarUsername () {
    new Promise((resolve) => {
      setInterval(() => {
        if (sessionStorage.getItem('usuario')) {
          clearInterval()
          resolve()
        }
      }, 10)
    }).then(() => {
      const { nombre, apellido } = JSON.parse(sessionStorage.getItem('usuario'))
      const username = document.querySelector('.username')
      username.innerHTML = `Hola, ${nombre} ${apellido}🌱`
    })
  }

  closeSession () {
    document.cookie =
      'jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; SameSite=Strict; path=/;'
    document.cookie =
      'userId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; SameSite=Strict; path=/;'
    sessionStorage.clear()
  }
}

const { cambiarUsername, closeSession } = new Auth()
const urlWithNavBar = Object.freeze({
  url: ['/inicio', '/procesos', '/personal']
})

if (urlWithNavBar.url.find((url) => url === window.location.pathname)) {
  document.querySelector('.close-session').addEventListener('click', closeSession)
  cambiarUsername()
}
