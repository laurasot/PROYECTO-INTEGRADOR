package dh.backend.clinica.service;

import dh.backend.clinica.model.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardarPaciente(Turno turno);

    Optional<Turno> buscarPorId(Integer id);
    List<Turno> buscarTodos();

    void modificarTurnos(Turno turno);

    void eliminarTurno(Integer id);
}
