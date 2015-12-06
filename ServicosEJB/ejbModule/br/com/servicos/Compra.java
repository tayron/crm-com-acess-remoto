package br.com.servicos;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful(name="Compra")
@LocalBean
public class Compra implements CompraRemote {

	private double valorCalculo;
	
	@EJB
	private CotacaoRemote cotacao;
	
	@Override
	public void adicionaDolar(double valor) {
		valorCalculo = valorCalculo + valor;
	}

	@Override
	public void removeDolar(double valor) {
		valorCalculo = valorCalculo - valor;		
	}

	@Override
	public double calculaValorTotal() {
		return cotacao.calcularCotacaoReal(valorCalculo);
	}

	
}
