package dh.backend.clinica.model;

import dh.backend.clinica.utils.GsonProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;


    @Override
    public String toString() {
        return GsonProvider.getGson().toJson(this);
    }

}
