package dh.backend.clinica.service.impl;

import dh.backend.clinica.dao.impl.DaoH2Turno;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import dh.backend.clinica.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    @Override
    public Turno guardarPaciente(Turno turno) {
        return null;
    }

    @Override
    public Optional<Turno> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Turno> buscarTodos() {
        return null;
    }

    @Override
    public void modificarTurnos(Turno turno) {

    }

    @Override
    public void eliminarTurno(Integer id) {

    }
}
