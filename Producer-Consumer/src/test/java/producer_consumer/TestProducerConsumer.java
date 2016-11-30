/**
 * 
 */
package producer_consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Before
	public void initTest() throws Exception{
	
	}
	
	@Test
	public void test() throws InterruptedException {
		
		taskExecutor.execute(producer);	
		Thread.sleep(10000);
		taskExecutor.shutdown();
		
		
	}
	
	@After
	public void endTest() throws Exception{
		
	}

}
