package pl.most.typer.service.footballservice.competition;

import org.springframework.stereotype.Service;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.competition.Season;
import pl.most.typer.model.competition.Team;
import pl.most.typer.repository.footballrepo.TeamRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceDefault implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceDefault(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean existsByApiId(Integer apiId) {
        return teamRepository.existsByApiId(apiId);
    }

    private Team findByApiId(Integer apiId) {
        Optional<Team> teamOptional = teamRepository.findByApiId(apiId);
        return teamOptional.orElseThrow(() -> {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find Team with id: " + apiId);
            ex.setResource("team");
            return ex;
        });
    }

    private Team save(Team team) {
        if (!existsByApiId(team.getApiId())) {
            return teamRepository.save(team);
        }
        else {
            ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("Team with id: " + team.getApiId()
            );
            ex.setResource("Team");
            ex.setIssue("id");
            throw ex;
        }
    }

    @Override
    public Team saveOrUpdate(Team team) {
            try {
                return save(team);
            } catch (ResourceAlreadyExistsException ex) {
                return update(team);
            }
    }

    @Override
    public void saveAll(Collection<Team> teams) {
        teams = teams.stream().distinct().collect(Collectors.toList());
        for (Team team : teams) {
            try {
                save(team);
            } catch (ResourceAlreadyExistsException ex) {
                //Do nothing
            }
        }
    }

    public Team update(Team team) throws ResourceNotFoundException {
        Team teamDB = findByApiId(team.getApiId());
        teamDB.setCrestUrl(teamDB.getCrestUrl());
        teamDB.setName(teamDB.getName());
        return teamRepository.save(teamDB);
    }
    

}
