package pl.most.typer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.most.typer.model.account.Role;
import pl.most.typer.model.account.RoleType;
import pl.most.typer.model.account.User;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.repository.accountrepo.RoleRepository;
import pl.most.typer.repository.accountrepo.UserRepository;
import pl.most.typer.repository.typerrepo.TyperCompetitionRepository;
import pl.most.typer.repository.typerrepo.TyperPlayerRepository;
import pl.most.typer.service.typer.TyperCompetitionService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class TyperApplication {
    ///First commit
    public static void main(String[] args) {
        SpringApplication.run(TyperApplication.class, args);
    }



    @Bean
    public CommandLineRunner dataLoader(
            UserRepository userRepository,
            RoleRepository roleRepository,
            TyperCompetitionRepository typerCompetitionRepository,
            TyperPlayerRepository typerPlayerRepository
    ) {
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
                User save = userRepository.save(s);

                TyperCompetition typerCompetition = new TyperCompetition();
                typerCompetition.setName("defaultLeague");
                typerCompetition.setLastUpdated(LocalDateTime.now());
                typerCompetition.setTyperPlayers(new ArrayList<>());

                TyperPlayer typerPlayer = new TyperPlayer();
                typerPlayer.setUser(save);
                typerPlayer.getTyperCompetitions().add(typerCompetition);
                typerCompetition.getTyperPlayers().add(typerPlayer);
                typerPlayerRepository.save(typerPlayer);
                typerCompetitionRepository.save(typerCompetition);


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

