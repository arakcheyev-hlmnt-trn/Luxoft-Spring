package com.luxoft.springaop.lab5.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.luxoft.springaop.lab5.exceptions.ValidationException;

@Aspect
public class ValidationLoggingAspect {
  private final static Logger LOG = Logger.getLogger(ValidationLoggingAspect.class.getName());


  @Pointcut("execution(* *.setAge(..))")
  public void setterMethod() {
  }

  @AfterThrowing(pointcut = "setterMethod()", throwing = "e")
  public void validationExceptionLogger(JoinPoint joinPoint, ValidationException e) {
    String methodName = joinPoint.getSignature().getName();
    Object[] methodArgs = joinPoint.getArgs();
    StringBuilder args = new StringBuilder();
    for (Object arg : methodArgs) {
      if (args.length() > 0) {
        args.append(", ");
      }
      args.append(arg.toString());
    }
    LOG.severe("ValidationException in method " + methodName + " with args " + args);
  }
}
