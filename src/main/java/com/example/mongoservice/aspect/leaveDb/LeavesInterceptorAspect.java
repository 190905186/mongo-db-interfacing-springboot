package com.example.mongoservice.aspect.leaveDb;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LeavesInterceptorAspect {
    @Before("execution(* getAllEmployees(..))")
    public void beforeAdvice(){
        System.out.println("Executing the getAllCustomers function");
    }
    @After("execution(* getAllEmployees(..))")
    public void afterAdvice(){
        System.out.println("Executed the getAllCustomers function");
    }
    @Around("execution(* getAllEmployees(..))")
    public void aroundAdvice(){
        System.out.println("around the getAllCustomers function");
    }
}
