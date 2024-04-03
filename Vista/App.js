import { createServer as createServerVite } from 'vite'
import express from 'express'
import path from 'path'
import multer from 'multer'
import fs from 'fs'
import sharp from 'sharp'

async function createServer () {
  const app = express()
  const vite = await createServerVite({
    server: { middlewareMode: 'html ' }
  })

  if (!fs.existsSync('public/uploads')) { fs.mkdirSync('public/uploads') }
  const storage = multer.diskStorage({
    destination: (req, file, cb) => {
      cb(null, 'public/uploads/')
    },
    filename: (req, file, cb) => {
      cb(null, file.originalname)
    }
  })

  const upload = multer({ storage })
  app.post('/subir-imagen', upload.single('tituloImagen'), (req, res) => {
    sharp(req.file.path)
      .resize(300, 300)
      .webp({ quality: 80 })
      .toFile(`public/uploads/${req.body.tituloImagen}.webp`, (err) => {
        if (err) {
          console.log(err)
        }
      })

    res.send('Imagen subida')
  }, (error, req, res, next) => {
    console.log(error)
    res.status(400).send('Error al subir la imagen')
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
  route('/registro/lote', 'pages/registros/registro_lotes.html')
  route('/registro/proceso', 'pages/registros/registro_procesos.html')
  route('/registro/personal', 'pages/registros/registro_personal.html')
  route('/procesos', 'pages/modulo_procesos.html')

  app.use(vite.middlewares)
  app.listen(5050, () => {
    console.log('Server running at http://localhost:5050')
  })
}

createServer()
