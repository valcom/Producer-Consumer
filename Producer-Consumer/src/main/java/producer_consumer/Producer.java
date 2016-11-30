/**
 * 
 */
package producer_consumer;

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
public class Producer implements Runnable {

	@Value("${endMessage}")
	private String endMessage;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void run() {
		try{
			jmsTemplate.send(new TextMessageCreator("ciao"));
			jmsTemplate.send(new TextMessageCreator("come"));
			jmsTemplate.send(new TextMessageCreator("stai,"));
			jmsTemplate.send(new TextMessageCreator("tutto"));
			jmsTemplate.send(new TextMessageCreator("bene?"));
			jmsTemplate.send(new TextMessageCreator(endMessage));
		}catch(Exception e){
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
