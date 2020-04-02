package pl.most.typer.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.most.typer.data.UserRepository;

import java.util.Optional;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadByUserName(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return  user.orElseThrow(() -> new UsernameNotFoundException("Użytkownik '" +username + "' nie został znaleziony!" ));

    }
}
