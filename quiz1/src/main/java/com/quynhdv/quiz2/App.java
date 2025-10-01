package com.quynhdv.quiz2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private AClass a;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args); 
    }
    @Override
    public void run(String... args) throws Exception {
        a.getAText();
        a.getBText();
    }
}


@Component
class AClass {
    @Value("Test")
    private String text;
    private BClass bClass;
    @Autowired
    public AClass(BClass b) {
        System.out.println("AClass Constructor");
        setText("Hello");
        bClass = b;
    }
    public void setText(String text) {
        System.out.println("Setting text to: " + text);
        this.text = text;
    }
    public String getText() {
        return text;
    }       
    public void getAText() {
        System.out.println("This is a " + getText());
    }
    public void getBText() {
        System.out.println("This is a " + bClass.getText());
    }
}
@Aspect
@Component
class AwesomeAspect {
    @Value("Test")
    private String text;
    public AwesomeAspect() {
        System.out.println("AwesomeAspect Constructor - text: " + text);
    }
    @PostConstruct
    public void start(){
        System.out.println("AwesomeAspect start method - text: " + text);
    }
    @Around("execution(* com.quynhdv.quiz2..get*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getTarget().getClass().getSimpleName();
        if(name.equals("BClass")){
            return "Something";
        }
        System.out.println("AwesomeAspect around method - text: " + text + ", method: " + name);
        return joinPoint.proceed();
    }
}
@Component
class BClass {
    @Value("Test")
    private String text;
    public BClass() {
        System.out.println("BClass Constructor");
    }
    public String getText() {
        return text;
    }
}