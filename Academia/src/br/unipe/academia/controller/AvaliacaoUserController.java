package br.unipe.academia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.AvaliacaoService;
import br.unipe.academia.services.MedicaoService;
import br.unipe.academia.services.MedidaAvaliacaoService;

@Controller
@RequestMapping("/avaliacaouser")
public class AvaliacaoUserController {
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private AlunoService  alunoService;
	
	@Autowired
	private MedicaoService medicaoService;
	
	@Autowired
	private MedidaAvaliacaoService medidaAvaliacaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(HttpSession sessao, ModelMap modelMap){
		List<Avaliacao> avaliacoes = null;
		Aluno aluno  = (Aluno) sessao.getAttribute("alunoLogado");
		Aluno alunoUser = alunoService.buscarPorId(aluno.getId());
		avaliacoes = avaliacaoService.listarAvaliacaoApartirDeAluno(alunoUser.getNome());
		modelMap.addAttribute("avaliacoes",avaliacoes);
		modelMap.addAttribute("aluno",alunoUser);
		return "avaliacaouser/listar";
	}
	
	@RequestMapping(value="/detalhar/{id}",method=RequestMethod.POST)
	public String form(@PathVariable Long id, HttpSession sessao, ModelMap modelMap){
		List<MedidaAvaliacao> medidaAvaliacoes = medidaAvaliacaoService.listarMedidaAvaliacaoApartirDeAvaliacao(id);
		Aluno aluno  = (Aluno) sessao.getAttribute("alunoLogado");
		Aluno alunoUser = alunoService.buscarPorId(aluno.getId());
		modelMap.addAttribute("medidaAvaliacoes",medidaAvaliacoes);
		modelMap.addAttribute("aluno",alunoUser);
		return "avaliacaouser/detalhar";
	}
	
}
