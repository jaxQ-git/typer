package pl.most.typer.service.footballservice.matches;

import org.springframework.stereotype.Service;
import pl.most.typer.model.matches.Score;
import pl.most.typer.model.matches.TeamGoals;
import pl.most.typer.repository.footballrepo.TeamGoalsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeamGoalsServiceDefault implements TeamGoalsService {

    private TeamGoalsRepository teamGoalsRepository;

    public TeamGoalsServiceDefault(TeamGoalsRepository teamGoalsRepository) {
        this.teamGoalsRepository = teamGoalsRepository;
    }

    @Override
    public void saveAll(List<TeamGoals> teamGoalsFromMatchDTO) {
        for (TeamGoals teamGoals : teamGoalsFromMatchDTO) {
            Optional<TeamGoals> byId = teamGoalsRepository.findById(teamGoals.getScore().getId());
            byId.orElseGet(() -> teamGoalsRepository.save(teamGoals));
        }
    }
}
