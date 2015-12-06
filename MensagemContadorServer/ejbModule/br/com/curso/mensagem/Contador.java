/**
 * 
 */
package br.com.curso.mensagem;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 
 */
@MessageDriven(activationConfig={
	@ActivationConfigProperty(
		propertyName="destinationType",
		propertyValue="javax.jms.Queue"
	),
	@ActivationConfigProperty(
		propertyName="destination",
		propertyValue="queue/Contador"		
	)
})
public class Contador implements MessageListener{

	/**
	 * Recebe uma solicitação de execução
	 */
	@Override
	public void onMessage(Message message) {
		TextMessage txtMessage = (TextMessage) message;
		
		try {
			String text = txtMessage.getText();
			System.out.println("Mensagem: " + text);
			
			for(int x=1; x<51; x++){
				Thread.currentThread().sleep(500);

				String plural = x > 1 ? "s" : "";
				
				String musica = Integer.toString(x)
						+ " elefante" + plural
						+ " incomoda muita gente. "
						+ Integer.toString(x + 1)
						+ " elefantes"
						+ " incomodam muito mais...";
				
				System.out.println( musica );
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}