package com.book.libary.security;

import com.book.libary.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecurityContext {
    private User currentUser;
    private String ipAddress;
}
