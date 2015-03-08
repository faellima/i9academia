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
import br.unipe.academia.persistence.entity.AlunoModalidade;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.FichaExercicio;
import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.persistence.entity.Usuario;
import br.unipe.academia.services.AlunoModalidadeService;
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.ExercicioService;
import br.unipe.academia.services.FichaExercicioService;
import br.unipe.academia.services.FichaService;
import br.unipe.academia.services.ModalidadeService;
import br.unipe.academia.services.UsuarioService;

@Controller
@RequestMapping("/ficha")
public class FichaController{
	@Autowired
	private FichaService fichaService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	@Autowired
	private AlunoModalidadeService alunoModalidadeService;
	
	@Autowired
	private ExercicioService exercicioService;
	
	@Autowired
	private FichaExercicioService fichaExercicioService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Ficha> fichas = fichaService.listarFicha();
		preCondition(modelMap);
		modelMap.addAttribute("fichas",fichas);
		return "ficha/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap, HttpSession sessao){
		sessao.setAttribute("carrinho", new ArrayList<FichaExercicio>());
		sessao.setAttribute("repeticaoError", null);
		sessao.setAttribute("SerieError", null);
		sessao.setAttribute("carrinhoError", null);
		preCondition(modelMap);
		return "ficha/novo";
	}
	
	@RequestMapping(value="adicionarFicha", method=RequestMethod.POST)
	public String adicionarCarrinho(@ModelAttribute @Valid FichaExercicio fichaExercicio,ModelMap modelMap, HttpSession sessao ){
		Ficha ficha = new Ficha();
		Date date = new Date();
		sessao.setAttribute("repeticaoError", null);
		sessao.setAttribute("SerieError", null);
		sessao.setAttribute("carrinhoError", null);
		
		if(fichaExercicio.getQtdRepeticoes() == 0){
			sessao.setAttribute("repeticaoError", "As Repetições não podem ser nulas");
			preCondition(modelMap);
			if(fichaExercicio.getQtdSeries()==0){
				sessao.setAttribute("SerieError", "O número de séries não podem ser nulos");
				preCondition(modelMap);
			}
			return "ficha/novo";
		}
		
		if(fichaExercicio.getQtdSeries()==0){
			sessao.setAttribute("SerieError", "O número de séries não podem ser nulos");
			preCondition(modelMap);
			return "ficha/novo";
		}
		
		Exercicio exercicio = exercicioService.buscarPorId(fichaExercicio.getExercicio().getId());
		Aluno aluno = alunoService.buscarPorId(fichaExercicio.getFicha().getAluno().getId());
		ficha.setAluno(aluno);
		ficha.setData(date);
		fichaExercicio.setExercicio(exercicio);
		fichaExercicio.setFicha(ficha);
		List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
		carrinho.add(fichaExercicio);
		preCondition(modelMap);
		return "ficha/novo";
	}
	
	@RequestMapping(value="{linha}/remover", method=RequestMethod.POST) 
	public String removerItem(@PathVariable int linha, ModelMap modelMap, HttpSession sessao){
		List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
		carrinho.remove(linha);
		preCondition(modelMap);
		return "ficha/novo";
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST) 
	public String salvar(HttpSession sessao, ModelMap modelMap){
		sessao.setAttribute("repeticaoError", null);
		sessao.setAttribute("SerieError", null);
		sessao.setAttribute("carrinhoError", null);
		List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
		Exercicio exercicio = new Exercicio();
		Ficha ficha = new Ficha();
		Aluno aluno = new Aluno();
		AlunoModalidade alunoModalidade = new AlunoModalidade();
		sessao.setAttribute("carrinhoError", null);
		
		if(carrinho.isEmpty()){
			sessao.setAttribute("carrinhoError", "Antes de Salvar Adicone algumaFicha");
			preCondition(modelMap);
			return "ficha/novo";
		}
		
		for (FichaExercicio fichaExercicio : carrinho) {
			fichaService.SalvarFicha(fichaExercicio.getFicha());
			fichaExercicioService.SalvarFichaExercicio(fichaExercicio);
		}
		
		return "redirect:/ficha";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long id){
		List<FichaExercicio> fichaExercicios = fichaExercicioService.listarFichaExercicio();
		for (FichaExercicio fichaExercicio : fichaExercicios) {
			if(fichaExercicio.getFicha().getId() != null){
				if (fichaExercicio.getFicha().getId() == id){
					fichaExercicioService.ExcluirFichaExercicio(fichaExercicio);
				}
			}
		}

		fichaService.ExcluirFicha(new Ficha(id));
		return "redirect:/ficha";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession sessao) {
		sessao.setAttribute("repeticaoError", null);
		sessao.setAttribute("SerieError", null);
		sessao.setAttribute("carrinhoError", null);
		List<FichaExercicio> fichaExercicios =  fichaExercicioService.listarPorFichaExercicoApartirDeFicha(id);
		Ficha ficha = fichaService.buscarPorId(id);
		sessao.setAttribute("carrinho", new ArrayList<FichaExercicio>());
		List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
		for (FichaExercicio fichaExercicioCarrinho : fichaExercicios) {
			carrinho.add(fichaExercicioCarrinho);
		}
		modelMap.addAttribute("ficha", ficha);
		
		preCondition(modelMap);
		return "ficha/editar";
	}
	
	@RequestMapping(value="adicionarFichaEditada", method=RequestMethod.POST)
	public String adicionarCarrinhoEditado(@ModelAttribute @Valid FichaExercicio fichaExercicio, HttpSession sessao, ModelMap modelMap ){
		sessao.setAttribute("repeticaoError", null);
		sessao.setAttribute("SerieError", null);
		sessao.setAttribute("carrinhoError", null);
		Date date = new Date();
		Long id = null;
		
		if(fichaExercicio.getQtdRepeticoes() == 0){
			sessao.setAttribute("repeticaoError", "As Repetições não podem ser nulas");
			preCondition(modelMap);
			if(fichaExercicio.getQtdSeries()==0){
				sessao.setAttribute("SerieError", "O número de séries não podem ser nulos");
				preCondition(modelMap);
			}
			return "ficha/editar";
		}
		
		if(fichaExercicio.getQtdSeries()==0){
			sessao.setAttribute("SerieError", "O número de séries não podem ser nulos");
			preCondition(modelMap);
			return "ficha/editar";
		}
		
		
		List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
		if(!(carrinho.isEmpty())){
			for (FichaExercicio fichaExercicioCarrinho : carrinho) {
				if(fichaExercicioCarrinho.getFicha().getId() != null){
					id = fichaExercicioCarrinho.getFicha().getId();
				}
			}
		}
		
		Ficha ficha = fichaService.buscarPorId(id);
		Exercicio exercicio = exercicioService.buscarPorId(fichaExercicio.getExercicio().getId());
		Aluno aluno = alunoService.buscarPorId(fichaExercicio.getFicha().getAluno().getId());
		ficha.setAluno(aluno);
		ficha.setData(date);
		fichaExercicio.setExercicio(exercicio);
		fichaExercicio.setFicha(ficha);
		carrinho.add(fichaExercicio);
		preCondition(modelMap);
		return "ficha/editar";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String atualizar(ModelMap modelMap, HttpSession sessao){
			sessao.setAttribute("repeticaoError", null);
			sessao.setAttribute("SerieError", null);
			sessao.setAttribute("carrinhoError", null);
			List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
			Long id = null;
			
			if(carrinho.isEmpty()){
				sessao.setAttribute("carrinhoError", "Antes de Salvar Adicone algumaFicha");
				preCondition(modelMap);
				return "ficha/editar";
			}
			
			for (FichaExercicio fichaExercicio : carrinho) {
				Ficha ficha = fichaService.buscarPorId(fichaExercicio.getFicha().getId());
				fichaService.AtualizarFicha(ficha);
				fichaExercicioService.AtualizarFichaExercicio(fichaExercicio);
			}
			
			return "redirect:/ficha";
	}
	
	@RequestMapping(value="{linha}/removerEditados", method=RequestMethod.POST) 
	public String removerItemEditavel(@PathVariable int linha, ModelMap modelMap, HttpSession sessao){
		List<FichaExercicio> carrinho = (List<FichaExercicio>)sessao.getAttribute("carrinho");
		carrinho.remove(linha);
		preCondition(modelMap);
		return "ficha/editar";
	}
	
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid AlunoModalidade alunoModalidade,ModelMap modelMap ){
		List<Ficha> fichas = null;
		if(alunoModalidade.getModalidade().getId() == null || (alunoModalidade.getModalidade().getId()).equals("")){
			fichas = fichaService.listarPorAtributoNomeEmFicha(alunoModalidade.getAluno().getNome());
			modelMap.addAttribute("fichas", fichas);
		}else{
			Modalidade modalidade = modalidadeService.buscarPorId(alunoModalidade.getModalidade().getId());
			fichas = fichaService.listarPorAtributoModalidadeEmFicha(modalidade.getNome());
			modelMap.addAttribute("fichas", fichas);
		}
		preCondition(modelMap);
		return "ficha/listar";
	}
	
	public void preCondition(ModelMap modelMap){
		List<Modalidade> modalidades = modalidadeService.listarModalidade();
		List<Aluno> alunos = alunoService.listarAluno();
		List<Exercicio> exercicios = exercicioService.listarExercicio();
		Map<Long, String> exerciciosMap = new TreeMap<Long, String>();
		for (Exercicio exercicio : exercicios) {
			exerciciosMap.put(exercicio.getId(), exercicio.getNome());
		}
		Map<Long, String> alunosMap = new TreeMap<Long, String>();
		for (Aluno aluno : alunos) {
			alunosMap.put(aluno.getId(), aluno.getNome());
		}
		Map<Long, String> modalidadesMap = new TreeMap<Long, String>();
		for (Modalidade modalidade : modalidades) {
			modalidadesMap.put(modalidade.getId(), modalidade.getNome());
		}
		
		modelMap.addAttribute("modalidades", modalidadesMap);
		modelMap.addAttribute("alunos", alunosMap);
		modelMap.addAttribute("exercicios", exerciciosMap);
		modelMap.addAttribute("alunomodalidade", new AlunoModalidade());
		modelMap.addAttribute("fichaExercicio", new FichaExercicio());
	}	
}
