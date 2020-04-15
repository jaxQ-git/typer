package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Team;
import pl.most.typer.repository.footballrepo.TeamRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceDefault implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceDefault(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void saveTeam(Team team) {
        if (teamRepository.existsByApiId(team.getApiId())) {
            return;
        }
        teamRepository.save(team);

    }

    @Override
    public void saveTeams(Collection<Team> teams) {
        List<Team> distinctTeams = getDistinctTeams(teams);
        List<Team> uniqueTeams = distinctTeams.stream()
                .filter(team -> !teamRepository.existsByApiId(team.getApiId()))
                .collect(Collectors.toList());
        teamRepository.saveAll(uniqueTeams);
    }

    private List<Team> getDistinctTeams(Collection<Team> teams) {
        return teams.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
