package br.unipe.academia.controller;

import java.util.ArrayList;
import java.util.Date;
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
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.AvaliacaoService;
import br.unipe.academia.services.MedicaoService;
import br.unipe.academia.services.MedidaAvaliacaoService;
@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private AlunoService  alunoService;
	
	@Autowired
	private MedicaoService medicaoService;
	
	@Autowired
	private MedidaAvaliacaoService medidaAvaliacaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Avaliacao> avaliacoes = avaliacaoService.listarAvaliacao();
		modelMap.addAttribute("avaliacoes",avaliacoes);
		modelMap.addAttribute("aluno",new Aluno());
		return "avaliacao/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap, HttpSession sessao){
		sessao.setAttribute("carrinho", new ArrayList<MedidaAvaliacao>());
		sessao.setAttribute("carrinhoErro", null);
		sessao.setAttribute("valorError", null);
		preCondition(modelMap);
		return "avaliacao/novo";
	}
	
	@RequestMapping(value="adicionarMedida", method=RequestMethod.POST)
	public String adicionarCarrinho(@ModelAttribute @Valid MedidaAvaliacao medidaAvaliacao, HttpSession sessao, ModelMap modelMap ){
		Avaliacao avaliacao = new Avaliacao();
		Date data= new Date();
		
		if(medidaAvaliacao.getValor() <= 0){
			sessao.setAttribute("valorError", "Valor invalido");
			preCondition(modelMap);
			return "avaliacao/novo";
		}
		
		sessao.setAttribute("valorError", null);
		sessao.setAttribute("carrinhoErro", null);
		Medicao medicao  = medicaoService.buscarPorId(medidaAvaliacao.getMedicao().getId());
		Aluno aluno_1 = (Aluno) alunoService.buscarPorId(medidaAvaliacao.getAvaliacao().getAluno().getId());
		avaliacao.setAluno(aluno_1);
		avaliacao.setData(data);
		medidaAvaliacao.setAvaliacao(avaliacao);
		medidaAvaliacao.setMedicao(medicao);
		List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
		carrinho.add(medidaAvaliacao);
		preCondition(modelMap);
		return "avaliacao/novo";
	}
	
	@RequestMapping(value="{linha}/remover", method=RequestMethod.POST) 
	public String removerItem(@PathVariable int linha, ModelMap modelMap, HttpSession sessao){
		List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
		carrinho.remove(linha);
		preCondition(modelMap);
		return "avaliacao/novo";
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST) 
	public String salvar(@ModelAttribute @Valid MedidaAvaliacao medidaAvaliacao, BindingResult result, ModelMap modelMap, HttpSession sessao){
		List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
		Avaliacao avaliacao = new Avaliacao();
		Medicao medicao = new Medicao();
		if(carrinho == null || carrinho.isEmpty()){
			sessao.setAttribute("carrinhoErro", "Insira pelo menos uma medida");
			preCondition(modelMap);
			return "avaliacao/novo";
		}
		for (MedidaAvaliacao medidasAvaliacao : carrinho) {
			Aluno aluno = (Aluno) alunoService.buscarPorId(medidasAvaliacao.getAvaliacao().getAluno().getId());
			
			medicao.setNome(medidasAvaliacao.getMedicao().getNome());
			medicao.setMedidaavaliacoes(carrinho);
			medicaoService.salvarMedicao(medicao);

			avaliacao.setAluno(aluno);
			avaliacao.setMedidasavaliacoes(carrinho);
			avaliacaoService.salvarAvaliacao(avaliacao);
			
			medidasAvaliacao.setAvaliacao(avaliacao);
			medidasAvaliacao.setMedicao(medicao);
			
			medidaAvaliacaoService.salvarMedidaAvaliacao(medidasAvaliacao);
			
			avaliacao = new Avaliacao();
			medicao = new Medicao();
		}
		
		//avaliacao.setMedidasavaliacoes(carrinho);
		return "redirect:/avaliacao";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long id){
		List<MedidaAvaliacao> medidaAvaliacaos = medidaAvaliacaoService.listarMedidaAvaliacao();
		for (MedidaAvaliacao medidaAvaliacao : medidaAvaliacaos) {
			if(medidaAvaliacao.getAvaliacao().getId() != null){
				if (medidaAvaliacao.getAvaliacao().getId() == id){
					medidaAvaliacaoService.excluirMedidaAvaliacao(medidaAvaliacao);
				}
			}
		}

		Avaliacao avaliacao = (Avaliacao)avaliacaoService.buscarPorId(id);
		avaliacaoService.exluirAvaliacao(new Avaliacao(id));
		return "redirect:/avaliacao";
	}
	
//	@RequestMapping(value = "{id}", method = RequestMethod.POST)
//	public String editForm(@PathVariable Long id, ModelMap modelMap) {
//		Avaliacao avaliacao = avaliacaoService.buscar(new Avaliacao(id));
//		modelMap.addAttribute("avaliacao", avaliacao);
//		return "avaliacao/editar";
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT)
//	public String atualizar(@ModelAttribute @Valid Modalidade modalidade, ModelMap modelMap, BindingResult result){
//		if(result.hasErrors()){
//			modelMap.addAttribute("modalidade", modalidade);
//			return "modalidade/editar";
//		}
//		//modalidadeService.atualizar(modalidade);
//		return "redirect:/modalidade";
//	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession sessao) {
		sessao.setAttribute("valorError", null);
		sessao.setAttribute("carrinhoErro", null);
		List<MedidaAvaliacao> medidaAvaliacoes = medidaAvaliacaoService.listarMedidaAvaliacaoApartirDeAvaliacao(id);
		Avaliacao avaliacao = avaliacaoService.buscarPorId(id);
		sessao.setAttribute("carrinho", new ArrayList<MedidaAvaliacao>());
		List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
		for (MedidaAvaliacao medidaAvaliacoesCarrinho : medidaAvaliacoes) {
			carrinho.add(medidaAvaliacoesCarrinho);
		}
		modelMap.addAttribute("avaliacao", avaliacao);
		
		preCondition(modelMap);
		return "avaliacao/editar";
	}
	
	@RequestMapping(value="adicionarAvaliacaoEditada", method=RequestMethod.POST)
	public String adicionarCarrinhoEditado(@ModelAttribute @Valid MedidaAvaliacao medidaAvaliacao, HttpSession sessao, ModelMap modelMap ){
		Date data = new Date();
		Long id = null;
		
		if(medidaAvaliacao.getValor() <= 0){
			sessao.setAttribute("valorError", "Valor invalido");
			preCondition(modelMap);
			return "avaliacao/editar";
		}
		
		List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
		if(!(carrinho.isEmpty())){
			for (MedidaAvaliacao medidaAvaliacoes : carrinho) {
				if(medidaAvaliacoes.getAvaliacao().getId() != null){
					id = medidaAvaliacoes.getAvaliacao().getId();
				}
			}
		}
		
		Avaliacao avaliacao = avaliacaoService.buscarPorId(id);
		
		Medicao medicao  = medicaoService.buscarPorId(medidaAvaliacao.getMedicao().getId());
		Aluno aluno_1 = (Aluno) alunoService.buscarPorId(medidaAvaliacao.getAvaliacao().getAluno().getId());
		avaliacao.setAluno(aluno_1);
		avaliacao.setData(data);
		medidaAvaliacao.setAvaliacao(avaliacao);
		medidaAvaliacao.setMedicao(medicao);
		
		carrinho.add(medidaAvaliacao);
		preCondition(modelMap);
		return "avaliacao/editar";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String atualizar(@ModelAttribute @Valid MedidaAvaliacao medidaAvaliacao, BindingResult result, ModelMap modelMap, HttpSession sessao){
			List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
			Long id = null;
			
			if(carrinho == null || carrinho.isEmpty()){
				sessao.setAttribute("carrinhoErro", "Adicione pelo menos uma medida");
				preCondition(modelMap);
				return "avaliacao/editar";
			}
			
			for (MedidaAvaliacao medidaAvaliacoes : carrinho) {
				Avaliacao avaliacao = avaliacaoService.buscarPorId(medidaAvaliacoes.getAvaliacao().getId());
				avaliacaoService.atualizarAvaliacao(avaliacao);
				medidaAvaliacaoService.atualizarMedidaAvaliacao(medidaAvaliacoes);
			}
			
			return "redirect:/avaliacao";
	}
	
	@RequestMapping(value="{linha}/removerEditados", method=RequestMethod.POST) 
	public String removerItemEditavel(@PathVariable int linha, ModelMap modelMap, HttpSession sessao){
		List<MedidaAvaliacao> carrinho = (List<MedidaAvaliacao>)sessao.getAttribute("carrinho");
		carrinho.remove(linha);
		preCondition(modelMap);
		return "avaliacao/editar";
	}
	
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid Aluno aluno,ModelMap modelMap ){
		List<Avaliacao> avaliacoes = null;
		avaliacoes = avaliacaoService.listarAvaliacaoApartirDeAluno(aluno.getNome());
		modelMap.addAttribute("avaliacoes",avaliacoes);
		modelMap.addAttribute("aluno",new Aluno());
		return "avaliacao/listar";
	}
	
	public void preCondition(ModelMap modelMap){
		List<Aluno> alunos = alunoService.listarAluno();
		List<Medicao> medicoes = medicaoService.listarMedicao();
		Map<Long, String> alunosMap = new TreeMap<Long, String>();
		for (Aluno aluno : alunos) {
			alunosMap.put(aluno.getId(), aluno.getNome());
		}
		Map<Long, String> medicoesMap = new TreeMap<Long, String>();
		for (Medicao medicao : medicoes) {
			medicoesMap.put(medicao.getId(),medicao.getNome());
		}
		
		modelMap.addAttribute("alunos", alunosMap);
		modelMap.addAttribute("medicoes", medicoesMap);
		modelMap.addAttribute("medidaAvaliacao", new MedidaAvaliacao());
	}
	
}
