package dh.backend.clinica.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoModifyDto {
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;

}
