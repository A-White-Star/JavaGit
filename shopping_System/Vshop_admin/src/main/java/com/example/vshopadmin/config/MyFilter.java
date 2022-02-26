package com.example.vshopadmin.config;

import com.example.vshopadmin.model.CaiDan;
import com.example.vshopadmin.model.JueSe;
import com.example.vshopadmin.service.CaiDanService;
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
    private CaiDanService cdService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requesetUrl=((FilterInvocation) o).getRequestUrl();

        if(requesetUrl.equals("/system/config/caidan")){
            return org.springframework.security.access.SecurityConfig.createList("ROLE_login");
        }
        List<CaiDan> caiDanList = cdService.getCaiDanList();
        for(CaiDan item:caiDanList){
            if(item.getPath()!=null&&pathMatcher.match(item.getPath(),requesetUrl)){
                List<JueSe> jueSes = item.getJueSeList();
                String[] jsStr= new String[jueSes.size()];
                for(int i =0;i< jueSes.size();i++){
                    jsStr[i]="ROLE_"+jueSes.get(i).getJueSeMingCheng();
                }
                return org.springframework.security.access.SecurityConfig.createList(jsStr);
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
