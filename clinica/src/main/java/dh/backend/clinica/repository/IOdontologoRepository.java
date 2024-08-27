package dh.backend.clinica.repository;

import dh.backend.clinica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Domicilio,Integer> {
}
