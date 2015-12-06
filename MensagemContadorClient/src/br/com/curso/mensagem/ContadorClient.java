/**
 * 
 */
package br.com.curso.mensagem;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author tayron
 *
 */
public class ContadorClient {

	/**
	 * Configura chamada para o EJB Mensagem Contador Server
	 */
	public static void main(String[] args) {
		
		ConnectionFactory fabrica = null;
		Connection conexao = null;
		
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, 
				"org.jnp.interfaces.NamingContextFactory");
		
		jndiProps.put(Context.URL_PKG_PREFIXES, 
				"org.jboss.naming:org.jnp.interfaces");
		
		jndiProps.put(Context.PROVIDER_URL, 
				"jnp://127.0.0.1:1099");
		
		try {
			Context ctx = new InitialContext(jndiProps);
			
			fabrica = (ConnectionFactory) ctx.lookup("ConnectionFactory");
			Queue mensagem = (Queue) ctx.lookup("queue/Contador");
			
			conexao = fabrica.createConnection();
			Session sessao = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer produtor = sessao.createProducer((Destination)mensagem);
			
			conexao.start();
			
			TextMessage txtMessage = sessao.createTextMessage();
			txtMessage.setText("CLIENTE CONTADOR");
			produtor.send(txtMessage);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
				
		
	}
}
