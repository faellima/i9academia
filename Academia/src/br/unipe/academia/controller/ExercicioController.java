package br.unipe.academia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.services.ExercicioService;

@Controller
@RequestMapping("/exercicio")
public class ExercicioController {
	
	@Autowired
	private ExercicioService exercicioService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Exercicio> exercicios = exercicioService.listarExercicio();
		modelMap.addAttribute("exercicios", exercicios);
		modelMap.addAttribute("exercicio", new Exercicio());
		return "exercicio/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap, HttpSession session){
		session.setAttribute("nameError", null);
		modelMap.addAttribute("exercicio",new Exercicio());
		return "exercicio/criar";
	}
	
	@RequestMapping(value="criar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Exercicio exercicio, BindingResult result, ModelMap modelMap, HttpSession session ){
		session.setAttribute("nameError", null);
		if(result.hasErrors()){
			if(exercicio.getNome() == null || exercicio.getNome().equals("")){
				modelMap.addAttribute("exercicio", exercicio);
				session.setAttribute("nameError", "Insira um nome");
			}
			return "exercicio/criar";
		}
		
		if(exercicio.getNome() == null || exercicio.getNome().equals("")){
			modelMap.addAttribute("exercicio", exercicio);
			session.setAttribute("nameError", "Insira um nome");
			return "exercicio/criar";
		}
		
		exercicioService.SalvarExercicio(exercicio);
		return "redirect:/exercicio";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		exercicioService.ExluirExercicio(new Exercicio(id));
		return "redirect:/exercicio";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
		session.setAttribute("nameError", null);
		Exercicio exercicio = exercicioService.buscar(new Exercicio(id));
		modelMap.addAttribute("exercicio", exercicio);
		return "exercicio/editar";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String atualizar(@ModelAttribute @Valid Exercicio exercicio, ModelMap modelMap, BindingResult result, HttpSession session){
		if(result.hasErrors()){
			if(exercicio.getNome() == null || exercicio.getNome().equals("")){
				modelMap.addAttribute("exercicio", exercicio);
				session.setAttribute("nameError", "Insira um nome");
				return "exercicio/editar";
			}
			
			modelMap.addAttribute("exercicio", exercicio);
			return "exercicio/editar";
		}
		if(exercicio.getNome() == null || exercicio.getNome().equals("")){
			modelMap.addAttribute("exercicio", exercicio);
			session.setAttribute("nameError", "Insira um nome");
			return "exercicio/editar";
		}
		exercicioService.AtualizarExercicio(exercicio);
		return "redirect:/exercicio";
	}
	
	
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid Exercicio exercicio,ModelMap modelMap ){
		List<Exercicio> exercicios = null;
		exercicios = exercicioService.listarPorAtributoNome(exercicio.getNome());
		modelMap.addAttribute("exercicios",exercicios);
		modelMap.addAttribute("exercicio", new Exercicio());
		return "exercicio/listar";
	}
	
}
