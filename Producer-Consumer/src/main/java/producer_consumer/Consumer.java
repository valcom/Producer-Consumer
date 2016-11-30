/**
 * 
 */
package producer_consumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author vcompagnone
 *
 */
public class Consumer implements MessageListener,Runnable {
	
	private JmsTemplate jmsTemplate;
	
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textmessage = (TextMessage) message;
			System.out.println("Testo del messaggio -> "+ textmessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {

		try{
			Message message = jmsTemplate.receive();
			TextMessage textmessage = (TextMessage) message ;
			System.out.println("Testo del messaggio -> "+ textmessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
