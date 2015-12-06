package br.com.crm.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.crm.entidades.pessoas.Usuario;
import br.com.crm.modelo.IBaseDAO;
import br.com.crm.modelo.IUsuarioDAO;
import br.com.crm.modelo.excecoes.ExcecaoModelo;

/**
 * Classe DAO para que manipula dos dao do usuário no banco
 */
public class UsuarioDAO implements IBaseDAO<Usuario>, IUsuarioDAO{

	/**
	 * 
	 */
	private EntityManager entityManager;
	
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @see IBaseDAO#excluir(Usuario)
	 */
	public void incluir(Usuario usuario) throws ExcecaoModelo {	
		entityManager.persist(usuario);
	}

	/**
	 * {@see IBaseDAO#alterar(Usuario)
	 */
	public void alterar(Usuario usuario) throws ExcecaoModelo {
		entityManager.merge(usuario);
	}

	/**
	 * @see IBaseDAO#excluir(Usuario)
	 */
	public void excluir(Usuario usuario) throws ExcecaoModelo {
		Usuario usuarioEncontrado = entityManager.find(Usuario.class, usuario.getId());
		entityManager.remove(usuarioEncontrado);
	}

	/**
	 * @see IBaseDAO#listar()
	 */
	public List<Usuario> listar() throws ExcecaoModelo {		
		List<Usuario> usuarios = null;

		TypedQuery<Usuario> query = entityManager
			.createQuery("Select u from Usuario u", 
				Usuario.class);
		
		usuarios = query.getResultList();
		
		return usuarios;
	}

	/**
	 * @see IBaseDAO#recuperar(Usuario)
	 */
	public Usuario recuperar(Usuario usuario) throws ExcecaoModelo {
		return entityManager.find(Usuario.class, usuario.getId());
	}
	
	/**
	 * @see IUsuarioDAO#consultar(String, String)
	 */
	public Boolean consultar(String login, String senha) throws ExcecaoModelo {		
		Usuario usuario = null;
	
		StringBuffer sql = new StringBuffer();
		sql.append("Select u from Usuario u where ");
		sql.append("u.login = :login and ");
		sql.append("u.senha = :senha");
		
		TypedQuery<Usuario> query = entityManager.createQuery(sql.toString(), Usuario.class);

		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		usuario = query.getSingleResult();
		
		return usuario != null;
	}
}
