/**
 * 
 */
package br.com.crm.servicos.interfaces;

import java.util.List;

import br.com.crm.entidades.pessoas.Pessoa;
import br.com.crm.servicos.execoes.ExcecaoNegocio;


/**
 * Interface para os servicos do ejb
 */
public interface IBaseServices<T extends Pessoa> {
	
	/**
	 * Método que insere um registor no banco de dados
	 * 
	 * @param t
	 * @throws ExcecaoNegocio
	 */
	public void incluir(T t) throws ExcecaoNegocio;
	
	/**
	 * Método que altera um registro no banco de dados
	 * 
	 * @param t
	 * @throws ExcecaoNegocio
	 */
	public void alterar(T t) throws ExcecaoNegocio;
	
	/**
	 * Método que remove um registro do banco de dados
	 * 
	 * @param t
	 * @throws ExcecaoNegocio
	 */
	public void excluir(T t) throws ExcecaoNegocio;
	
	/**
	 * Método que lista todos os registros do banco de dados
	 * 
	 * @param t
	 * @return
	 * @throws ExcecaoNegocio
	 */
	public List<T> listar() throws ExcecaoNegocio;
	
	/**
	 * Métodoque busca recupera uma entidade no banco de dados através do seu id
	 * 
	 * @param t
	 * @return
	 * @throws ExcecaoNegocio
	 */
	public T recuperar(T t) throws ExcecaoNegocio; 
	
	/**
	 * Método que consulta um usuário pelo seu email e senha
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws ExcecaoNegocio
	 */
	public Boolean consultar(String login, String senha) throws ExcecaoNegocio;
}