package pl.most.typer.service.footballservice.matches;

import org.springframework.stereotype.Service;
import pl.most.typer.model.matches.TeamGoals;
import pl.most.typer.repository.footballrepo.TeamGoalsRepository;

import java.util.List;

@Service
public class TeamGoalsServiceDefault implements TeamGoalsService {

    private TeamGoalsRepository teamGoalsRepository;

    public TeamGoalsServiceDefault(TeamGoalsRepository teamGoalsRepository) {
        this.teamGoalsRepository = teamGoalsRepository;
    }

    //TODO Pomyslec nad zapisywaniem tego do bazy (przynajmniej zeby w locie przypisywac ID meczu,
    // zeby drugi raz nie zapisywal)
    @Override
    public void save(List<TeamGoals> teamGoalsFromMatchDTO) {
        for (TeamGoals teamGoalsList : teamGoalsFromMatchDTO) {
            teamGoalsRepository.save(teamGoalsList);

        }
    }
}
