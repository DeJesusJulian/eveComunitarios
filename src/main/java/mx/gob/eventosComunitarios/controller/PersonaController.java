package mx.gob.eventosComunitarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mx.gob.eventosComunitarios.dao.PersonaInterface;
import mx.gob.eventosComunitarios.entity.Persona;

@Controller
public class PersonaController {
	
	@Autowired
	private PersonaInterface personaInterface;
	
	@GetMapping("/persona/all")
	public String getAllPerson(@ModelAttribute("persona") Persona persona, BindingResult result, Model model) {
		List<Persona> listaPersona = personaInterface.findAll();
		model.addAttribute("listaPersona",listaPersona);
		return "views/persona";
	}
	
	
	@PostMapping("/persona/create")
	public String personaCreate(Persona persona, BindingResult result, Model model) {
		if(persona.getIdPersona()==0) {
			personaInterface.save(persona);
		}else {
			personaInterface.update(persona);
		}
		return "redirect:/persona/all";
	}
	
	@GetMapping("/persona/find/{id}")
	public String personaFind(@PathVariable("id") long id, @ModelAttribute("persona") Persona persona, BindingResult result, Model model) {
		persona = personaInterface.findById(id);
		
		List<Persona> personas = personaInterface.findAll();
		model.addAttribute("listaPersona", personas);
		model.addAttribute("persona", persona);
		return"views/persona";
	}
	
	@GetMapping("/persona/bye/{id}")
	public String PersonaBye(@PathVariable("id") long id) {
		personaInterface.delete(id);
		return "redirect:/persona/all";
	}
	
}
