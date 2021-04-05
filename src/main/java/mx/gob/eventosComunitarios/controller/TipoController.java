package mx.gob.eventosComunitarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import mx.gob.eventosComunitarios.dao.TipoInterface;
import mx.gob.eventosComunitarios.entity.Tipo;

@Controller
public class TipoController {

	@Autowired
	private TipoInterface tipoInterface;
	
	@GetMapping("/tipo/all")
	public String tipoAll(@ModelAttribute("tipo") Tipo tipo, BindingResult result, Model model) {
		List<Tipo> tipos = tipoInterface.findAll();
		model.addAttribute("listaTipo",tipos);
		return"views/tipo";
	}
	
}
