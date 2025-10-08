package cs544.bank.aop;

import cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class JmsLoggingAdvice {
	@Autowired
	private ILogger logger;
	
	public JmsLoggingAdvice(ILogger logger) {
		this.logger = logger;
	}
	
	@After("execution(* cs544.bank.jms.JMSSender.sendJMSMessage(..)) && args (message))")
	public void log(JoinPoint joinpoint, String message) {
		logger.log("JMS Message: "+message);
	}

}
