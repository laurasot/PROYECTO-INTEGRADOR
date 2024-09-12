package dh.backend.clinica.repository;

import dh.backend.clinica.model.Domicilio;
import dh.backend.clinica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Integer> {

    List<Odontologo> findAllByApellidoNotContainingIgnoreCase(String apellido);
}
