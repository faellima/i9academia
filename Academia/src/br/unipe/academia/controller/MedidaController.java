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

import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.services.MedicaoService;

@Controller
@RequestMapping("/medida")
public class MedidaController {

	@Autowired
	private MedicaoService medicaoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Medicao> medicoes = medicaoService.listarMedicao();
		modelMap.addAttribute("medidas", medicoes);
		modelMap.addAttribute("medicao",new Medicao());
		return "medida/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap, HttpSession session){
		session.setAttribute("nameError", null);
		modelMap.addAttribute("medida",new Medicao());
		return "medida/criar";
	}
	
	@RequestMapping(value="criar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Medicao medida, BindingResult result, ModelMap modelMap, HttpSession session ){
		if(result.hasErrors()){
			if(medida.getNome() == null || medida.getNome().equals("")){
				modelMap.addAttribute("medida", medida);
				session.setAttribute("nameError", "Insira um nome");
			}
			
			modelMap.addAttribute("medida", medida);
			return "medida/criar";
		}
		
		if(medida.getNome() == null || medida.getNome().equals("")){
			modelMap.addAttribute("medida", medida);
			session.setAttribute("nameError", "Insira um nome");
			
			return "medida/criar";
		}
		
		medicaoService.salvarMedicao(medida);
		return "redirect:/medida";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		medicaoService.exluirMedicao(new Medicao(id));
		return "redirect:/medida";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
		session.setAttribute("nameError", null);
		Medicao medida = medicaoService.buscar(new Medicao(id));
		modelMap.addAttribute("medida", medida);
		return "medida/editar";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String atualizar(@ModelAttribute @Valid Medicao medida, ModelMap modelMap, BindingResult result, HttpSession session){
		if(result.hasErrors()){
			if(medida.getNome() == null || medida.getNome().equals("")){
				modelMap.addAttribute("medida", medida);
				session.setAttribute("nameError", "Insira um nome");
				
				return "medida/editar";
			}
			modelMap.addAttribute("medida", medida);
			return "medida/editar";
		}
		
		if(medida.getNome() == null || medida.getNome().equals("")){
			modelMap.addAttribute("medida", medida);
			session.setAttribute("nameError", "Insira um nome");
			
			return "medida/editar";
		}
		
		medicaoService.atualizarMedicao(medida);
		return "redirect:/medida";
	}
	
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid Medicao medicao,ModelMap modelMap ){
		List<Medicao> medicoes = null;
		medicoes = medicaoService.listarPorAtributoNome(medicao.getNome());
		modelMap.addAttribute("medidas",medicoes);
		modelMap.addAttribute("medicao",new Medicao());
		return "medida/listar";
	}
}
