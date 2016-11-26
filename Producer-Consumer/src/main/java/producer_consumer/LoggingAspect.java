/**
 * 
 */
package producer_consumer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author vcompagnone
 *
 */
@Component
@Aspect
public class LoggingAspect {
	
	private final static String POINTCUT = "execution(public * producer_consumer.*.*(..))";
	public final static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	
	private static StringBuilder buildInput(Object[] args) {
		StringBuilder sb = null;
		if(args != null && args.length>0){
			sb = new StringBuilder();
			for(Object arg :args){
				sb.append(arg!=null?arg.toString():null);
				sb.append("\n");
			}
		}
		return sb;
	}
	
	@Before(value = POINTCUT)
	public void beforeLog(JoinPoint jp){
		final Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
		if(logger.isDebugEnabled()){
			logger.debug("Inizio metodo {}",jp.getSignature());
			if(logger.isTraceEnabled()){
				StringBuilder input = buildInput(jp.getArgs());
				if(input != null){
					logger.trace("input = {}",input);
				}
			}
		}
		
	}
	
	
	@AfterReturning(pointcut= POINTCUT,returning="result")
	public void afterReturningLog(JoinPoint jp,Object result){
		final Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
		if(logger.isDebugEnabled()){
			logger.debug("Fine Metodo {}",jp.getSignature());
			if(logger.isTraceEnabled()&&result!=null){
				logger.trace("output = {}",result.toString());
			}
		}
	}

	@AfterThrowing(pointcut=POINTCUT,throwing="e")
	public void afterThrowingLog(JoinPoint jp,Throwable e) throws Throwable{
		final Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());

		logger.error(e.getMessage(),e);
		StringBuilder input = buildInput(jp.getArgs());
		if(input != null){
			logger.error("input = {}",input);
		}
		throw e;
	}
	
	
}
