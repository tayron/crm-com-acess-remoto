package br.com.servicos;

import javax.ejb.Remote;

@Remote
public interface CotacaoRemote {

	public double calcularCotacaoReal(double valor);
}
