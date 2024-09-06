package dh.backend.clinica.service;

import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    Paciente guardarPaciente(Paciente paciente);

    Optional<PacienteResponseDto> buscarPorId(Integer id);
    List<Paciente> buscarTodos();

    void modificarPaciente(Paciente paciente);

    void eliminarPaciente(Integer id);

    List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre);
    List<Paciente> buscarPorUnaParteApellido(String parte);
}
