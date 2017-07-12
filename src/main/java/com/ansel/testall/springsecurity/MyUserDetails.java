package com.ansel.testall.springsecurity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails{  
    private Integer userId;  
    private String username;  
    private String password;  
    private boolean enabled;  
    private Collection<? extends GrantedAuthority> authorities;  
      
    public MyUserDetails(Integer userId, String username, String password, boolean enabled) {  
        super();  
        this.userId = userId;  
        this.username = username;  
        this.password = password;  
        this.enabled = enabled;  
    }  
      
    public MyUserDetails(Integer userId, String username, String password, boolean enabled,  
            Collection<? extends GrantedAuthority> authorities) {  
        super();  
        this.userId = userId;  
        this.username = username;  
        this.password = password;  
        this.enabled = enabled;  
        this.authorities = authorities;  
    }  
    public Integer getUserId(){  
        return this.userId;  
    }  
  
    @Override  
    public Collection<? extends GrantedAuthority> getAuthorities() {  
        return authorities;  
    }  
    @Override  
    public String getPassword() {  
        return password;  
    }  
    @Override  
    public String getUsername() {  
        return username;  
    }  
    @Override  
    public boolean isAccountNonExpired() {  
        return true;  
    }  
    @Override  
    public boolean isAccountNonLocked() {  
        return true;  
    }  
    @Override  
    public boolean isCredentialsNonExpired() {  
        return true;  
    }  
    @Override  
    public boolean isEnabled() {  
        return enabled;  
    }  
  
    @Override  
    public String toString() {  
        return "MyUserDetails [userId=" + userId + ", username=" + username  
                + ", password=" + password + ", enabled=" + enabled  
                + ", authorities=" + authorities + "]";  
    }  
  
  
}  