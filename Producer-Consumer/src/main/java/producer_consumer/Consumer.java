/**
 * 
 */
package producer_consumer;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	
	private static final double LIMIT = 0.8;

	@Autowired
	private JmsTemplate jmsQueueTemplate;
		
	private List<String> listaMessaggi;
	
	@Value("${endMessage}")
	private String endMessage;

	private final int name;
	
	private static int counter = 0;
	
	public Consumer() {
		super();
		listaMessaggi = new ArrayList<String>();
		name = counter++;
	}

	
	
	public void onMessage(Message message) {
		try {
			TextMessage textmessage = (TextMessage) message;
			generaEccezioneCasuale();
			listaMessaggi.add(textmessage.getText());
			if(endMessage.equals(textmessage.getText())){
				stampaLista();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	private void generaEccezioneCasuale() {

		if(Math.random()>LIMIT){
			throw new RuntimeException("eccezione test "+name);
		}
		
		
	}

	@Override
	public void run() {
		try{
			Message message = jmsQueueTemplate.receive();
			TextMessage textmessage = (TextMessage) message ;
			listaMessaggi.add(textmessage.getText());
			
			if(endMessage.equals(textmessage.getText())){
				stampaLista();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stampaLista(){
		System.out.println("Lista Messaggi "+name);
		listaMessaggi.forEach(m->System.out.println(m));
	}
	


}
