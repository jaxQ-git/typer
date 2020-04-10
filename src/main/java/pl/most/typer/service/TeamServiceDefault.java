package pl.most.typer.service;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Team;
import pl.most.typer.repository.TeamRepository;

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
}
