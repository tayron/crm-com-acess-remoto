/**
 * 
 */
package br.com.crm.modelo;

import javax.ejb.Local;

import br.com.crm.modelo.dao.UsuarioDAO;

/**
 * Interface que define 
 */
@Local
public interface IDaoRepository {
	
	/**
	 * Método que busca e retorna um usuário
	 */
	public UsuarioDAO getUsuario(); 
	
}
