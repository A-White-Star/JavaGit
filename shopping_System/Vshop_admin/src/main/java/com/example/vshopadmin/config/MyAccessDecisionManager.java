package com.example.vshopadmin.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for(ConfigAttribute attr:collection){
            if(attr.getAttribute().equals("ROLE_login")){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("非法请求！");
                }else {
                    return;
                }
            }
            Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
            for(GrantedAuthority auth:authorities){
                if(attr.getAttribute().equals(auth.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("非法请求！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
