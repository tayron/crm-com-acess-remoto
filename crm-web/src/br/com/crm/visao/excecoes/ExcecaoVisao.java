package br.com.crm.visao.excecoes;


/**
 * Classe de exceção para a camada de negócio.
 */
public class ExcecaoVisao extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensagem
	 */
	public ExcecaoVisao(String mensagem) {
		super(mensagem);
	}
}
