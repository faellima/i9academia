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
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.services.ModalidadeService;

@Controller
@RequestMapping("/modalidade")
public class ModalidadeController {
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Modalidade> modalidades = modalidadeService.listarModalidade();
		modelMap.addAttribute("modalidades",modalidades);
		modelMap.addAttribute("modalidade",new Modalidade());
		return "modalidade/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap, HttpSession session){
		session.setAttribute("nameError", null);
		modelMap.addAttribute("modalidade",new Modalidade());
		return "modalidade/criar";
	}
	
	@RequestMapping(value="criar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Modalidade modalidade, BindingResult result, ModelMap modelMap, HttpSession session ){
		if(result.hasErrors()){
			if(modalidade.getNome() == null || modalidade.getNome().equals("")){
				modelMap.addAttribute("modalidade", modalidade);
				session.setAttribute("nameError", "Insira um nome");
			}
			modelMap.addAttribute("modalidade", modalidade);
			return "modalidade/criar";
		}
		
		if(modalidade.getNome() == null || modalidade.getNome().equals("")){
			modelMap.addAttribute("modalidade", modalidade);
			session.setAttribute("nameError", "Insira um nome");
			return "modalidade/criar";
		}
		
		modalidadeService.salvarModalidade(modalidade);
		return "redirect:/modalidade";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		modalidadeService.excluir(new Modalidade(id));
		return "redirect:/modalidade";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
		Modalidade modalidade = modalidadeService.buscar(new Modalidade(id));
		session.setAttribute("nameError", null);
		modelMap.addAttribute("modalidade", modalidade);
		return "modalidade/editar";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String atualizar(@ModelAttribute @Valid Modalidade modalidade, ModelMap modelMap, BindingResult result, HttpSession session){
		if(result.hasErrors()){
			if(modalidade.getNome() == null || modalidade.getNome().equals("")){
				modelMap.addAttribute("modalidade", modalidade);
				session.setAttribute("nameError", "Insira um nome");
			}
			
			modelMap.addAttribute("modalidade", modalidade);
			return "modalidade/editar";
		}
		
		if(modalidade.getNome() == null || modalidade.getNome().equals("")){
			modelMap.addAttribute("modalidade", modalidade);
			session.setAttribute("nameError", "Insira um nome");
			return "modalidade/editar";
		}
		modalidadeService.atualizar(modalidade);
		return "redirect:/modalidade";
	}
	
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid Modalidade modalidade,ModelMap modelMap ){
		List<Modalidade> modalidades = null;
		modalidades = modalidadeService.listarPorNome(modalidade.getNome());
		modelMap.addAttribute("modalidades",modalidades);
		modelMap.addAttribute("exercicio", new Exercicio());
		return "modalidade/listar";
	}
	
	
}
