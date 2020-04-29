package pl.most.typer.service.footballservice.competition;

import pl.most.typer.model.competition.Team;

import java.util.Collection;

public interface TeamService {


    boolean existsByApiId(Integer apiId);

    Team saveOrUpdate(Team team);

    void saveAll(Collection<Team> teams);
}
