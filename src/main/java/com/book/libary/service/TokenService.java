package com.book.libary.service;

import com.book.libary.exception.TokenNotFoundException;
import com.book.libary.model.entity.AuthorizationToken;
import com.book.libary.model.entity.User;
import com.book.libary.repository.AuthorizationTokenRepository;
import com.book.libary.security.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
    public static final String TOKEN_HEADER = "Authorization";

    @Value("${application.token-ttl-in-seconds}")
    private int tokenTtlInSeconds;

    private final AuthorizationTokenRepository authorizationTokenRepository;
    private final SecurityContext securityContext;

    public AuthorizationToken generateToken(final User user){
        final AuthorizationToken token = AuthorizationToken.builder()
                .user(user)
                .token(RandomStringUtils.randomAlphanumeric(64))
                .ipAddress(securityContext.getIpAddress())
                .expireAt(DateUtils.addSeconds(new Date(), tokenTtlInSeconds))
                .build();
        return authorizationTokenRepository.save(token);
    }

    public AuthorizationToken getTokenOrFail(final String token)  {
        final AuthorizationToken authorizationToken = authorizationTokenRepository.findByToken(token).orElseThrow(() -> new TokenNotFoundException("Token bulunamadı"));

        if(authorizationToken.getExpireAt().compareTo(new Date()) < 0){
            return null;
        }

        return authorizationToken;
    }

}
