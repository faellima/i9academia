package br.unipe.academia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.FichaExercicio;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.AvaliacaoService;
import br.unipe.academia.services.FichaExercicioService;
import br.unipe.academia.services.FichaService;
import br.unipe.academia.services.MedicaoService;
import br.unipe.academia.services.MedidaAvaliacaoService;

@Controller
@RequestMapping("/fichauser")
public class FichaUsuarioController {
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private AlunoService  alunoService;
	
	@Autowired
	private MedicaoService medicaoService;
	
	@Autowired
	private MedidaAvaliacaoService medidaAvaliacaoService;
	
	@Autowired
	private FichaService fichaService;
	
	@Autowired
	private FichaExercicioService fichaExercicioService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(HttpSession sessao, ModelMap modelMap){
		List<Ficha> fichas = null;
		Aluno aluno  = (Aluno) sessao.getAttribute("alunoLogado");
		Aluno alunoUser = alunoService.buscarPorId(aluno.getId());
		fichas = fichaService.listarPorFichaApartirDoAluno(alunoUser.getId());
		modelMap.addAttribute("fichas",fichas);
		modelMap.addAttribute("aluno",alunoUser);
		return "fichauser/listar";
	}
	
	@RequestMapping(value="/detalhar/{id}",method=RequestMethod.POST)
	public String form(@PathVariable Long id, HttpSession sessao, ModelMap modelMap){
		List<FichaExercicio> fichaExercicios = fichaExercicioService.listarPorFichaExercicoApartirDeFicha(id);
		Aluno aluno  = (Aluno) sessao.getAttribute("alunoLogado");
		Aluno alunoUser = alunoService.buscarPorId(aluno.getId());
		modelMap.addAttribute("fichaExercicios",fichaExercicios);
		modelMap.addAttribute("aluno",alunoUser);
		return "fichauser/detalhar";
	}
}
