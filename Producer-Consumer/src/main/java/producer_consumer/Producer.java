/**
 * 
 */
package producer_consumer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author vcompagnone
 *
 */
@Component
@Scope("prototype")
public class Producer implements Runnable {
	@Override
	public void run() {
	
	}
	
}
