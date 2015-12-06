package br.com.servicos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name="Cotacao")
@LocalBean
public class Cotacao implements CotacaoRemote{

	private static final double COTACAO_DOLAR = 3.80;
	
	@Override
	public double calcularCotacaoReal(double valor) {
		return valor * COTACAO_DOLAR;
	}

}
