package com.white.examsystem.config;

import com.white.examsystem.model.Menu;
import com.white.examsystem.model.Character;
import com.white.examsystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private MenuService cdService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requesetUrl=((FilterInvocation) o).getRequestUrl();

        if(requesetUrl.equals("/system/config/menu")){
            return org.springframework.security.access.SecurityConfig.createList("ROLE_login");
        }
        List<Menu> menuList = cdService.getMenuList();
        for(Menu item: menuList){
            if(item.getPath()!=null&&pathMatcher.match(item.getPath(),requesetUrl)){
                List<Character> characterList = item.getCharacterList();
                String[] characterStr= new String[characterList.size()];
                for(int i =0;i< characterList.size();i++){
                    characterStr[i]="ROLE_"+characterList.get(i).getCharacterNameEN();
                }
                return org.springframework.security.access.SecurityConfig.createList(characterStr);
            }
        }
        return org.springframework.security.access.SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
