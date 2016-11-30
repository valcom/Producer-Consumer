/**
 * 
 */
package producer_consumer;

import java.util.Arrays;
import java.util.List;

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
	
	@Autowired
	private JmsTemplate jmsQueueTemplate;

	@Override
	public void run() {
		try{
			
			List<TextMessageCreator> textMessageCreators = Arrays.asList(new TextMessageCreator("ciao"),
					new TextMessageCreator("come"),new TextMessageCreator("stai,"),new TextMessageCreator("tutto"),new TextMessageCreator("bene?"),new TextMessageCreator(endMessage));
			
			textMessageCreators.forEach(m->jmsTemplate.send(m));
			textMessageCreators.forEach(m->jmsQueueTemplate.send(m));

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
