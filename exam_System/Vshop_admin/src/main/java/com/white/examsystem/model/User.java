package com.white.examsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private  Integer id;
    private String userName;
    private String name;
    private Boolean sex;
    private String phone;
    private String password;
    private Boolean isDelete;


    private List<Character> characterList;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(
            Character item:characterList){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+item.getCharacterNameCN()));
        }
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return this.userName;
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
