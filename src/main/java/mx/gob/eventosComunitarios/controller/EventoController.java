package mx.gob.eventosComunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mx.gob.eventosComunitarios.dao.EventoInterface;
import mx.gob.eventosComunitarios.dao.ModalidadInterface;
import mx.gob.eventosComunitarios.dao.TipoInterface;
import mx.gob.eventosComunitarios.entity.Evento;
import mx.gob.eventosComunitarios.entity.Modalidad;
import mx.gob.eventosComunitarios.entity.Tipo;

@Controller
public class EventoController {
	@Autowired
	private EventoInterface eventoInterface;
	@Autowired
	private TipoInterface tipoInterfaces;
	@Autowired
	private ModalidadInterface modalidadInterfaces;
	
	@GetMapping("/evento/all")
	public String getAll(@ModelAttribute("evento") Evento evento, BindingResult result, Model model) {
		List<Evento> eventos = eventoInterface.findAll();
		List<Tipo> tipos = tipoInterfaces.findAll();
		List<Modalidad> modalidades = modalidadInterfaces.findAll();
		model.addAttribute("tipos", tipos);
		model.addAttribute("modalidades", modalidades);
		model.addAttribute("listaEventos",eventos);
		return "views/eventos";
	}
	
	@PostMapping("/evento/save")
	public String saveEvento(Evento evento,@ModelAttribute("tipoName")int tipoName,@ModelAttribute("modalidadName") int modalidadName, BindingResult result, Model model) {
		if(evento.getIdEvento()==0) {
			evento.setStatus(1);
			eventoInterface.save(evento, tipoName, modalidadName);
		}else {
			eventoInterface.update(evento, tipoName, modalidadName);
		}
		return"redirect:/evento/all";
	}
	
	@GetMapping("/evento/bye/{id}")
	public String byeEvento(@PathVariable("id") long id) {
			eventoInterface.deleteLogical(id);
		return"redirect:/evento/all";
	}
	
	@GetMapping("/evento/one/{id}")
	public String oneEvento(@PathVariable("id") long id, @ModelAttribute("evento") Evento evento, BindingResult result, Model model) {
		evento = eventoInterface.findById(id);
		List<Tipo> tipos = tipoInterfaces.findAll();
		List<Modalidad> modalidades = modalidadInterfaces.findAll();
		model.addAttribute("tipos", tipos);
		model.addAttribute("modalidades", modalidades);
		List<Evento> eventos = eventoInterface.findAll();
		model.addAttribute("listaEventos",eventos);
		model.addAttribute("evento", evento);
		return "views/eventos";
	}
	
	
}
