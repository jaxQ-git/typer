package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import pl.most.typer.model.account.PlayerDTO;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.repository.typerrepo.TyperPlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TyperPlayerServiceDefault implements TyperPlayerService {

    private TyperPlayerRepository typerPlayerRepository;

    public TyperPlayerServiceDefault(TyperPlayerRepository typerPlayerRepository) {
        this.typerPlayerRepository = typerPlayerRepository;
    }


    @Override
    public List<PlayerDTO> findAll() {
        List<TyperPlayer> typerPlayers = typerPlayerRepository.findAll();
        return typerPlayers.stream().map(typerPlayer -> new PlayerDTO(typerPlayer)).collect(Collectors.toList());
    }

    @Override
    public TyperPlayer findById(Integer id) {
        Optional<TyperPlayer> typerPlayer = typerPlayerRepository.findById(id);
        if (typerPlayer.isPresent()) {
            return typerPlayer.get();
        }
        //TODO throw exception istead of return null
        return null;
    }
}
