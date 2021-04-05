package mx.gob.eventosComunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import mx.gob.eventosComunitarios.dao.InscripcionPersonaInterface;
import mx.gob.eventosComunitarios.dao.OfertaInterface;
import mx.gob.eventosComunitarios.dao.UsuarioInterface;
import mx.gob.eventosComunitarios.entity.InscripcionPersona;
import mx.gob.eventosComunitarios.entity.Oferta;
import mx.gob.eventosComunitarios.entity.Usuario;

@Controller
public class InscripcionPersonaCOntroller {

	@Autowired
	private InscripcionPersonaInterface inscripcionPersonaInterface;
	@Autowired
	private UsuarioInterface usuarioInterface;
	@Autowired
	private OfertaInterface ofertaInterface;

	@GetMapping("/inscripcionPersona/all")
	public String getPersona(@ModelAttribute("inscripcionPersona") InscripcionPersona persona, BindingResult result,
			Model model) {
		List<InscripcionPersona> personas = inscripcionPersonaInterface.findAll();
		List<Usuario> usuarios = usuarioInterface.findAll();
		List<Oferta> ofertas = ofertaInterface.findAll();
		model.addAttribute("listaUsuario", usuarios);
		model.addAttribute("listaOfertas", ofertas);
		model.addAttribute("listaPersona", personas);
		return "views/inscripcionPersona";
	}

	@PostMapping("/inscripcion/persona")
	public String createPersona(@ModelAttribute("inscripcionPersona") InscripcionPersona persona, BindingResult result,
			Model model) {
		inscripcionPersonaInterface.save(persona);
		return "redirect:/inscripcionEquipo/all";
	}
	
	@GetMapping("/inscripcion/persona/delete/{id}")
	public String deletePersona(@PathVariable("id")long id, @ModelAttribute("inscripcionPersona")InscripcionPersona persona, BindingResult result, Model model) {
		inscripcionPersonaInterface.delete(id);
		return"redirect:/inscripcionPersona/all";
	}
	
	/*
	@GetMapping("/persona/user/{id}")
	public String userEquipo(@PathVariable("id")long id, @ModelAttribute("inscripcionEquipo")InscripcionEquipo equipo, BindingResult result, Model model) {
			equipo = equipoInterface.findByUser(id, id);
			model.addAttribute("equipo", equipo);
			
			List<InscripcionEquipo> equipos = equipoInterface.findAll();
			List<Usuario> usuarios = usuarioInterface.findAll();
			List<Oferta> ofertas = OfertaInterface.findAll();
			model.addAttribute("listaUsuario", usuarios);
			model.addAttribute("listaOfertas", ofertas);
			model.addAttribute("listaEquipo", equipos);
			
		return "views/equipo";
	}
	como se recibimos los 2 valores
	*/
}
