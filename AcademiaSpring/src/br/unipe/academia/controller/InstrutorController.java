package br.unipe.academia.controller;

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

import com.sun.org.apache.bcel.internal.generic.IINC;

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.AlunoModalidade;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.persistence.entity.Usuario;
import br.unipe.academia.services.AlunoModalidadeService;
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.InstrutorService;
import br.unipe.academia.services.ModalidadeService;
import br.unipe.academia.services.UsuarioService;
import br.unipe.academia.util.Util;

@Controller
@RequestMapping("/instrutor")
public class InstrutorController {
	
	@Autowired
	private InstrutorService instrutorService;
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoModalidadeService alunoModalidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar(ModelMap modelMap){
		List<Instrutor> instrutores = instrutorService.listar();
		modelMap.addAttribute("instrutores",instrutores);
		preCondition(modelMap);
		return "instrutor/listar";
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST)
	public String form(ModelMap modelMap, HttpSession session){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		modelMap.addAttribute("instrutor",new Instrutor());
		return "instrutor/novo";
	}
	
	@RequestMapping(value="criar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Instrutor instrutor, BindingResult result,
												ModelMap modelMap, HttpSession session ){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		if(result.hasErrors()){
			if(instrutor.getUsuario().getLogin() == null || instrutor.getUsuario().getLogin().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("loginError", "O Login não pode ser vazio");
			}
			
			if(instrutor.getUsuario().getSenha() == null || instrutor.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}
			if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			modelMap.addAttribute("intrutor", instrutor);
			//modelMap.addAttribute("usuario", usuario);
			return "instrutor/novo";
		}
		
		if(instrutor.getUsuario().getLogin() == null || instrutor.getUsuario().getLogin().equals("")){
			modelMap.addAttribute("instrutor", instrutor);
			session.setAttribute("loginError", "O Login não pode ser vazio");
			if(instrutor.getUsuario().getSenha() == null || instrutor.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "instrutor/novo";
		}
		
		if(instrutor.getUsuario().getSenha() == null || instrutor.getUsuario().getSenha().equals("")){
			modelMap.addAttribute("instrutor", instrutor);
			session.setAttribute("passWordError", "A Senha não pode ser vazia");
			if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "instrutor/novo";
		}
		if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
			modelMap.addAttribute("instrutor", instrutor);
			session.setAttribute("dateError", "A Data não pode ser vazia");
			return "instrutor/novo";
		}
		
		instrutor.setDataNasc(Util.editorDeData(instrutor.getDataTemporaria()));
		Usuario usuario = new Usuario();
		usuario.setLogin(instrutor.getUsuario().getLogin());
		usuario.setSenha(instrutor.getUsuario().getSenha());
		
		usuarioService.salvarUsuarioInstrutor(usuario, instrutor);
		return "redirect:/instrutor";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		Instrutor instrutor =(Instrutor) instrutorService.buscarPorId(id);
		if((instrutor.getUsuario().getId()) == null || (instrutor.getUsuario().getId()).equals("")){
			instrutorService.ExcluirInstrutor(instrutor);
		}else{
			Usuario usuario = usuarioService.buscarPorId(instrutor.getUsuario().getId());
			usuarioService.exluirUsuarioInstrutor(usuario, instrutor);
		}
		return "redirect:/instrutor";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String editForm(@PathVariable Long id, ModelMap modelMap, HttpSession session) {
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		Instrutor instrutor = instrutorService.buscar(new Instrutor(id));
		Usuario usuario = usuarioService.buscarPorId(instrutor.getUsuario().getId());
		instrutor.setDataTemporaria(Util.converterDataEmString(instrutor.getDataNasc()));
		modelMap.addAttribute("instrutor", instrutor);
		modelMap.addAttribute("usuario", usuario);
		return "instrutor/editar";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String atualizar(@ModelAttribute @Valid Instrutor instrutor, BindingResult result,ModelMap modelMap, HttpSession session){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		
		if(result.hasErrors()){
			if(instrutor.getUsuario().getLogin() == null || instrutor.getUsuario().getLogin().equals("")){
				modelMap.addAttribute("instrutor",instrutor);
				session.setAttribute("loginError", "O Login não pode ser vazio");
			}
			
			if(instrutor.getUsuario().getSenha() == null || instrutor.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}
			
			if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			modelMap.addAttribute("intrutor", instrutor);
			return "instrutor/editar";
		}
		
		if(instrutor.getUsuario().getLogin() == null || instrutor.getUsuario().getLogin().equals("")){
			modelMap.addAttribute("instrutor", instrutor);
			session.setAttribute("loginError", "O Login não pode ser vazio");
			if(instrutor.getUsuario().getSenha() == null || instrutor.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "instrutor/editar";
		}
		if(instrutor.getUsuario().getSenha() == null || instrutor.getUsuario().getSenha().equals("")){
			modelMap.addAttribute("instrutor", instrutor);
			session.setAttribute("passWordError", "A Senha não pode ser vazia");
			if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
				modelMap.addAttribute("instrutor", instrutor);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			return "instrutor/editar";
		}
		if(instrutor.getDataTemporaria() == null || instrutor.getDataTemporaria().equals("")){
			modelMap.addAttribute("instrutor", instrutor);
			session.setAttribute("dateError", "A Data não pode ser vazia");
			return "instrutor/editar";
		}
		List<Usuario> users = null;
		Usuario usuario = null;
		instrutor.setDataNasc(Util.editorDeData(instrutor.getDataTemporaria()));
		users = usuarioService.listarUsuarioAparitrDeAluno(instrutor.getId());
		for (Usuario usuarioCoontrole : users) {
			usuario = usuarioCoontrole;
		}
	
		usuario.setLogin(instrutor.getUsuario().getLogin());
		usuario.setSenha(instrutor.getUsuario().getSenha());
		usuarioService.atualizarUsuario(usuario);
		instrutor.setUsuario(usuario);
	
		instrutorService.AtualizarInstrutor(instrutor);
		return "redirect:/instrutor";
	}
		
	@RequestMapping(value="filtro", method=RequestMethod.GET)
	public String filtrar(@ModelAttribute @Valid Ficha ficha,ModelMap modelMap ){
		List<Instrutor> instrutores = null;
		List<AlunoModalidade> alunoModalidades = null;
		List<Ficha> fichas = null;
		if(ficha.getAlunoModalidade().getModalidade().getId() == null || (ficha.getAlunoModalidade().getModalidade().getId()).equals("")){
			instrutores = instrutorService.listarPorAtributoNome(ficha.getInstrutor().getNome());
			modelMap.addAttribute("instrutores",instrutores);
		}else{
			Modalidade modalidade = modalidadeService.buscarPorId(ficha.getAlunoModalidade().getModalidade().getId());
			instrutores = instrutorService.listarPorAtributoModalidadeEmInstrutor(modalidade.getNome());		
			modelMap.addAttribute("instrutores",instrutores);
		}
		preCondition(modelMap);
		return "instrutor/listar";
	}
	
	public void preCondition(ModelMap modelMap){
		List<Modalidade> modalidades = modalidadeService.listarModalidade();
		Map<Long, String> modalidadesMap = new TreeMap<Long, String>();
		for (Modalidade modalidade : modalidades) {
			modalidadesMap.put(modalidade.getId(), modalidade.getNome());
		}
		
		modelMap.addAttribute("modalidades", modalidadesMap);
		modelMap.addAttribute("ficha", new Ficha());
	}
}
