package cs544.bank.aop;

import cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DaoLoggingAdvice {
	@Autowired
	private ILogger logger;
	
	public DaoLoggingAdvice(ILogger logger) {
		this.logger = logger;
	}

	@After("execution(* cs544.bank.dao.*.*(..))")
	public void log(JoinPoint joinpoint) {
		logger.log("Call was made to:" + joinpoint.getSignature().getName()
				+ " on " + joinpoint.getTarget().getClass());
	}
}
