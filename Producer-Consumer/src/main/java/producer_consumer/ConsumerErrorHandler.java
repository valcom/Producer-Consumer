/**
 * 
 */
package producer_consumer;

import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * @author vcompagnone
 *
 */
@Component
public class ConsumerErrorHandler implements ErrorHandler {

	/* (non-Javadoc)
	 * @see org.springframework.util.ErrorHandler#handleError(java.lang.Throwable)
	 */
	@Override
	public void handleError(Throwable arg0) {
		// TODO Auto-generated method stub

	}

}
