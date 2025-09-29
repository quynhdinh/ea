package cs544.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import cs544.EmailSender;

@Aspect
@Component
public class EmailLoggingAspect {
    @After("execution(* cs544.EmailSender.sendEmail(..)) && args(email, message)")
    public void logEmailSent(JoinPoint joinPoint, String email, String message) {
        EmailSender emailSender = (EmailSender) joinPoint.getTarget();
        String outgoingMailServer = emailSender.getOutgoingMailServer();
        System.out.println(new java.util.Date() + " method= sendEmail address=" + email + " message= " + message + " outgoing mail server = " + outgoingMailServer);
    }
}
