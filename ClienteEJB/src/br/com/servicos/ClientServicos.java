package br.com.servicos;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientServicos {
	public static void main(String[] args) {

		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, 
				"org.jnp.interfaces.NamingContextFactory");
		
		jndiProps.put(Context.URL_PKG_PREFIXES, 
				"org.jboss.naming:org.jnp.interfaces");
		
		jndiProps.put(Context.PROVIDER_URL, 
				"jnp://127.0.0.1:1099");

		try {
			Context ctx = new InitialContext(jndiProps);
			
			CotacaoRemote cotacao = (CotacaoRemote)ctx.lookup("Cotacao/remote");
			
			System.out.println("Cotação em reais: " + cotacao.calcularCotacaoReal(1));
			
			CompraRemote compra = (CompraRemote)ctx.lookup("Compra/remote");
			
			compra.adicionaDolar(1000);
			System.out.println("adicionando 1000");
			
			System.out.println("Valor atualizado: " + compra.calculaValorTotal());
			
			compra.adicionaDolar(250);
			System.out.println("Adicionando 250");
			
			System.out.println("Valor atualizado: " + compra.calculaValorTotal());
			
			compra.removeDolar(100);
			System.out.println("Removendo 100");
			
			System.out.println("Valor atualizado: " + compra.calculaValorTotal());
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
