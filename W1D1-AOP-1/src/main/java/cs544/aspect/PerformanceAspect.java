package cs544.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* cs544.CustomerDAO.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed(); // Proceed with the original method execution
        sw.stop();
        long totaltime = sw.lastTaskInfo().getTimeMillis();
        System.out.println("Time to execute " + call.getSignature().getName() + " = " + totaltime + " ms");
        return retVal;
    }
}
