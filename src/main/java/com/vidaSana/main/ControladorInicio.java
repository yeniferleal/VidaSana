package com.vidaSana.main;

import com.vidaSana.entities.SolicitudRegistro;
import com.vidaSana.entities.SolicitudRol;
import com.vidaSana.entities.Usuario;
import com.vidaSana.service.impl.SolicitudRegistroServiceImpl;
import com.vidaSana.service.impl.UsuarioServiceImpl;
import com.vidaSana.util.EncriptarPassword;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private SolicitudRegistroServiceImpl solicitudImpl;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        Usuario usuario = usuarioService.encontrarUsuario(user.getUsername());
        session.setAttribute("usuario", usuario);
        model.addAttribute("usuario", usuario);
        return userAuthentication(user);
    }

    private String userAuthentication(User user) {
        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMINISTRADOR")) {
                return "html/Sis_Administrador_Prin";
            }
            if (role.getAuthority().equals("ROLE_USUARIO")) {
                return "html/Sis_Usuario_Prin";
            }
        }
        return "login";
    }
    
    @GetMapping("/Sis_Administrador_Prin")
    public String Administrador(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/Sis_Administrador_Prin";
    }
    
    @GetMapping("/Sis_Usuario_Prin")
    public String Usuario(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/Sis_Usuario_Prin";
    }

    @GetMapping("/sign_in")
    public String registrar_User(Usuario usuario) {
        return "html/Registrar_User";
    }

    @PostMapping("/Guardar_Solicitud")
    public String guardar(Usuario usuario, HttpServletRequest req) {
        //HttpServletResponse res
        try {
            EncriptarPassword encriptar = new EncriptarPassword();
            SolicitudRegistro solicitud = new SolicitudRegistro();
            solicitud.setNombre(usuario.getNombre());
            solicitud.setApellido(usuario.getApellido());
            solicitud.setUsername(usuario.getUsername());
            solicitud.setPassword(encriptar.encriptarPassword(usuario.getPassword()));

            String checkAdmin = req.getParameter("check_admin");
            String checkUser = req.getParameter("check_user");
            List<SolicitudRol> roles = new ArrayList<>();
            SolicitudRol solicitud_rol;
            if (checkAdmin != null) {
                if (checkAdmin.equals("1")) {
                    solicitud_rol = new SolicitudRol();
                    solicitud_rol.setTipo_rol("ROLE_ADMINISTRADOR");
                    roles.add(solicitud_rol);
                }
            }
            if (checkUser != null) {
                if (checkUser.equals("2")) {
                    solicitud_rol = new SolicitudRol();
                    solicitud_rol.setTipo_rol("ROLE_USUARIO");
                    roles.add(solicitud_rol);
                }
            }
            solicitud.setSolicitudRoles(roles);
            solicitudImpl.guardarSolicitud(solicitud);
            //imagen.saveFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return "login";
    }
    
    @GetMapping("/Olvide_Contrasenia")
    public String olvideContrasenia() {
        return "html/Olvide_Contrasenia";
    }
    
}
