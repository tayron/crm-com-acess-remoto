/**
 * 
 */
package br.com.crm.modelo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.crm.modelo.IDaoRepository;

/**
 * Classe repositório que fornece acesso a um dao
 */
@Stateless
public class DaoRepository implements IDaoRepository {

	/**
	 * Injeção de um entityManager
	 */
	@PersistenceContext(name="crm-jpa")
	private EntityManager entityManager;
	
	/**
	 * @see IDaoRepository#getUsuario()
	 */
	@Override
	public UsuarioDAO getUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.setEntityManager(entityManager);
		return usuarioDAO;
	}

}
