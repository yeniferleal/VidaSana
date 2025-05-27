package com.vidaSana.main;

import com.vidaSana.entities.Persona;
import com.vidaSana.entities.Usuario;
import com.vidaSana.service.impl.PersonaServiceImpl;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class Controlador_Usu {
	
	@Autowired
	private PersonaServiceImpl servicioPersona;

	@GetMapping("/Usu_Guia_Alimentaria")
    public String Guia_Alimentaria(Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/pages/Usu_Guia_Alimentaria";
    }
	
	@GetMapping("/Usu_Guia_Etiquetas")
    public String Guia_Etiquetas(Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/pages/Usu_Guia_Etiquetas";
    }
	
	@GetMapping("/Usu_Cambio_Habitos")
    public String Cambio_Habitos(Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/pages/Usu_Cambio_Habitos";
    }
	
	@GetMapping("/Sis_Usuario_Personas")
    public String Gestionar_Personas(Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		var personas = servicioPersona.listar();
        model.addAttribute("personas", personas);
        model.addAttribute("usuario", usuario);
        return "html/usuario/Sis_Usuario_Personas"; 
    }
	
	@GetMapping("/Sis_Usuario_Agregar_Persona")
    public String Sis_Agregar_Zonas(Persona persona, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "html/usuario/Sis_Usuario_Agregar_Persona";
    }

    @PostMapping("/guardarPersona")
    public String guardarPersona(Model model, Persona persona,RedirectAttributes atribute, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        try {
        	persona.setUsuario(usuario);
            servicioPersona.guardar(persona);
            var personas = servicioPersona.listar();
            model.addAttribute("personas", personas);
        } catch (Exception e) {
            atribute.addFlashAttribute("errores", "La persona ya existe.");
            return "redirect:/Sis_Usuario_Agregar_Persona";
        }
        return "html/usuario/Sis_Usuario_Personas";
    }
    
    @GetMapping("/eliminarPersona/{id_persona}")
    public String eliminarPunto(Persona persona, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        servicioPersona.eliminar(persona);
        var personas = servicioPersona.listar();
        model.addAttribute("personas", personas);
        return "html/usuario/Sis_Usuario_Personas";
    }
}
