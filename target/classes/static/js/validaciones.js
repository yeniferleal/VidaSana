
function verContraseña() {
    let password = document.getElementById("password");
    let c_password;
    if (document.title == "Registrar Usuario" || document.title == "Editar Usuario") {
        c_password = document.getElementById("c-password");
    }
    let eye = document.getElementById("password-eye");
    if (password.type == "password") {
        eye.setAttribute("src", "/img/icons/eye-regular.svg");
        password.setAttribute("type", "text");
        if (password !== undefined) {
            c_password.setAttribute("type", "text");
        }
    } else {
        eye.setAttribute("src", "/img/icons/eye-slash-regular.svg");
        password.setAttribute("type", "password");
        c_password.setAttribute("type", "password");
    }
}

function modificarAction() {
    let inputs = document.querySelectorAll(".input");
    if (verificarUsername(inputs[2].value)) {
        if (verificarContraseñas(inputs)) {
            document.formulario.submit();
        }
    } else {
        alert("El usuario no es correo valido.");
    }

}

function registrarAction() {
    let inputs = document.querySelectorAll(".input");
    let check1 = document.getElementById("check_admin");
    let check2 = document.getElementById("check_user");
    if (inputs[0].value !== "" && inputs[1].value !== "" && inputs[2].value !== "" && inputs[3].value !== "" && inputs[4].value !== "" && check1.checked || check2.checked) {
        if (verificarUsername(inputs[2].value)) {
            if (verificarContraseñas(inputs)) {
                alert("Solicitud de registro enviada...");
                document.formulario.submit();
            }
        } else {
            alert("El usuario no es correo valido.");
        }
    } else {
        alert("Por favor, complete todos los campos.");
    }
}

function deleteUsuario(id){
    const id_usuario = id;
    document.location.href = "/eliminarUsuario/" + id_usuario;
}

function modificarUsuario(id) {
    const id_usuario = id;
    document.location.href = "/modificarUsuario/" + id_usuario;
}

function rechazarSolicitud(id, url) {
    eliminarSolicitud(id, url);
}

function aceptarSolicitud(id) {
    const id_solicitud = id;
    document.location.href = "/aceptarSolicitud/" + id_solicitud;
}

function verificarUsername(username) {
    let codigo;
    if (username.length === 0)
        return true;
    for (let index = 0; index < username.length; index++) {
        codigo = username.charCodeAt(index);
        //@
        if (codigo == 64) {
            return true;
        }
    }
    return false;
}

function verificarContraseñas(inputs) {
    if (inputs[3].value !== "" && inputs[4].value !== "") {
        if (inputs[3].value == inputs[4].value) {
            let password = inputs[3].value;
            if (password.length >= 8) {
                let codigo;
                let mayus, mini, sim;
                mayus = false;
                mini = false;
                num = false;
                for (let index = 0; index < password.length; index++) {
                    codigo = password.charCodeAt(index);
                    //Mayusculas
                    if (codigo >= 65 && codigo <= 90) {
                        mayus = true;
                    }
                    //Minusculas
                    if (codigo >= 97 && codigo <= 122) {
                        mini = true;
                    }
                    //Numeros
                    if (codigo >= 48 && codigo <= 57) {
                        num = true;
                    }
                }
                if (mayus && mini && num) {
                    return true;
                }
            } else {
                alert("La contraseña debe tener como minimo 8 caracteres. \n Además, la contraseña debe tener al menos una letra mayuscula, una minuscula y un numero.");
                return false;
            }
        } else {
            alert("Las contraseñas no coinciden.");
            return false;
        }
    } else {
        return true;
    }
}

function olvidarContrasenia() {
    let input = document.getElementById("email").value;
    if (input !== undefined || input !== "") {
        alert("verifica tu correo electronico");
        document.formulario.submit();
    } else {
        alert("No ha escrito un correo valido");
    }

}