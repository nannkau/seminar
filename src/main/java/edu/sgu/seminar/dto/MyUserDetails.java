package edu.sgu.seminar.dto;

import edu.sgu.seminar.entity.Role;
import edu.sgu.seminar.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private String userName ;
    private String passsword;
    private String active;

    private List<GrantedAuthority> authorites;
    public MyUserDetails(User user){

        this.userName= user.getEmail();
        this.passsword=user.getPassword();
        this.active=user.getActive();
        List<GrantedAuthority> role= new ArrayList<>();

        for(Role i:user.getRoles()){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(i.getCode());
            role.add(simpleGrantedAuthority);
        }
        this.authorites= role;



    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorites;
    }

    @Override
    public String getPassword() {
        return passsword;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return this.active.equals("0")?true:false;
    }

}
