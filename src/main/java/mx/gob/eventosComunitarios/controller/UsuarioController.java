package mx.gob.eventosComunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import mx.gob.eventosComunitarios.dao.PersonaInterface;
import mx.gob.eventosComunitarios.dao.RolInterface;
import mx.gob.eventosComunitarios.dao.UsuarioInterface;
import mx.gob.eventosComunitarios.entity.Persona;
import mx.gob.eventosComunitarios.entity.Rol;
import mx.gob.eventosComunitarios.entity.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioInterface usuarioInterface;
	@Autowired
	private RolInterface rolInterface;
	@Autowired 
	private PersonaInterface personaInterface;
	
	@GetMapping("/usuario/all")
	public String getUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
		List<Usuario> usuarios = usuarioInterface.findAll();
		List<Rol> roles = rolInterface.findAll();
		List<Persona> personas = personaInterface.findAll();
		model.addAttribute("listaUsuario", usuarios);
		model.addAttribute("listaRol", roles);
		model.addAttribute("listaPersona", personas);
		return "views/usuario";
	}
	
	@PostMapping("/usuario/create")
	public String usuarioCreate(Usuario usuario, BindingResult result, Model model) {
		if(usuario.getIdUsuario() == 0) {
			usuarioInterface.save(usuario);
		}else {
			usuarioInterface.update(usuario);
		}
		return"redirect:/usuario/all";
	}
	
	@GetMapping("/usuario/delete/{id}")
	public String usuarioDelete(@PathVariable("id")long id) {
		usuarioInterface.delete(id);
		return "redirect:/usuario/all";
	}
	
	@GetMapping("/usuario/one/{id}")
	public String OneUsuario(@PathVariable("id") long id, @ModelAttribute("usuario")Usuario usuario, BindingResult result, Model model) {
		usuario = usuarioInterface.findById(id);
		List<Usuario> usuarios = usuarioInterface.findAll();
		List<Rol> roles = rolInterface.findAll();
		model.addAttribute("listaUsuario", usuarios);
		model.addAttribute("listaRol", roles);
		model.addAttribute("usuario", usuario);
		return"views/usuario";
	}
	
	@GetMapping("/usuario/email/{email}")
	public String EmailUsuario(@PathVariable("email") String email, @ModelAttribute("usuario")Usuario usuario, BindingResult result, Model model) {
		usuario = usuarioInterface.findByCorreo(email);
		List<Usuario> usuarios = usuarioInterface.findAll();
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaUsuario", usuarios);
		return "views/usuario";
	}
	
	@GetMapping("/usuario/status/{email}")
	public String statusUsuario(@PathVariable("status") long status, @ModelAttribute("usuario")Usuario usuario, BindingResult result, Model model) {
		List <Usuario>usuariosStatus = usuarioInterface.findByStatus(status);
		List<Usuario> usuarios = usuarioInterface.findAll();
		model.addAttribute("ListaStatus", usuariosStatus);
		model.addAttribute("listaUsuario", usuarios);
		return "views/usuario";
	}
}
