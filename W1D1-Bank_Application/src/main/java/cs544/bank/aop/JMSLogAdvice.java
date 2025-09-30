package cs544.bank.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import cs544.bank.logging.ILogger;
@Aspect
public class JMSLogAdvice {
    @Autowired
    private ILogger logger;

    @AfterReturning("execution(* cs544.bank.jms.JMSSender.sendJMSMessage(..))")
    public void logAfterSending(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder message = new StringBuilder("JMS message sent: " + methodName + " with arguments: ");
        for (Object arg : args) {
            message.append(arg).append(" ");
        }
        logger.log(message.toString());
    }
}
