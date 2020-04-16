package pl.most.typer.service.footballservice.competition;

import pl.most.typer.model.competition.Team;

import java.util.Collection;

public interface TeamService {


    Team save(Team team);

    void saveAll(Collection<Team> teams);
    void saveTeam(Team team);

    void saveTeams(Collection<Team> teams);
}
