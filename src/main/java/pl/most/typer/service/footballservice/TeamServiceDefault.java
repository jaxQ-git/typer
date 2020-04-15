package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Team;
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
    public Team save(Team team) {
        Optional<Team> optionalTeam = teamRepository.findByApiId(team.getApiId());
        if (optionalTeam.isPresent()) {
            return optionalTeam.get();
        }
        else {
            return teamRepository.save(team);
        }

    }

    @Override
    public void saveAll(Collection<Team> teams) {
        teams.stream().distinct().forEach(this::save);
    }

}
