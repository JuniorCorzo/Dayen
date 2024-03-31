import { createServer as createServerVite } from 'vite'
import express from 'express'
import path from 'path'

async function createServer () {
  const app = express()

  const vite = await createServerVite({
    server: { middlewareMode: 'html' }
  })

  function route (url, page) {
    app.get(url, (req, res) => {
      res.sendFile(path.join(__dirname, page))
    })
  }

  route('/', 'index.html')
  route('/inicio', 'pages/modulo_lotes.html')
  route('/login', 'pages/inicio_sesion.html')
  route('/registrarse', 'pages/registros/registro_usuario.html')
  route('/recuperar_clave', 'pages/registros/recuperar_contraseña.html')
  route('/registro/lote', 'pages/registros/registro_hectareas.html')
  route('/registro/proceso', 'pages/registros/registro_procesos.html')
  route('/registro/personal', 'pages/registros/registro_personal.html')
  route('/procesos', 'pages/modulo_procesos.html')

  app.use(vite.middlewares)
  app.listen(5050, () => {
    console.log('Server running at http://localhost:5050')
  })
}

createServer()
