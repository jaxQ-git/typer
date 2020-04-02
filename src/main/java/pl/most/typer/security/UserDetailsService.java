package pl.most.typer.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails loadByUserName(String username) throws UsernameNotFoundException;
}
