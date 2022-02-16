package co.edu.eci.ieti.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAutentication extends AbstractAuthenticationToken {

    String token;
    String subject;
    List<String> roles;

    public TokenAutentication (String token, String subject, List<String >roles){
        super(null);
        this.token = token;
        this.subject = subject;
        this.roles = roles;
    }

    @Override
    public boolean isAuthenticated(){
        return !token.isEmpty() && !subject.isEmpty() && !roles.isEmpty();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        return roles.stream().map(role -> new SimpleGrantedAuthority("Role_"+role)).collect(Collectors.toList());
    }
}
