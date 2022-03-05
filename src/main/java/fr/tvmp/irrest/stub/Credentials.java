package fr.tvmp.irrest.stub;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class Credentials {
    @NotNull UUID uuid;
    @NotNull String password;
}
