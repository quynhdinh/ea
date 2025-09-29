package cs544.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailLoggingAspect {
    @After("execution(* cs544.EmailSender.sendEmail(..)) && args(email, message)")
    public void logEmailSent(String email, String message) {
        System.out.println(new java.util.Date() + " method= sendEmail address=" + email + " message=" + message);
    }
}
