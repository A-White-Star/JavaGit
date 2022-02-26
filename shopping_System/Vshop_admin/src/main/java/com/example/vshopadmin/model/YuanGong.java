package com.example.vshopadmin.model;

import ch.qos.logback.classic.jul.JULHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.el.parser.BooleanNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YuanGong implements UserDetails {
    private  Integer id;
    private String yongHuMing;
    private String zhenShiXingMing;
    private Boolean xingBie;
    private String shouJiHao;
    private String miMa;
    private Boolean isDelete;


    private List<JueSe> jueSeList;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(JueSe item:jueSeList){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+item.getJueSeMingCheng()));
        }
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return this.miMa;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return this.yongHuMing;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !this.isDelete;
    }
}
