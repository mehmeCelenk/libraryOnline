package com.book.libary.security;

import com.book.libary.model.entity.AuthorizationToken;
import com.book.libary.service.TokenService;
import com.book.libary.util.IPUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.book.libary.service.TokenService.TOKEN_HEADER;


@WebFilter(urlPatterns = "/*")
@RequiredArgsConstructor
@Service
public class TokenFilter implements Filter {
    public final TokenService tokenService;
    public final SecurityContext securityContext;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest  request  = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String              token    = request.getHeader(TOKEN_HEADER);

        securityContext.setIpAddress(IPUtils.getCurrentIpAddress(request));

        checkTokenAndInjectCurrenUserOrFail(token);

        filterChain.doFilter(request, response);

    }

    private void checkTokenAndInjectCurrenUserOrFail(final String token) {
        if(Strings.isBlank(token)){
            return;
        }

        final AuthorizationToken authorizationToken = tokenService.getTokenOrFail(token);
        securityContext.setCurrentUser(authorizationToken.getUser());
    }
}
