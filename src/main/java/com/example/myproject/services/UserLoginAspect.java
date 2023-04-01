package com.example.myproject.services;


import com.example.myproject.entity.UserLog;
import com.example.myproject.repository.UserLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;

@Aspect
@Component
public class UserLoginAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserLogRepository userLogRepository;

    @Around("execution(* com.example.myproject.services.UserService.addUser(..))")
    public Object logUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        String methodName = joinPoint.getSignature().getName();
        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        LocalDateTime dateTime = LocalDateTime.now();
        // get client IP address
        InetAddress inetAddress = InetAddress.getLocalHost();
        String clientIp = inetAddress.getHostAddress();

        String browserInfo = request.getHeader("User-Agent");


        try {
            result = joinPoint.proceed();
            logger.info("User saved successfully.");
        } catch (Exception e) {
            logger.error("Error saving user.", e);
            throw e;
        }

        UserLog userLog = new UserLog();
        userLog.setMethodName(methodName);
        userLog.setPackageName(packageName);
        userLog.setDateTime(dateTime);
        userLog.setClientIp(clientIp);
        userLog.setBrowserInfo(browserInfo);

        userLogRepository.save(userLog);

        return result;
    }
}
