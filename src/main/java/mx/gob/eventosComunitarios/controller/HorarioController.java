package mx.gob.eventosComunitarios.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mx.gob.eventosComunitarios.dao.HorarioInterface;
import mx.gob.eventosComunitarios.entity.Horario;




@Controller
public class HorarioController {
	
	@Autowired
	private HorarioInterface horarioInterface;
	
	@GetMapping("/horario/all")
	public String getHorarios(@ModelAttribute("horario") Horario horario, BindingResult result, Model model) {
		List<Horario> horarios = horarioInterface.findAll();
		model.addAttribute("listaHorarios", horarios);
		return "views/horario";
	}
	
	
	@PostMapping("/horario/save")
	public String createHorario(Horario horario, BindingResult result, Model model) {
		if(horario.getIdHorarios() == 0) {
			horarioInterface.save(horario);
		}else {
			horarioInterface.update(horario);
		}
		return "redirect:/horario/all";
	}
	
	
	/*@GetMapping("/horario/delete/{id}")
	public String deleteHorario(@PathVariable("id") long id, BindingResult result, Model model) {
		horarioInterface.deleteLogical(id);
		return "redirect:/horario/all";
	}*/
	
	
	@GetMapping("/horario/delete/{id}")
	public String deleteHorario(@PathVariable("id") long id) {
		horarioInterface.deleteLogical(id);
		return "redirect:/horario/all";
	}
	
	@GetMapping("/horario/one/{id}")
	public String getHorarioId(@PathVariable("id")long id, @ModelAttribute("horario")Horario horario, BindingResult result, Model model) {
		horario = horarioInterface.findById(id);
		List<Horario> horarios = horarioInterface.findAll();
		model.addAttribute("listaHorarios", horarios);
		model.addAttribute("horario", horario);
		return "views/horario";
	}
	
	@GetMapping("/horario/day/{day}")
	public String getHorarioDay(@PathVariable("day")String day, @ModelAttribute("horario")Horario horario, BindingResult result, Model model) {
		List<Horario> horarios = horarioInterface.findByDay(day);
		model.addAttribute("listaHorarios", horarios);
		return "views/horario";
	}
	
	
	@GetMapping("/horario/hour/{hour}")
	public String getHorarioHour(@PathVariable("hour")String hour, @ModelAttribute("horario")Horario horario, BindingResult result, Model model) {
		List<Horario> horarios = horarioInterface.findByHour(hour);
		model.addAttribute("listaHorarios", horarios);
		return "views/horario";
	}
	
	
}
