package controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import models.entity.Prueba;
import models.service.IPruebaService;

@Controller
public class PruebaController {
	
	@Autowired
	private IPruebaService pruebaService;
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Prueba prueba = pruebaService.findOne(id);
		if(prueba==null) {
			flash.addFlashAttribute("error","Prueba no existe en la BBDD");
			return "redirect:/listarp";
		}
		
		model.put("prueba", prueba);
		model.put("titulo", "Detalle Material: " + prueba.getNombre());
		return "ver";
	}
	
	@RequestMapping(value="/listarp", method=RequestMethod.GET)
	public String listarp(Model model) {
		
		model.addAttribute("titulo", "Listado de Materiales");
		model.addAttribute("prueba", pruebaService.findAll());
		return "listarp";
	}
	
	@RequestMapping(value="/formp")
	public String crear(Map<String, Object> model) {
		
		Prueba prueba = new Prueba();
		model.put("prueba",prueba);
		model.put("titulo", "Formulario de Material");
		return "formp";
	}
	
	@RequestMapping(value="/formp/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Prueba prueba = null;
		

		if(id>0) {
			prueba = pruebaService.findOne(id);
			if(prueba == null) {
				flash.addFlashAttribute("error", "Prueba no existe en la BBDD!");
				return "redirect:/listarp";
			}
		}else {
			flash.addFlashAttribute("error", "Pruebacreado no puede ser cero!");
			return "redirect:/listarp";
		}
		model.put("prueba",prueba);
		model.put("titulo", "Editar Prueba");
		return "formp";
	}
	
	@RequestMapping(value="/eliminarp/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id>0) {
			
			pruebaService.delete(id);
			flash.addFlashAttribute("success", "Prueba eliminado con éxito!");
			
		}
		return "redirect:/listarp";
		
	}
	
	
}
