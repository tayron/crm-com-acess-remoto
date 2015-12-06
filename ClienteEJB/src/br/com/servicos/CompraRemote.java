package br.com.servicos;

import javax.ejb.Remote;

@Remote
public interface CompraRemote {

	public void adicionaDolar(double valor);
	public void removeDolar(double valor);
	public double calculaValorTotal();
	
}
