package pl.most.typer.model.typer;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Data
@Entity
public class TyperCompetition extends BaseModel {



    @ManyToMany
    private Collection<TyperPlayer> typerPlayers;

    private String name;

    private LocalDateTime lastUpdated;
}
