package dh.backend.clinica.repository;

import dh.backend.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

    List<Paciente> findByApellidoAndNombre(String apellido, String nombre);

    @Query("SELECT p from Paciente p WHERE p.turnoSet IS EMPTY")
    List<Paciente> buscarPacienteSinTurno();

    List<Paciente> findByApellidoContainingIgnoreCase(String apellido);

    @Query("Select p from Paciente p where LOWER(p.apellido) LIKE LOWER(CONCAT('%',:parteApellido,'%'))")
    List<Paciente> buscarPorParteApellido(String parteApellido);
}
