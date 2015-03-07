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

import br.unipe.academia.persistence.entity.Administrador;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.Usuario;
import br.unipe.academia.persistence.entity.Vaucher;
import br.unipe.academia.services.AdministradorService;
import br.unipe.academia.services.AlunoService;
import br.unipe.academia.services.InstrutorService;
import br.unipe.academia.services.UsuarioService;
import br.unipe.academia.services.VaucherService;
import br.unipe.academia.util.Util;

@Controller 
public class LoginController{ 

@Autowired
private UsuarioService usuarioService;

@Autowired
private AlunoService alunoService;

@Autowired
private AdministradorService administradorService;

@Autowired
private InstrutorService instrutorService;

@Autowired
private VaucherService vaucherService;

  @RequestMapping(value={"/", "loginForm"}) 
  public String loginForm() { 
    return "login/efetuarLogin"; 
  }   

  @RequestMapping("efetuaLogin") 
  public String logar(Usuario usuario, HttpSession session) {
	  Usuario usuarioLogado = usuarioService.efetuarLogin(usuario.getLogin(), usuario.getSenha());
	  session.setAttribute("loginPrincipalError", null);
	  if(usuarioLogado != null){
		 // session.setAttribute("usuarioLogado", usuarioLogado); 
		  Aluno aluno = alunoService.buscarPorId(usuarioLogado.getPessoa().getId());
		  Administrador administrador = administradorService.buscarPorId(usuarioLogado.getPessoa().getId());
		  Instrutor instrutor = instrutorService.buscarPorId(usuarioLogado.getPessoa().getId());
		  if(aluno != null){
			  session.setAttribute("alunoLogado", aluno);
			  return "login/menuDeEntitysAluno"; 
		  }else{
			  if(instrutor!=null){
				  session.setAttribute("instrutorLogado", instrutor);
				  return "login/menuDeEntitysInstrutor"; 
			  }else{
				  session.setAttribute("administradorLogado", administrador);
				  return "login/menuDeEntitysAdministrador"; 
			  }
		  }
	  	  //return "login/menuDeEntitys"; 
	  }
	  if(usuarioLogado == null){
		  session.setAttribute("loginPrincipalError", "Usuário ou Senha Inválidos");
		  return "login/efetuarLogin"; 
	  }
   return "redirect:loginForm"; 
  }
  
  @RequestMapping("cadastraAdmin")
  public String form(ModelMap modelMap,HttpSession session){
	  modelMap.addAttribute("administrador",new Administrador());
	  return "login/cadastro";
  }
  
  @RequestMapping(value="/login/criarAdministrador", method=RequestMethod.POST)
	public String salvar(@ModelAttribute @Valid Administrador administrador, BindingResult result,ModelMap modelMap,HttpSession session){
		session.setAttribute("loginError", null);
		session.setAttribute("passWordError", null);
		session.setAttribute("dateError", null);
		session.setAttribute("vaucherError", null);
		session.setAttribute("novoAdmin", null);
		if(result.hasErrors()){
			if(administrador.getUsuario().getLogin() == null || administrador.getUsuario().getLogin().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("loginError", "O Login não pode ser vazio");
			}
			
			if(administrador.getUsuario().getSenha() == null || administrador.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}
			if(administrador.getDataTemporaria() == null || administrador.getDataTemporaria().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}
			if(administrador.getVaucher() == null || administrador.getVaucher().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("vaucherError", "Vaucher Invalido");
			}
			
			modelMap.addAttribute("administrador", administrador);
			return "login/cadastro";
		}	
		
		if(administrador.getUsuario().getLogin() == null || administrador.getUsuario().getLogin().equals("")){
			modelMap.addAttribute("administrador", administrador);
			session.setAttribute("loginError", "O Login não pode ser vazio");
			if(administrador.getUsuario().getSenha() == null || administrador.getUsuario().getSenha().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("passWordError", "A Senha não pode ser vazia");
			}	if(administrador.getDataTemporaria() == null || administrador.getDataTemporaria().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}if(administrador.getVaucher() == null || administrador.getVaucher().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("vaucherError", "Vaucher Invalido");
			}
			return "login/cadastro";
		}
		if(administrador.getUsuario().getSenha() == null || administrador.getUsuario().getSenha().equals("")){
			modelMap.addAttribute("aluno", administrador);
			session.setAttribute("passWordError", "A Senha não pode ser vazia");
			if(administrador.getDataTemporaria() == null || administrador.getDataTemporaria().equals("")){
				modelMap.addAttribute("administrador",administrador);
				session.setAttribute("dateError", "A Data não pode ser vazia");
			}if(administrador.getVaucher() == null || administrador.getVaucher().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("vaucherError", "Vaucher Invalido");
			}
			return "login/cadastro";
		}
		
		if(administrador.getDataTemporaria() == null || administrador.getDataTemporaria().equals("")){
			modelMap.addAttribute("administrador", administrador);
			session.setAttribute("dateError", "A Data não pode ser vazia");
			if(administrador.getVaucher() == null || administrador.getVaucher().equals("")){
				modelMap.addAttribute("administrador", administrador);
				session.setAttribute("vaucherError", "Vaucher Invalido");
			}
			return "login/cadastro";
		}
		
		if(administrador.getVaucher() == null || administrador.getVaucher().equals("")){
			modelMap.addAttribute("administrador", administrador);
			session.setAttribute("vaucherError", "Vaucher Invalido");
			return "login/cadastro";
		}
		
		List<Vaucher> vauchers = vaucherService.listarVaucher();
		int cont = 0;
		for (Vaucher vaucher : vauchers) {
			System.out.println("####"+vaucher.getSequenciador());
			if(vaucher.getSequenciador().equals(administrador.getVaucher())){
				cont +=1;
			}
		}
		
		if(cont > 0){
			administrador.setDataNasc(Util.editorDeData(administrador.getDataTemporaria()));
			administrador.setDataTemporaria(null);
			Usuario usuario = new Usuario();
			usuario.setLogin(administrador.getUsuario().getLogin());
			usuario.setSenha(administrador.getUsuario().getSenha());
			
			usuarioService.salvarUsuarioAdministrador(usuario, administrador);
			session.setAttribute("novoAdmin", "Novo Administrador Cadastrado Com Sucesso");
			return "redirect:/";
		}else{
			modelMap.addAttribute("administrador", administrador);
			session.setAttribute("vaucherError", "Vaucher Invalido");
			return "login/cadastro";
		}
		
	}
}
