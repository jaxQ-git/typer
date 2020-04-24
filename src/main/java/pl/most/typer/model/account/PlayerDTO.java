package pl.most.typer.model.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.most.typer.model.typer.TyperPlayer;

@Data
@NoArgsConstructor
public class PlayerDTO {

    private Integer id;
    private String username;
    private boolean isEnabled;

    public PlayerDTO(TyperPlayer player) {
        this.id = player.getId();
        this.username = player.getUser().getUsername();
        this.isEnabled = player.getUser().isEnabled();
    }
}
