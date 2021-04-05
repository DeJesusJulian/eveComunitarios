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

import mx.gob.eventosComunitarios.dao.InscripcionEquipoInterface;
import mx.gob.eventosComunitarios.dao.OfertaInterface;
import mx.gob.eventosComunitarios.dao.UsuarioInterface;
import mx.gob.eventosComunitarios.entity.InscripcionEquipo;
import mx.gob.eventosComunitarios.entity.Oferta;
import mx.gob.eventosComunitarios.entity.Usuario;

@Controller
public class InscripcionEquipoController {

	
	@Autowired
	private InscripcionEquipoInterface equipoInterface;
	@Autowired
	private UsuarioInterface usuarioInterface;
	@Autowired
	private OfertaInterface OfertaInterface;
	
	@GetMapping("/inscripcionEquipo/all")
	public String getEquipo(@ModelAttribute("inscripcionEquipo")InscripcionEquipo inscripcionEquipo, BindingResult result, Model model) {
		List<InscripcionEquipo> equipos = equipoInterface.findAll();
		List<Usuario> usuarios = usuarioInterface.findAll();
		List<Oferta> ofertas = OfertaInterface.findAll();
		model.addAttribute("listaUsuario", usuarios);
		model.addAttribute("listaOfertas", ofertas);
		model.addAttribute("listaEquipo", equipos);
		return "views/equipo";
	}
	
	@PostMapping("/inscripcion/equipo")
	public String createEquipo(@ModelAttribute("inscripcionEquipo") InscripcionEquipo inscripcionEquipo,
			@ModelAttribute("usuarioName")int usuarioName,@ModelAttribute("ofertaId")int ofertaId, 
			BindingResult result, Model model) {
		if(inscripcionEquipo.getIdEquipo() == 0) {
			equipoInterface.save(inscripcionEquipo,usuarioName,ofertaId);
		}else {
			equipoInterface.update(inscripcionEquipo,usuarioName,ofertaId);
		}
		return "redirect:/inscripcionEquipo/all";
	}
	
	@GetMapping("/inscripcion/equipo/delete/{id}")
	public String deleteEquipo(@PathVariable("id")long id, @ModelAttribute("inscripcionEquipo")InscripcionEquipo equipo, BindingResult result, Model model) {
		equipoInterface.delete(id);
		return"redirect:/inscripcionEquipo/all";
	}
	
	@GetMapping("/equipo/one/{id}")
	public String oneEquipo(@PathVariable("id")long id, @ModelAttribute("inscripcionEquipo")InscripcionEquipo equipo, BindingResult result, Model model) {
			equipo = equipoInterface.findById(id);
			model.addAttribute("equipo", equipo);
			
			List<InscripcionEquipo> equipos = equipoInterface.findAll();
			List<Usuario> usuarios = usuarioInterface.findAll();
			List<Oferta> ofertas = OfertaInterface.findAll();
			model.addAttribute("listaUsuario", usuarios);
			model.addAttribute("listaOfertas", ofertas);
			model.addAttribute("listaEquipo", equipos);
			
		return "views/equipo";
	}
	/*
	@GetMapping("/equipo/one/{id}")
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
