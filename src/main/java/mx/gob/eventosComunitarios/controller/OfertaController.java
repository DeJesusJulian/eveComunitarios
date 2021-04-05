package mx.gob.eventosComunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import mx.gob.eventosComunitarios.dao.EventoInterface;
import mx.gob.eventosComunitarios.dao.HorarioInterface;
import mx.gob.eventosComunitarios.dao.OfertaInterface;
import mx.gob.eventosComunitarios.entity.Evento;
import mx.gob.eventosComunitarios.entity.Horario;
import mx.gob.eventosComunitarios.entity.Modalidad;
import mx.gob.eventosComunitarios.entity.Oferta;
import mx.gob.eventosComunitarios.entity.Tipo;

@Controller
public class OfertaController {
	
	@Autowired
	private OfertaInterface ofertaInterface;
	@Autowired
	private EventoInterface eventoInterface;
	@Autowired
	private HorarioInterface horarioInterface;
	
	/*
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    ApiError handleMethodArgumentNotValid(MissingServletRequestParameterException ex) {

        return new ApiError(ErrorCode.MISSING_REQUIRED_PARAMS, ex.getMessage());
    }*/
	
	@GetMapping("/oferta/all")
	public String getAll(@ModelAttribute("oferta") Oferta oferta, BindingResult result, Model model) {
		List<Oferta> ofertas = ofertaInterface.findAll();
		List<Evento> eventos = eventoInterface.findAll();
		List<Horario> horarios = horarioInterface.findAll();
		model.addAttribute("eventos", eventos);
		model.addAttribute("horarios", horarios);
		model.addAttribute("listaOferta",ofertas);
		return "views/oferta";
	}
	
	@PostMapping("/oferta/save")
	public String saveOferta(Oferta oferta, 
			BindingResult result, 
			Model model) {
		
		
		System.out.println("Entre al oferta save");
		if(oferta.getIdOferta()==0) {
			oferta.setStatus(1);
			ofertaInterface.save(oferta);
		}else {
			ofertaInterface.update(oferta, 1, 1);
		}
		return"redirect:/oferta/all";
	}
	
	@GetMapping("/oferta/delete/{id}")
	public String byeOferta(@PathVariable("id") long id) {
			ofertaInterface.delete(id);
		return"redirect:/oferta/all";
	}
	
	@GetMapping("/oferta/one/{id}")
	public String oneOferta(@PathVariable("id") long id, @ModelAttribute("oferta") Oferta oferta, BindingResult result, Model model) {
		oferta = ofertaInterface.findById(id);
		List<Evento> eventos = eventoInterface.findAll();
		List<Horario> horarios = horarioInterface.findAll();
		model.addAttribute("eventos", eventos);
		model.addAttribute("horarios", horarios);
		List<Oferta> ofertas = ofertaInterface.findAll();
		model.addAttribute("listaOferta",ofertas);
		model.addAttribute("oferta", oferta);
		System.out.println(oferta.getEvento().getIdEvento());
		return "views/oferta";
	}
	

}
