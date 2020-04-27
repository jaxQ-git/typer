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
import pl.most.typer.model.typer.TyperLeagueStanding;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.repository.accountrepo.RoleRepository;
import pl.most.typer.repository.accountrepo.UserRepository;
import pl.most.typer.repository.typerrepo.TyperCompetitionRepository;
import pl.most.typer.repository.typerrepo.TyperLeagueStandingRepository;
import pl.most.typer.repository.typerrepo.TyperPlayerRepository;
import pl.most.typer.repository.typerrepo.TyperStandingRepository;
import pl.most.typer.service.typer.TyperCompetitionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            TyperPlayerRepository typerPlayerRepository,
            TyperLeagueStandingRepository typerLeagueStandingRepository,
            TyperStandingRepository typerStandingRepository,
            TyperCompetitionService typerCompetitionService
    ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                initializeRoleTypesInDb();

                //Generate default user
                User admin = createUser("admin", RoleType.ADMIN);
                User user1 = createUser("user1");
                User user2 = createUser("user2");
                List<User> users = Arrays.asList(admin, user1, user2);
                userRepository.saveAll(users);

                TyperCompetition typerCompetition = new TyperCompetition("Liga Testowa");

                List<TyperPlayer> typerPlayers = users.stream().map(user -> getTyperPlayer(user)).collect(Collectors.toList());
                typerPlayers.stream().forEach(typerPlayer -> typerPlayer.addTyperCompetition(typerCompetition));

                TyperStanding typerStanding = new TyperStanding(typerCompetition);

                typerPlayerRepository.saveAll(typerPlayers);
                typerCompetitionRepository.save(typerCompetition);
                typerStandingRepository.save(typerStanding);

            }

            private TyperPlayer getTyperPlayer(User user) {
                TyperPlayer typerPlayer = new TyperPlayer();
                typerPlayer.setUser(user);
                return typerPlayer;
            }

            private User createUser(String username,  RoleType roleType) {
                User s = User.createUser(
                        username,
                        new BCryptPasswordEncoder().encode("1111"),
                        "test@test.pl"
                );
                s.setEnabled(true);
                s.getRoles().add(roleRepository.findByRoleType(roleType));
                return s;
            }

            private User createUser(String username) {
                return createUser(username, RoleType.USER);
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

