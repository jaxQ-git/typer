package pl.most.typer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.most.typer.model.account.Role;
import pl.most.typer.model.account.RoleType;
import pl.most.typer.model.account.User;
import pl.most.typer.repository.accountrepo.RoleRepository;
import pl.most.typer.repository.accountrepo.UserRepository;

@SpringBootApplication
public class TyperApplication {
    ///First commit
    public static void main(String[] args) {
        SpringApplication.run(TyperApplication.class, args);
    }

//FIXME zakomentowałem bo baza się krzaczyła przy kolejnym uruchomieniu aplikacji

    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                initializeRoleTypesInDb();

                //Generate default user
                User s = User.createUser(
                        "user",
                        new BCryptPasswordEncoder().encode("user"),
                        "test@test.pl"
                );
                s.setEnabled(true);
                s.getRoles().add(roleRepository.findByRoleType(RoleType.ADMIN));
                userRepository.save(s);
            }

            private void initializeRoleTypesInDb() {
                for (RoleType roleType : RoleType.values()) {
                    Role role = new Role(roleType);
                    roleRepository.save(role);
                }
            }
        };
    }
}

