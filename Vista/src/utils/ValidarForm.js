function validarTexto (texto) {
  if (!texto) {
    return 'El campo no puede estar vacío'
  }

  return true
}

function validarNumero (numero) {
  if (!numero) {
    return 'El campo no puede estar vacío'
  }

  if (/\D/.test(numero)) {
    return 'El campo debe ser un número'
  }

  return true
}

function validarCedula (cedula) {
  if (!cedula) {
    return 'El campo no puede estar vacío'
  }

  if (/\D/.test(cedula)) {
    return 'La cédula debe ser un número'
  }

  return true
}

function validarCorreo (correo) {
  if (!correo) {
    return 'El campo no puede estar vacío'
  }

  if (!/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/.test(correo)) {
    return 'El correo no es válido'
  }

  return true
}

function validarClave (clave) {
  if (!clave) {
    return 'El campo no puede estar vacío'
  }

  if (clave.length < 8 || /^[0-9]+$/.test(clave)) {
    return 'La contraseña debe contener números <br> La contraseña debe contener al menos 8 caracteres'
  }

  return true
}

export { validarTexto, validarCedula, validarNumero, validarCorreo, validarClave }
