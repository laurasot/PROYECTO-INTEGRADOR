package dh.backend.clinica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdontologoRequestDto {
    private String matricula;

    private String nombre;

    private String apellido;
}
