package com.vidaSana.main;

import com.vidaSana.entities.Rol;
import com.vidaSana.entities.SolicitudRegistro;
import com.vidaSana.entities.SolicitudRol;
import com.vidaSana.entities.Usuario;
import com.vidaSana.service.impl.ActividadFisicaServiceImpl;
import com.vidaSana.service.impl.PlanAlimentarioServiceImpl;
import com.vidaSana.service.impl.SolicitudRegistroServiceImpl;
import com.vidaSana.service.impl.UsuarioServiceImpl;
import com.vidaSana.util.EncriptarPassword;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador_Admin {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private SolicitudRegistroServiceImpl solicitudService;
    
    @Autowired
    private PlanAlimentarioServiceImpl servicioPlan;
    
    @Autowired
    private ActividadFisicaServiceImpl servicioActividad;

    
    @GetMapping("/Sis_Administrador_Planes")
    public String Sis_Administrador_Planes(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        var planes = servicioPlan.listar();
        model.addAttribute("planes", planes);
        model.addAttribute("usuario", usuario);
        return "html/admin/Sis_Administrador_Planes";
    }
    
    @GetMapping("/Sis_Administrador_Actividades")
    public String Sis_Administrador_Actividades(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        var actividades = servicioActividad.listar();
        model.addAttribute("actividades", actividades);
        model.addAttribute("usuario", usuario);
        return "html/admin/Sis_Administrador_Actividades";
    }

    @GetMapping("/Sis_Administrador_GesReg")
    public String Sis_Administrador_GesReg(Model model, HttpSession session) {
        List<SolicitudRegistro> solicitudes = solicitudService.listarSolicitudes();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        model.addAttribute("solicitudes", solicitudes);
        return "html/admin/Sis_Administrador_GesReg";
    }

    @GetMapping("/aceptarSolicitud/{id_solicitud}")
    public String aceptarSolicitud(SolicitudRegistro solicitud, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        solicitud = solicitudService.findById(solicitud.getId_solicitud());
        Usuario nuevoUser = new Usuario();
        nuevoUser.setNombre(solicitud.getNombre());
        nuevoUser.setApellido(solicitud.getApellido());
        nuevoUser.setUsername(solicitud.getUsername());
        nuevoUser.setPassword(solicitud.getPassword());
        List<Rol> roles = new ArrayList<>();
        for (SolicitudRol aux : solicitud.getSolicitudRoles()) {
            roles.add(new Rol(aux.getTipo_rol()));
        }
        nuevoUser.setRoles(roles);
        usuarioService.guardarUsuario(nuevoUser);
        solicitudService.eliminarSolicitud(solicitud);
        var solicitudes = solicitudService.listarSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        return "html/admin/Sis_Administrador_GesReg";
    }

    @GetMapping("/rechazarSolicitud/{id_solicitud}")
    public String rechazarSolicitud(SolicitudRegistro solicitud, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        solicitud = solicitudService.findById(solicitud.getId_solicitud());
        solicitudService.eliminarSolicitud(solicitud);
        var solicitudes = solicitudService.listarSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        return "html/admin/Sis_Administrador_GesReg";
    }

    @GetMapping("/Sis_Administrador_GesUs")
    public String Sis_Administrador_GesUs(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "html/admin/Sis_Administrador_GesUs";
    }

    @GetMapping("/eliminarUsuario/{id_usuario}")
    public String eliminarUsuario(Usuario usuario, Model model, HttpSession session) {
        Usuario aux = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", aux);
        usuario = usuarioService.findById(usuario.getId_usuario());
        usuarioService.eliminarUsuario(usuario.getId_usuario());
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "html/admin/Sis_Administrador_GesUs";
    }

    @GetMapping("/modificarUsuario/{id_usuario}")
    public String modificarUsuario(Usuario usuario, Model model, HttpSession session) {
        Usuario aux = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", aux);
        usuario = usuarioService.findById(usuario.getId_usuario());
        model.addAttribute("usuarioAux", usuario);
        return "html/admin/Editar_User"; 
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario, Model model, HttpSession session) {
        EncriptarPassword encriptar = new EncriptarPassword();
        Usuario aux = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", aux);
        Usuario info = usuarioService.findById(usuario.getId_usuario());
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        String username = usuario.getUsername();
        String password = usuario.getPassword();
        if (!nombre.equals("")) {
            info.setNombre(nombre);
        }
        if (!apellido.equals("")) {
            info.setApellido(apellido);
        }
        if (!username.equals("")) {
            info.setUsername(username);
        }
        if (!password.equals("")) {
            info.setPassword(encriptar.encriptarPassword(password));
        }
        usuarioService.guardarUsuario(info);
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "html/admin/Sis_Administrador_GesUs";
    }
}
