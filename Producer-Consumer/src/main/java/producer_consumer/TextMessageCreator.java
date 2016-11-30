/**
 * 
 */
package producer_consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

/**
 * @author vcompagnone
 *
 */
public class TextMessageCreator implements MessageCreator {
	
	private String text;


	public TextMessageCreator(String text) {
		super();
		this.text = text;
	}
	/* (non-Javadoc)
	 * @see org.springframework.jms.core.MessageCreator#createMessage(javax.jms.Session)
	 */
	@Override
	public Message createMessage(Session session) throws JMSException {

		return session.createTextMessage(text);
	}

}
