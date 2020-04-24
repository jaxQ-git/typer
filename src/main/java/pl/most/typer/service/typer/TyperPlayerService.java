package pl.most.typer.service.typer;

import pl.most.typer.model.account.PlayerDTO;
import pl.most.typer.model.typer.TyperPlayer;

import java.util.List;

public interface TyperPlayerService {


    List<PlayerDTO> findAll();

    TyperPlayer findById(Integer id);
}
