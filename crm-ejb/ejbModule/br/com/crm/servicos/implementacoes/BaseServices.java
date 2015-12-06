package br.com.crm.servicos.implementacoes;

import br.com.crm.entidades.pessoas.Pessoa;
import br.com.crm.servicos.execoes.ExcecaoNegocio;

/**
 * Classe abstrata que define os métodos a serem implementados 
 * pelas classes de negócio que extende de Pessoa.
 */
public class BaseServices<T extends Pessoa> {
	
	/**
	 * Método que valida se o nome, endereço e cfp estão presentes
	 * 
	 * @param t
	 * @throws ExcecaoNegocio
	 */
	public void validarCamposObrigatorios(T t) throws ExcecaoNegocio{
		if(t.getNome().isEmpty() || 
			t.getEndereco().isEmpty() || 
			t.getCpf().isEmpty()){
			throw new ExcecaoNegocio("Campos obrigatórios não foram preenchidos");
		}
	}	
}
