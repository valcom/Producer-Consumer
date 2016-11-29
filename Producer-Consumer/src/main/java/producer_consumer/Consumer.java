/**
 * 
 */
package producer_consumer;

import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * @author vcompagnone
 *
 */
public class Consumer extends JmsGatewaySupport implements Runnable {
	
	public final static int MAX_IDLE_TIME = 5000;

	
	@Override
	public void run() {
		try {
			Thread.sleep((long) (Math.random()*MAX_IDLE_TIME));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
