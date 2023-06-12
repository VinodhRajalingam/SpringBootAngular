package com.srv.springsecurity6.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Setter
@Getter
@Builder
@Table(name = "User",uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@NoArgsConstructor
@AllArgsConstructor
public class MyUser implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_seq", initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "user_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    @Column(name = "authorities")
    private String authority;
    @Column(columnDefinition = "boolean default true")
    private boolean accountNonExpired;
    @Column(columnDefinition = "boolean default true")
    private boolean accountNonLocked;
    @Column(columnDefinition = "boolean default true")
    private boolean credentialsNonExpired;
    @Column(columnDefinition = "boolean default true")
    private boolean enabled;
    public Collection<GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority(getAuthority()));
    }
}
