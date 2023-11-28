package com.book.libary.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "authorization_tokens")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationToken extends BaseEntity{

    @Column(name = "token")
    private String token;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "expire_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.JOIN)
    private User user;

}
