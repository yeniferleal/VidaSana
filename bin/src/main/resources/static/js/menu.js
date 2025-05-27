function selectDisable() {
    let select = document.getElementById("select-tipo");
    select.setAttribute("disabled", "disabled");
}

function more() {
    let cant = document.getElementById("cantidad-insumo");
    cant.value++;
}

function less() {
    let cant = document.getElementById("cantidad-insumo");
    if (cant.value - 1 > -1) {
        cant.value--;
    } else {
        alert("La cantidad no puede ser negativa.");
    }
}

if(document.title==="Supervisor Cargar Pedido"){
    let nombre_pedido = document.getElementById("nombre-pedido");
    let tipo_pedido = document.getElementById("select-tipo");
    let crear_pedido = document.getElementById("crear_pedido");
    if (nombre_pedido.value !== "" && tipo_pedido.value > 0 ) {
        nombre_pedido.disabled = true;
        tipo_pedido.disabled = true;
        crear_pedido.disabled = true;
        crear_pedido.hidden = true;
        document.form_insumos.hidden = false;
        document.getElementById("eliminar_pedido").hidden = false;
        selectDisable();
    }
}

function buscarPedidoSuper(){
    let id_pedido = document.getElementById("id_pedido");
    if(id_pedido.value!==""){
        document.form_buscar.action="/verPedidoSuper/"+id_pedido.value;
        document.form_buscar.submit();
    }else{
        alert("Campos vacios.");
    }
}

function buscarPedidoInter(){
    let id_pedido = document.getElementById("id_pedido");
    if(id_pedido.value!==""){
        document.form_buscar.action="/verPedidoInter/"+id_pedido.value;
        document.form_buscar.submit();
    }else{
        alert("Campos vacios.");
    }
}

let cargo = document.querySelector(".menu-cargo").getAttribute("value");
let li_roles = document.getElementById("sub-dropdown-menu").childNodes;
switch (cargo) {
    case "ADMINISTRADOR":
        li_roles[1].classList.add("li-active");
        break;
    case "SUPERVISOR":
        li_roles[3].classList.add("li-active");
        break;
    case "INTERVENTOR":
        li_roles[5].classList.add("li-active");
        break;
}

var menuCerrado = false;
var tamañoPage = 0;

function menu() {
    if (tamañoPage > 600 || tamañoPage == 0) {
        if (menuCerrado == false) {
            menuCerrado = true;
        } else {
            menuCerrado = false;
        }
        const text_nav = document.getElementById("texto-nav");
        text_nav.classList.toggle("texto-nav_move");
        const menu_img = document.querySelector(".user-aside_img");
        menu_img.classList.toggle("user-aside_img-active");
        const menu = document.querySelector(".menu");
        menu.classList.toggle("menu-active");
        const menu_figure = document.querySelector(".menu-figure");
        menu_figure.classList.toggle("menu-figure-active");
        const menu_ul = document.querySelector(".menu-ul");
        menu_ul.classList.toggle("menu-ul-active");

        const contenedor_principal = document.querySelector(".contenedor-principal");
        contenedor_principal.classList.toggle("contenedor-principal-active");

        var menu_a = document.getElementsByClassName("menu-a");
        for (let i = 0; i < menu_a.length; i++) {
            menu_a[i].classList.toggle("menu-a-active");
        }

        var menu_li = document.getElementsByClassName("menu-li");
        for (let i = 0; i < menu_li.length; i++) {
            menu_li[i].classList.toggle("menu-li-active");
        }

        const menu_info = document.getElementById("menu_info");

        const footer = document.getElementById("footer");


        if (window.innerWidth > 600) {
            if (menuCerrado == true) {
                footer.classList.toggle("footer_active");
                footer.classList.remove("footer_desactive");
            } else {
                footer.classList.remove("footer_active");
                footer.classList.toggle("footer_desactive");
            }
        }

        switch (cargo) {
            case "SUPERVISOR":
                if (menu_info.innerHTML === "") {
                    menu_info.innerHTML = '<h4 class="menu-text-user my-2">USUARIO</h4><h5 class="menu-cargo my-2">SUPERVISOR</h5><h6 class="menu-text-email my-2"><a href="mailto:user@gmail.com">user@gmail.com</a></h6>';
                } else {
                    menu_info.innerHTML = ""
                }
                break;
            case "INTERVENTOR":
                if (menu_info.innerHTML === "") {
                    menu_info.innerHTML = '<h4 class="menu-text-user my-2">USUARIO</h4><h5 class="menu-cargo my-2">INTERVENTOR</h5><h6 class="menu-text-email my-2"><a href="mailto:user@gmail.com">user@gmail.com</a></h6>';
                } else {
                    menu_info.innerHTML = ""
                }
                break;
            case "ADMINISTRADOR":
                if (menu_info.innerHTML === "") {
                    menu_info.innerHTML = '<h4 class="menu-text-user my-2">USUARIO</h4><h5 class="menu-cargo my-2">ADMINISTRADOR</h5><h6 class="menu-text-email my-2"><a href="mailto:user@gmail.com">user@gmail.com</a></h6>';
                } else {
                    menu_info.innerHTML = ""
                }
                break;
        }
    }
}

window.addEventListener("resize", function () {
    tamañoPage = window.innerWidth;
    if (window.innerWidth < 600) {
        const footer = document.getElementById("footer");
        footer.classList.add("footer_600");
    }

    if (window.innerWidth > 600) {
        const footer = document.getElementById("footer");
        footer.classList.remove("footer_600");
    }
});

if (window.innerWidth < 600) {
    const footer = document.getElementById("footer");
    footer.classList.add("footer_600");
}

function descargarReporteSuper(id_pedido){
    window.open("/verPedidoSuper/" + id_pedido + "?format=pdf","_blank");
}

function descargarReporteInter(id_pedido){
    window.open("/verPedidoInter/" + id_pedido + "?format=pdf","_blank");
}