package com.example.aircar.domain;

import com.example.aircar.constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User implements OAuth2User {

    private String mno;
    private String email;
    private String password;

    private boolean social;

    private String nickname;
    private Role role;
    private Map<String, Object> attr;

    public MemberSecurityDTO(String username, String password, String email, boolean social,  String nickname,
                              Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);

        this.mno = username;
        this.password = password;
        this.email = email;
        this.social = social;

        this.nickname = nickname;
    }

    public MemberSecurityDTO(
            String username,
            String password,
            boolean Social,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.social = Social;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return this.getAttr();
    }

    @Override
    public String getName() {
        return this.mno;
    }
}
