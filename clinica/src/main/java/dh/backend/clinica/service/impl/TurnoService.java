package dh.backend.clinica.service.impl;

import dh.backend.clinica.model.Turno;
import dh.backend.clinica.repository.ITurnoRepository;
import dh.backend.clinica.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private final ITurnoRepository turnoRepository;

    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Optional<Turno> buscarPorId(Integer id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public void modificarTurno(Turno turno) {
        turnoRepository.save(turno);

    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }
}
