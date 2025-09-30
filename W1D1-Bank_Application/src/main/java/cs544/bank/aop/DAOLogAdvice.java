package cs544.bank.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import cs544.bank.logging.ILogger;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class DAOLogAdvice {
    @Autowired
    private ILogger logger;

    @Before("execution(* cs544.bank.dao.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder message = new StringBuilder("Calling method: " + methodName + " with arguments: ");
        for (Object arg : args) {
            message.append(arg).append(" ");
        }
        logger.log(message.toString());
    }
}
