// package com.quynhdv.quiz1;

// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Component;

// import jakarta.annotation.PostConstruct;

// // @SpringBootApplication
// public class App implements CommandLineRunner {
//     @Autowired
//     private MyClass myClass;

//     public static void main(String[] args) {
//         SpringApplication.run(App.class, args);
//     }
//     @Override
//     public void run(String... args) throws Exception {
//         myClass.sayHello();
//         myClass.setText("Testing");
//     }
    
// }
// @Aspect
// @Configuration
// class TraceAspect {
//     @Value("Trace")
//     private String text;
//     public TraceAspect() {
//         System.out.println("TraceAspect Constructor - text: " + text);
//     }
//     @PostConstruct
//     public void start(){
//         System.out.println("TraceAspect start method - text: " + text);
//     }
//     @Before("execution(* com.quynhdv.quiz1.MyClass.*(..))")
//     public void beforeTrace(JoinPoint jp){
//         System.out.println(text + " Before method: " + jp.getSignature().getName());
//         if(jp.getTarget() instanceof MyClass){
//             MyClass myClass = (MyClass) jp.getTarget();
//             myClass.setText("Change");
//         }
//     }
// }
// @Component
// class MyClass {
//     private String text;
//     public MyClass() {
//         setText("Hello");
//         System.out.println("MyClass Constructor - text: " + text);
//     }
//     public void sayHello() {
//         System.out.println("This is a " + getText());
//     }

//     public void setText(String text) {
//         System.out.println("Setting text to: " + text);
//         this.text = text;
//     }
//     public String getText() {
//         return text;
//     }
// }
