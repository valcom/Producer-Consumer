/**
 * 
 */
package producer_consumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author vcompagnone
 *
 */
@Component
@Scope("prototype")
public class Consumer implements Runnable, MessageListener {
	
	private static final double LIMIT = 0.5;

	@Autowired
	private JmsTemplate jmsQueueTemplate;
	
	private final long sleepTime  =(long) (1000*Math.random());
	
	public void onMessage(Message message) {
		try {
			TextMessage textmessage = (TextMessage) message;
			generaEccezioneCasuale();

			System.out.println("Thread "+Thread.currentThread().getName()+"\tTesto del messaggio -> "+ textmessage.getText());
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}
		
	}

	private void generaEccezioneCasuale() {

		if(Math.random()>LIMIT){
			throw new RuntimeException("eccezione test");
		}
		
		
	}

	@Override
	public void run() {

		try{
			Message message = jmsQueueTemplate.receive();
			TextMessage textmessage = (TextMessage) message ;
			System.out.println("Thread "+Thread.currentThread().getName()+"\tTesto del messaggio -> "+ textmessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}



	}


}
