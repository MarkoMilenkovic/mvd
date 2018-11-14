package rs.mvd.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Optional;

@Aspect
@Component
public class MyInterceptor {

    @AfterThrowing(pointcut = "execution(* rs.mvd.*.*(..))", throwing = "ex")
    public void rollback(JoinPoint joinPoint, Exception ex) {
        Class<? extends Exception> aClass = ex.getClass();
        if (ex instanceof HibernateException){

        }
    }

}
