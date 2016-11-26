/**
 * 
 */
package producer_consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
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
	private TaskExecutor taskExecutor;
	
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
	
	@Test
	public void test(){
		
		taskExecutor.execute(producer);
		taskExecutor.execute(consumer1);
		taskExecutor.execute(consumer2);
		taskExecutor.execute(consumer3);
		taskExecutor.execute(consumer4);
		taskExecutor.execute(consumer5);
		
		
		
	}

}
