import { generalSessionStorage } from './ManageSessionStorage'

function validateAuth () {
  const jwt = getCookie('jwt')
  if (!jwt && window.location.pathname !== '/login') {
    window.location.replace('/login')
    return
  }

  if (jwt && window.location.pathname === '/login') {
    window.location.replace('/inicio')
  }

  if (jwt) {
    generalSessionStorage()
  }
}

function closeSession () {
  document.cookie =
    'jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; SameSite=Strict; path=/;'
  document.cookie =
    'userId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; SameSite=Strict; path=/;'
  sessionStorage.clear()
}

function getCookie (name) {
  return document.cookie.split('; ').find((row) => row.startsWith(name))
}

validateAuth()

const changeUsername = (usuario) => {
  if (usuario === null) {
    setTimeout(() => {
      usuario = JSON.parse(sessionStorage.getItem('usuario'))
      changeUsername(usuario)
    })
    return
  }

  const username = document.querySelector('.username')
  username.innerHTML = `Hola, ${usuario.nombre} ${usuario.apellido}🌱`
}

const usuario = JSON.parse(sessionStorage.getItem('usuario'))

console.log(window.location.pathname)
if (window.location.pathname !== '/registro/proceso') {
  changeUsername(usuario)
}

if (window.location.pathname !== '/login' && window.location.pathname !== '/registro/proceso') {
  document
    .querySelector('.close-session')
    .addEventListener('click', closeSession)
}
