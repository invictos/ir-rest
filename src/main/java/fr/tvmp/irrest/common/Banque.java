package fr.tvmp.irrest.common;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Banque {
    @NonNull private String iban;
}
