package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import br.unipe.academia.daos.AdministradorDao;
import br.unipe.academia.daos.AlunoDao;
import br.unipe.academia.daos.InstrutorDao;
import br.unipe.academia.daos.UsuarioDao;
import br.unipe.academia.persistence.entity.Administrador;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.Pessoa;
import br.unipe.academia.persistence.entity.Usuario;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AlunoDao alunoDao;
	
	@Autowired
	private InstrutorDao instrutorDao;
	
	@Autowired
	private AdministradorDao administradorDao;
	
	public UsuarioService() {
		super();
	}

	@Transactional
	public void salvarUsuario(Usuario usuario){
		usuarioDao.salavar(usuario);	
	}
	
	@Transactional
	public void salvarUsuarioAluno(Usuario usuario, Aluno aluno){
		
		usuario.setPessoa(aluno);
		usuarioDao.salavar(usuario);
		
		aluno.setUsuario(usuario);
		alunoDao.salavar(aluno);	
	}
	
	@Transactional
	public void salvarUsuarioInstrutor(Usuario usuario, Instrutor instrutor){
		usuario.setPessoa(instrutor);
		usuarioDao.salavar(usuario);
		
		instrutor.setUsuario(usuario);
		instrutorDao.salavar(instrutor);	
	}
	
	@Transactional
	public void salvarUsuarioAdministrador(Usuario usuario, Administrador administrador){
		usuario.setPessoa(administrador);
		usuarioDao.salavar(usuario);
		
		administrador.setUsuario(usuario);
		administradorDao.salavar(administrador);
	}
	
	@Transactional
	public void atualizarUsuario(Usuario usuario){
		usuarioDao.atualizar(usuario);
	}
	
	@Transactional
	public void exluirUsuario(Usuario usuario){
		usuarioDao.excluir(usuario);
	}
	
	@Transactional
	public void exluirUsuarioAluno(Usuario usuario, Aluno aluno){
		alunoDao.excluir(aluno);
		usuarioDao.excluir(usuario);
	}
	
	@Transactional
	public void exluirUsuarioInstrutor(Usuario usuario, Instrutor instrutor){
		instrutorDao.excluir(instrutor);
	
		usuarioDao.excluir(usuario);

	}
	
	@Transactional
	public void exluirUsuarioAdminsitrador(Usuario usuario, Administrador administrador){
		administradorDao.excluir(administrador);

		usuarioDao.excluir(usuario);
	}
	
	
	public List<Usuario> listarUsuario(){
		return usuarioDao.listar();
	}
	
	@Transactional
	public Usuario efetuarLogin(String login, String senha){
		List<Usuario> usuarios = usuarioDao.buscarUsuarioPorLoginSenha(login, senha);

		if(usuarios != null && (!usuarios.isEmpty())){
			return usuarios.get(0);
		}
		return null;
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioDao.buscarPorIdLong(id);
	}
	
	public List<Usuario> buscarPorLoginSenha(String login, String senha) {
		return usuarioDao.buscarUsuarioPorLoginSenha(login, senha);
	}
	
	public List<Usuario> listarUsuarioAparitrDeAluno(Long id) {
		return usuarioDao.listarUsuarioAparitrDeAluno(id);
	}
}
