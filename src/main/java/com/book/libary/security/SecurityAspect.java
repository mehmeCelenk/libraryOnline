package com.book.libary.security;

import com.book.libary.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAspect {
    private final SecurityContext securityContext;
    private final HttpServletResponse httpServletResponse;
    private final HttpServletRequest httpServletRequest;

    @Around("execution(* com.book.libary.controller..*.*(..))")
    public Object checkMethodAuthorization(final ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Method          method    = signature.getMethod();

        if(method.isAnnotationPresent(AllowFromIps.class)){
            final AllowFromIps annotation = method.getAnnotation(AllowFromIps.class);
            final Set<String> allowedIps = Set.of(annotation.value());
            if(allowedIps.contains(httpServletRequest.getRemoteAddr())){
                return proceedingJoinPoint.proceed();
            }
        }

        if(method.isAnnotationPresent(AllowAll.class)){
            return proceedingJoinPoint.proceed();
        }

        if(method.isAnnotationPresent(Authenticated.class)){
            final User currentUser = securityContext.getCurrentUser();

            if(currentUser == null){
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                return null;
            }

            final Authenticated authenticatedAnnotation = method.getAnnotation(Authenticated.class);

            if(authenticatedAnnotation.value().length == 0){
                return proceedingJoinPoint.proceed();
            }

            final Set<String> userRole = currentUser.getRoles();
            if(Arrays.stream(authenticatedAnnotation.value()).filter(userRole::contains).collect(Collectors.toSet()).isEmpty() == false){
                return proceedingJoinPoint.proceed();
            }
            throw new AuthenticationException();
        }
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization failed");
        return null;
    }
}

