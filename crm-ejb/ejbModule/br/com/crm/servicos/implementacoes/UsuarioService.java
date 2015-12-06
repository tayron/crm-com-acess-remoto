/**
 * 
 */
package br.com.crm.servicos.implementacoes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.crm.entidades.pessoas.Usuario;
import br.com.crm.modelo.IDaoRepository;
import br.com.crm.modelo.dao.UsuarioDAO;
import br.com.crm.modelo.excecoes.ExcecaoModelo;
import br.com.crm.servicos.execoes.ExcecaoNegocio;
import br.com.crm.servicos.interfaces.IBaseServices;
import br.com.crm.servicos.interfaces.IUsuarioServices;

/**
 * Serviço que provê recursos para panipulação do usuário
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioService extends BaseServices<Usuario> implements IUsuarioServices {
	
	/**
	 * Armazena o UsuarioDAO para manipulacao dos dados
	 */
	private UsuarioDAO usuarioDAO;
	
	/**
	 * Busca o dao usuário no repositório e armazena em usuarioDAO
	 */
	@PostConstruct
	public void initialize(){
		IDaoRepository daoRepository;
		try {
			daoRepository = (IDaoRepository) new InitialContext().
					lookup("crm-ear/DaoRepository/local");
			usuarioDAO = daoRepository.getUsuario();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * @see IBaseServices#excluir(Usuario)
	 */
	public void incluir(Usuario usuario) throws ExcecaoNegocio {
		try {
			validarCamposObrigatorios(usuario);
			validarLogin(usuario);
			if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
				throw new Exception("Confirmação de senha inválida");
			}

			usuarioDAO.incluir(usuario);
		} catch (Exception e) {
			throw new ExcecaoNegocio(e.getMessage());
		}
	}

	/**
	 * @see IBaseServices#alterar(Usuario)
	 */	
	@Override
	public void alterar(Usuario usuario) throws ExcecaoNegocio {
		try {
			validarID(usuario);
			validarCamposObrigatorios(usuario);
			validarLogin(usuario);
			
			if (usuario.getConfirmaSenha() == null) {
				throw new Exception("O campo confirmação de senha deve ser preenchido");
			}
			
			if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
				throw new Exception("Confirmação de senha inválida");
			}

			usuarioDAO.alterar(usuario);
		} catch (Exception e) {
			throw new ExcecaoNegocio(e.getMessage());
		}
	}
	
	/**
	 * @see IBaseServices#excluir(Usuario)
	 */
	@Override
	public void excluir(Usuario usuario) throws ExcecaoNegocio {
		try {
			validarID(usuario);			
			usuarioDAO.excluir(usuario);
		} catch (Exception e) {
			throw new ExcecaoNegocio(e.getMessage());
		}	
	}
	
	/**
	 * @see IBaseServices#listar()
	 */
	@Override
	public List<Usuario> listar() throws ExcecaoNegocio {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			listaUsuarios = usuarioDAO.listar();
			
			if(listaUsuarios.size() == 0){
				throw new Exception("Nenhum registro encontrado");
			}
		} catch (Exception e) {
			throw new ExcecaoNegocio(e.getMessage());
		}

		return listaUsuarios;
	}
	
	/**
	 * @see IBaseServices#recuperar(Usuario)
	 */
	@Override
	public Usuario recuperar(Usuario usuario) throws ExcecaoNegocio {
		Usuario usuarioEncontrado = null;
		try{
			usuarioEncontrado = usuarioDAO.recuperar(usuario);
		}catch(ExcecaoModelo e ){
			throw new ExcecaoNegocio(e.getMessage());
		}
		
		return usuarioEncontrado;
	}
	
	/**
	 * {@link IUsuarioBO#consultar(String, String)}
	 */
	@Override
	public Boolean consultar(String login, String senha) throws ExcecaoNegocio {
		try {
			if (! usuarioDAO.consultar(login, senha)){
				throw new Exception("Login e/ou senha invalido(s).");
			}
		} catch (Exception e) {
			throw new ExcecaoNegocio(e.getMessage());
		}

		return true;
	}		
	
	/**
	 * Método que verifica se o campo login e senha foram preenchidos.
	 * 
	 * @param usuario
	 * @throws ExcecaoNegocio
	 */
	private void validarLogin(Usuario usuario) throws ExcecaoNegocio{
		if(usuario.getLogin().isEmpty()||
			usuario.getSenha().isEmpty()){ 
			throw new ExcecaoNegocio("Login e senha obrigatórios");
		}
	}

	/**
	 * Método verifica se o id do usuário foi informado
	 * 
	 * @param usuario
	 * @throws Exception
	 */
	private void validarID(Usuario usuario) throws Exception{
		UsuarioDAO usuarioDao = new UsuarioDAO();
		if(usuario.getId() == null){
			throw new Exception("ID é obrigatório para operações de banco de dados");
		}

		try{
			usuarioDao.recuperar(usuario);
		}catch(ExcecaoModelo e ){
			throw new Exception("ID não encontrado para a operações de banco de dados solicitada");
		}
	}
}
