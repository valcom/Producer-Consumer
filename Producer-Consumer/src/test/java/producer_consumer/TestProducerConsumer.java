/**
 * 
 */
package producer_consumer;

import java.net.URI;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vcompagnone
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/applicationContext.xml")
public class TestProducerConsumer {
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private Runnable producer;
	
	@Autowired
	@Qualifier("consumer")
	private Runnable consumer1;
	
	@Autowired
	@Qualifier("consumer")
	private Runnable consumer2;
	
	@Autowired
	@Qualifier("consumer")
	private Runnable consumer3;
	
	@Autowired
	@Qualifier("consumer")
	private Runnable consumer4;
	
	@Autowired
	@Qualifier("consumer")
	private Runnable consumer5;
	
	private BrokerService broker; 
	
	@Value("${brokerURL}")
	private String brokerUrl;

	@Before
	public void initTest() throws Exception{
		broker = BrokerFactory.createBroker(new URI(brokerUrl));
		broker.setUseJmx(false);
		broker.setPersistent(false);
		broker.setUseShutdownHook(false);
		broker.start();
	}
	
	@Test
	public void test() {
		
		
		taskExecutor.execute(producer);

		taskExecutor.execute(consumer1);
		taskExecutor.execute(consumer2);
		taskExecutor.execute(consumer3);
		taskExecutor.execute(consumer4);
		taskExecutor.execute(consumer5);
		
	}
	
	@After
	public void endTest() throws Exception{
		if(taskExecutor!=null){
			taskExecutor.shutdown();
		}
		if(broker!=null){
			broker.stop();
		}
	}

}
