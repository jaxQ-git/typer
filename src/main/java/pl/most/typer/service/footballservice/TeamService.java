package pl.most.typer.service.footballservice;

import pl.most.typer.model.league.Team;

import java.util.Collection;

public interface TeamService {


    Team save(Team team);

    void saveAll(Collection<Team> teams);
}
