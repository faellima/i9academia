package br.unipe.academia.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
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
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.persistence.entity.Usuario;
import br.unipe.academia.services.AlunoModalidadeService;
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.ModalidadeService;
import br.unipe.academia.services.UsuarioService;
import br.unipe.academia.util.Util;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	@Autowired
	private AlunoModalidadeService alunoModalidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Aluno> alunos = alunoService.listarAluno();
		preCondition(modelMap);
		modelMap.addAttribute("alunos",alunos);
		return "aluno/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap,HttpSession session){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		modelMap.addAttribute("aluno",new Aluno());
		return "aluno/novo";
	}
	
	@RequestMapping(value="criar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Aluno aluno, BindingResult result,ModelMap modelMap,HttpSession session){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		if(result.hasErrors()){
			
			if(aluno.getUsuario().getLogin() == null || aluno.getUsuario().getLogin().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("loginError", "O Login não pode ser vazio");
			}
			
			if(aluno.getUsuario().getSenha() == null || aluno.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}
			if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			
			modelMap.addAttribute("aluno", aluno);
			return "aluno/novo";
		}	
		
		if(aluno.getUsuario().getLogin() == null || aluno.getUsuario().getLogin().equals("")){
			modelMap.addAttribute("aluno", aluno);
			session.setAttribute("loginError", "O Login não pode ser vazio");
			if(aluno.getUsuario().getSenha() == null || aluno.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}	if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "aluno/novo";
		}
		if(aluno.getUsuario().getSenha() == null || aluno.getUsuario().getSenha().equals("")){
			modelMap.addAttribute("aluno", aluno);
			session.setAttribute("passWordError", "A Senha não pode ser vazia");
			if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "aluno/novo";
		}
		if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
			modelMap.addAttribute("aluno", aluno);
			session.setAttribute("dateError", "A Data não pode ser vazia");
			return "aluno/novo";
		}
		
		aluno.setDataNasc(Util.editorDeData(aluno.getDataTemporaria()));
		aluno.setDataTemporaria(null);
		Usuario usuario = new Usuario();
		usuario.setLogin(aluno.getUsuario().getLogin());
		usuario.setSenha(Util.criptografar(aluno.getUsuario().getSenha()));
	
		usuarioService.salvarUsuarioAluno(usuario, aluno);
	
		return "redirect:/aluno";
	}

	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		Aluno aluno =(Aluno) alunoService.buscarPorId(id);
		if((aluno.getUsuario().getId()) == null || (aluno.getUsuario().getId()).equals("")){
			alunoService.exluirAluno(aluno);
		}else{
			Usuario usuario = usuarioService.buscarPorId(aluno.getUsuario().getId());
			usuarioService.exluirUsuarioAluno(usuario, aluno);
		}
		return "redirect:/aluno";
	}
	
	
	@RequestMapping(value = "/matricular/{id}", method = RequestMethod.POST)
	public String preMatricula(@PathVariable Long id, ModelMap modelMap, HttpSession session){
		session.setAttribute("matriculaError", null);
		List<AlunoModalidade> modalidadesDoAluno = null;
		modalidadesDoAluno = alunoModalidadeService.listarPorAtributoModalidadeEmAluno(id);
		Aluno aluno = alunoService.buscarPorId(id);
		modelMap.addAttribute("modalidadesDoAluno", modalidadesDoAluno);
		modelMap.addAttribute("aluno", aluno);
		preCondition(modelMap);
		return "aluno/matricular";
	}
		
	@RequestMapping(value = "/efetuarmatricula", method = RequestMethod.GET)
	public String salvarMatricular(@ModelAttribute @Valid AlunoModalidade alunoModalidade, ModelMap modelMap, HttpSession session){
		List<AlunoModalidade> modalidadesDoAluno = null;
		session.setAttribute("matriculaError", null);
		if(alunoModalidade.getModalidade().getId() == null || alunoModalidade.getModalidade().getId().equals("")){
			session.setAttribute("matriculaError", "Escolha uma modalidade");
			modalidadesDoAluno = alunoModalidadeService.listarPorAtributoModalidadeEmAluno(alunoModalidade.getAluno().getId());
			modelMap.addAttribute("modalidadesDoAluno", modalidadesDoAluno);
			Aluno alunoValidador = alunoService.buscarPorId(alunoModalidade.getAluno().getId());
			modelMap.addAttribute("aluno", alunoValidador);
			preCondition(modelMap);
			return "aluno/matricular";	
		}
		alunoModalidadeService.salvarAlunoModalidade(alunoModalidade);
		modalidadesDoAluno = alunoModalidadeService.listarPorAtributoModalidadeEmAluno(alunoModalidade.getAluno().getId());
		Aluno aluno = alunoService.buscarPorId(alunoModalidade.getAluno().getId());
		modelMap.addAttribute("modalidadesDoAluno", modalidadesDoAluno);
		modelMap.addAttribute("aluno", aluno);
		preCondition(modelMap);
		return "aluno/matricular";
	}
	

	@RequestMapping(value = "editarmatricula/{id}", method = RequestMethod.POST)
	public String editarMatricula(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
		session.setAttribute("matriculaError", null);
		AlunoModalidade alunoModalidade = alunoModalidadeService.buscarPorId(id);
		Aluno aluno = alunoService.buscarPorId(alunoModalidade.getAluno().getId());
		aluno.setDataTemporaria(aluno.getDataNasc().toString());
		modelMap.addAttribute("aluno", aluno);
		preCondition(modelMap);
		modelMap.addAttribute("alunomodalidade", alunoModalidade);
		return "aluno/editarMatricula";
	}
	
	@RequestMapping(value = "salvarmatriculaeditada", method = RequestMethod.POST)
	public String atualizarMatricula(@ModelAttribute @Valid AlunoModalidade alunoModalidade,ModelMap modelMap, HttpSession session) {
		List<AlunoModalidade> modalidadesDoAluno = null;
		session.setAttribute("matriculaError", null);
		if(alunoModalidade.getModalidade().getId() == null || alunoModalidade.getModalidade().getId().equals("")){
			session.setAttribute("matriculaError", "Escolha uma modalidade");
			AlunoModalidade alunoModalidadeValidador = alunoModalidadeService.buscarPorId(alunoModalidade.getId());
			Aluno alunoValidador = alunoService.buscarPorId(alunoModalidade.getAluno().getId());
			modelMap.addAttribute("aluno", alunoValidador);
			modelMap.addAttribute("alunomodalidade", alunoModalidade);
			preCondition(modelMap);
			return "aluno/editarMatricula";	
		}
		alunoModalidadeService.atualizarAlunoModalidade(alunoModalidade);
		modalidadesDoAluno = alunoModalidadeService.listarPorAtributoModalidadeEmAluno(alunoModalidade.getAluno().getId());
		Aluno aluno = alunoService.buscarPorId(alunoModalidade.getAluno().getId());
		modelMap.addAttribute("modalidadesDoAluno", modalidadesDoAluno);
		modelMap.addAttribute("aluno", aluno);
		preCondition(modelMap);
		return "aluno/matricular";
	}
	
	@RequestMapping(value = "/removermatricula/{id}", method = RequestMethod.DELETE)
	public String excluirMatricula(@PathVariable Long id, ModelMap modelMap) {
		List<AlunoModalidade> modalidadesDoAluno = null;
		AlunoModalidade alunoModalidade = alunoModalidadeService.buscarPorId(id);
		Long alunoId = (Long) alunoModalidade.getAluno().getId();
		alunoModalidadeService.exluirAlunoModalidade(alunoModalidade);
		modalidadesDoAluno = alunoModalidadeService.listarPorAtributoModalidadeEmAluno(alunoId);
		Aluno aluno = alunoService.buscarPorId(alunoId);
		modelMap.addAttribute("modalidadesDoAluno", modalidadesDoAluno);
		modelMap.addAttribute("aluno", aluno);
		preCondition(modelMap);
		return "aluno/matricular";
	}
	
//	public void atualizarUsuario(Long id, ModelMap modelMap, Usuario user){
//		Aluno aluno = alunoService.buscar(new Aluno(id));
//		Usuario usuario = usuarioService.buscarPorId(aluno.getUsuario().getId());
//		usuario.setLogin(user.getLogin());
//		usuario.setSenha(user.getSenha());
//		usuarioService.atualizarUsuario(usuario);
//	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		Aluno aluno = alunoService.buscar(new Aluno(id));
		Usuario usuario = usuarioService.buscarPorId(aluno.getUsuario().getId());
	    aluno.setDataTemporaria(Util.converterDataEmString(aluno.getDataNasc()));
		modelMap.addAttribute("aluno", aluno);
		modelMap.addAttribute("usuario", usuario);
		return "aluno/editar";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String atualizar(@ModelAttribute @Valid Aluno aluno,BindingResult result, ModelMap modelMap, HttpSession session){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		
		if(result.hasErrors()){
			
			if(aluno.getUsuario().getLogin() == null || aluno.getUsuario().getLogin().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("loginError", "O Login não pode ser vazio");
			}
			
			if(aluno.getUsuario().getSenha() == null || aluno.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}
			
			if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			modelMap.addAttribute("aluno", aluno);
			return "aluno/editar";
		}	
		
		if(aluno.getUsuario().getLogin() == null || aluno.getUsuario().getLogin().equals("")){
			modelMap.addAttribute("aluno", aluno);
			session.setAttribute("loginError", "O Login não pode ser vazio");
			if(aluno.getUsuario().getSenha() == null || aluno.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "aluno/editar";
		}
		if(aluno.getUsuario().getSenha() == null || aluno.getUsuario().getSenha().equals("")){
			modelMap.addAttribute("aluno", aluno);
			session.setAttribute("passWordError", "A Senha não pode ser vazia");
			if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
				modelMap.addAttribute("aluno", aluno);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "aluno/editar";
		}
		if(aluno.getDataTemporaria() == null || aluno.getDataTemporaria().equals("")){
			modelMap.addAttribute("aluno", aluno);
			session.setAttribute("dateError", "A Data não pode ser vazia");
			return "aluno/editar";
		}
		List<Usuario> users = null;
		Usuario usuario = null;
		aluno.setDataNasc(Util.editorDeData(aluno.getDataTemporaria()));
		users = usuarioService.listarUsuarioAparitrDeAluno(aluno.getId());
		for (Usuario usuarioCoontrole : users) {
			usuario = usuarioCoontrole;
		}
	
		usuario.setLogin(aluno.getUsuario().getLogin());
		usuario.setSenha(Util.criptografar(aluno.getUsuario().getSenha()));
		usuarioService.atualizarUsuario(usuario);
		aluno.setUsuario(usuario);
		alunoService.atualizarAluno(aluno);
		
		return "redirect:/aluno";
	}
	
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid AlunoModalidade alunoModalidade,ModelMap modelMap ){
		List<Aluno> alunos = null;
		if(alunoModalidade.getModalidade().getId() == null || (alunoModalidade.getModalidade().getId()).equals("")){
			alunos = alunoService.listarPorAtributoNome(alunoModalidade.getAluno().getNome());
			modelMap.addAttribute("alunos", alunos);
		}else{
			Modalidade modalidade = modalidadeService.buscarPorId(alunoModalidade.getModalidade().getId());
			alunos = alunoService.listarPorAtributoModalidade(modalidade.getNome());
			modelMap.addAttribute("alunos", alunos);
		}
		preCondition(modelMap);
		return "aluno/listar";
	}
	
	public void preCondition(ModelMap modelMap){
		List<Modalidade> modalidades = modalidadeService.listarModalidade();
		Map<Long, String> modalidadesMap = new TreeMap<Long, String>();
		for (Modalidade modalidade : modalidades) {
			modalidadesMap.put(modalidade.getId(), modalidade.getNome());
		}
		
		modelMap.addAttribute("modalidades", modalidadesMap);
		modelMap.addAttribute("alunomodalidade", new AlunoModalidade());
	}	
}
