package pl.most.typer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import pl.most.typer.data.UserRepository;
import pl.most.typer.security.User;

@SpringBootApplication
public class TyperApplication {
    ///First commit
    public static void main(String[] args) {
        SpringApplication.run(TyperApplication.class, args);
    }


    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                //Generate default user
                User s = new User(
                        "user",
                        new BCryptPasswordEncoder().encode("user"),
                        "test@test.pl"
                );
                s.setEnabled(true);
                userRepository.save(s);
            }
        };
    }
}

