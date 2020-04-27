package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.most.typer.exceptions.BadResourceException;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.account.PlayerDTO;
import pl.most.typer.model.account.User;
import pl.most.typer.model.typer.TyperCompetition;
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

    //PlayerDTO, żeby nie przekazywać informacji o userze
    @Override
    public List<PlayerDTO> findAll() {
        List<TyperPlayer> typerPlayers = typerPlayerRepository.findAll();
        return typerPlayers.stream().map(typerPlayer -> new PlayerDTO(typerPlayer)).collect(Collectors.toList());
    }

    @Override
    public TyperPlayer findById(Integer id) {
        Optional<TyperPlayer> typerPlayerOptional = typerPlayerRepository.findById(id);
        return typerPlayerOptional.orElseThrow(() -> {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find TyperPlayer with id: " + id);
            ex.setResource("player");
            return ex;
        });
    }

    @Override
    public TyperPlayer save(TyperPlayer typerPlayer) throws BadResourceException, ResourceAlreadyExistsException {
        if ((typerPlayer.getUser() != null)) {
            if (existsByUser(typerPlayer.getUser())) {
                ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("TyperPlayer already exists");
                ex.setResource("TyperPlayer");
                ex.setIssue("user");
                throw ex;
            }
            return typerPlayerRepository.save(typerPlayer);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save TyperPlayer");
            throw exc;
        }
    }

    @Override
    public TyperPlayer update(TyperPlayer typerPlayer) {

        if ((typerPlayer.getUser() != null) && typerPlayer.getId()!=null && existsById(typerPlayer)) {
            //Jeżeli jest już w bazie inny obiekt TyperPlayer,
            // który ma takiego usera to nie można zdublowac.
            // Jeżeli to ten sam TyperPlayer to robimy update
            if (!findByUser(typerPlayer.getUser()).equals(findById(typerPlayer.getId()))) {
                ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("TyperPlayer already exists");
                ex.setResource("TyperPlayer");
                ex.setIssue("user");
                throw ex;
            }
            TyperPlayer typerPlayerDB = findById(typerPlayer.getId());
            typerPlayerDB.setUser(typerPlayer.getUser());
            typerPlayerDB.setName(typerPlayer.getName());
            typerPlayerDB.setSurname(typerPlayer.getSurname());
            typerPlayerDB.setTyperCompetitions(typerPlayerDB.getTyperCompetitions());
            return typerPlayerRepository.save(typerPlayerDB);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save TyperPlayer");
            throw exc;
        }
    }

    private boolean existsById(TyperPlayer typerPlayer) {
        return typerPlayerRepository.existsById(typerPlayer.getId());
    }

    private boolean existsByUser(User user) {
        return typerPlayerRepository.existsByUser(user);
    }

    private TyperPlayer findByUser(User user) {
        Optional<TyperPlayer> typerPlayerOptional = typerPlayerRepository.findByUser(user);
        return typerPlayerOptional.orElseThrow(() -> {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find TyperPlayer with user: " + user.getUsername());
            ex.setResource("player");
            return ex;
        });
    }
}

