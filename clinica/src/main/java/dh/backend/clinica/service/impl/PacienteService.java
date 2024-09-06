package dh.backend.clinica.service.impl;


import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.repository.IPacienteRepository;
import dh.backend.clinica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private final IPacienteRepository pacienteRepository;

    private final ModelMapper modelMapper;

    public PacienteService(IPacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<PacienteResponseDto> buscarPorId(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            PacienteResponseDto pacienteRespuesta = convertirPacienteEnResponse(paciente.get());
            return Optional.of(pacienteRespuesta);
        }else {
            throw new ResourceNotFoundException("El Paciente no fue encontrado");
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        return pacienteRepository.findByApellidoAndNombre(apellido,nombre);
    }

    @Override
    public List<Paciente> buscarPorUnaParteApellido(String parte) {
        return pacienteRepository.buscarPorParteApellido(parte);
    }

    public PacienteResponseDto convertirPacienteEnResponse(Paciente paciente){
        PacienteResponseDto pacienteResponseDto = modelMapper.map(paciente, PacienteResponseDto.class);
        return pacienteResponseDto;
    }
}
