package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Standing;
import pl.most.typer.repository.footballrepo.StandingRepository;

import java.util.List;

@Service
///
public class StandingServiceDefault implements StandingService {

    private StandingRepository standingRepository;

    public StandingServiceDefault(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }

    @Override
    public void saveAll(List<Standing> standings) {
        standingRepository.saveAll(standings);
    }
}
