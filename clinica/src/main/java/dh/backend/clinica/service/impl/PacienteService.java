package dh.backend.clinica.service.impl;


import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return null;
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Paciente> buscarTodos() {
        return null;
    }

    @Override
    public void modificarPaciente(Paciente paciente) {

    }

    @Override
    public void eliminarPaciente(Integer id) {

    }
}
