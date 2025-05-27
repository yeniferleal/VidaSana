// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyCUrGv5_6a_7wgUKIKfdkJjlINnQa2VHx4",
    authDomain: "gestion-obras.firebaseapp.com",
    projectId: "gestion-obras",
    storageBucket: "gs://gestion-obras.appspot.com",
    messagingSenderId: "864821005377",
    appId: "1:864821005377:web:5458e2a00d625cb428cac0"
};
//"gestion-obras.appspot.com",
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

async function subirArchivo() {
    inputFile = document.getElementById("formFile");
    if (inputFile.files.length === 0) {
        alert("Por favor seleciona un archivo");
        return;
    } else {
        let file = inputFile.files[0];
        let storageRef = firebase.storage().ref("Imagenes");
        const fileName = file.name.toString();
        let path = modificarName(fileName);
        let thisRef = storageRef.child(path);
        const task = thisRef.put(file);
        firebase.auth().signInAnonymously()
                .then(function () {
                    task
                            .then(snapshot => snapshot.ref.getDownloadURL())
                            .then(url => {
                                alert("Solicitud de registro enviada...");
                                document.getElementById("foto").value = url;
                                document.formulario.submit();
                            });

                }).catch(function (error) {
            var errorCode = error.code;
            var errorMessage = error.message;
            console.log(errorCode);
            console.log(errorMessage);
        });
    }
}

async function eliminarUsuario(id, url) {
    
    let foto = url;
    if (foto==='null') {
        alert("Usuario eliminado.");
        document.location.href = "/eliminarUsuario/" + id;
    }else{
        let storageRef = firebase.storage().ref("Imagenes");
        let thisRef = storageRef.child(obtenerNombreImg(url));
        thisRef.delete().then(function () {
            alert("Usuario eliminado.");
            document.location.href = "/eliminarUsuario/" + id;
        }).catch(function (error) {
            console.log(error);
        });
    }
}

async function eliminarSolicitud(id, url) {
    
    let foto = url;
    if (foto==='null') {
        alert("Solicitud rechazada.");
        document.location.href = "/rechazarSolicitud/" + id;
    }else{
        let storageRef = firebase.storage().ref("Imagenes");
        let thisRef = storageRef.child(obtenerNombreImg(url));
        thisRef.delete().then(function () {
            alert("Solicitud rechazada.");
            document.location.href = "/rechazarSolicitud/" + id;
        }).catch(function (error) {
            console.log(error);
        });
    }
}

function obtenerNombreImg(url) {
    let valida = false;
    let cadena = "";
    let char;
    if (url.length > 100) {
        for (let i = 83; valida === false; i++) {
            char = url.toString().charAt(i);
            if (char === "?")
                valida = true;
            else
                cadena += char;

        }
    }
    return cadena;
}

function modificarName(fileName) {
    const nombreUsuario = document.getElementById("nombre").value;
    let nombres = nombreUsuario.toString().split(" ");
    let username = document.getElementById("username").value;
    return hashCode(username) + "-" + nombres[0] + getExtension(fileName);
}

function getExtension(fileName) {
    const file = fileName;
    let extension = "";
    let char = "";
    for (let i = file.length; i > -1; i--) {
        char = file.toString().charAt(i);
        extension += char;
        if (char === ".")
            i = 0;
    }
    let cadena = "";
    for (let i = extension.length; i > -1; i--) {
        cadena += extension.charAt(i);
    }
    return cadena;
}

function hashCode(s) {
    let h = 0;
    for (let i = 0; i < s.length; i++)
        h = Math.imul(31, h) + s.charCodeAt(i) | 0;

    return Math.abs(h);
}