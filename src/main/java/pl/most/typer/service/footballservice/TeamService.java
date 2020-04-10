package pl.most.typer.service.footballservice;

import pl.most.typer.model.league.Team;

import java.util.Collection;

public interface TeamService {


    void saveTeam(Team team);

    void saveTeams(Collection<Team> teams);
}
