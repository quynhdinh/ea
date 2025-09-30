package cs544.bank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import cs544.bank.logging.ILogger;

@Aspect
public class StopWatchAdvice {
    @Autowired
    private ILogger logger;

    @Around("execution(* cs544.bank.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        String methodName = joinPoint.getSignature().getName();
        logger.log("Execution time of " + methodName + ": " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }
}
