package app.messages.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityChecker {

    private static final Logger log = LoggerFactory.getLogger(SecurityCheck.class);

    @Pointcut("@annotation(app.messages.security.SecurityCheck)")
    public void checkMethodSecurity() {}

    @Around("checkMethodSecurity()")
    public Object checkSecurity(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Checking method security...");
        // Security checking login to be here
        Object result = joinPoint.proceed();
        return result;
    }

}
