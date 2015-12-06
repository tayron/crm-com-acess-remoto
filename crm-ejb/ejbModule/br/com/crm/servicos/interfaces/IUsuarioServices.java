/**
 * 
 */
package br.com.crm.servicos.interfaces;

import javax.ejb.Local;

import br.com.crm.entidades.pessoas.Usuario;
import br.com.crm.servicos.execoes.ExcecaoNegocio;

/**
 * 
 */
@Local
public interface IUsuarioServices extends IBaseServices<Usuario> {

	/**
	 * Método que consulta um usuário no banco pelo seu login e senha
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws ExcecaoNegocio
	 */
	public Boolean consultar(String login, String senha) throws ExcecaoNegocio;	
}
