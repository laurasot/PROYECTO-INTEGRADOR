package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.TurnoModifyDto;
import dh.backend.clinica.dto.request.TurnoReuquestDto;
import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import dh.backend.clinica.repository.ITurnoRepository;
import dh.backend.clinica.service.IOdontologoService;
import dh.backend.clinica.service.IPacienteService;
import dh.backend.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private final ITurnoRepository turnoRepository;
    private final IPacienteService pacienteService;

    private final IOdontologoService odontologoService;

    private final ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, IPacienteService pacienteService, IOdontologoService odontologoService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
    }

    private TurnoResponseDto convertirTurnoEnResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        return turnoResponseDto;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoReuquestDto turnoRequestDto) {

        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService .buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turno = new Turno();
        Turno turnoDesdeBD = null;
        TurnoResponseDto turnoResponseDto = null;
        if(paciente.isPresent() && odontologo.isPresent()){
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));

            turnoDesdeBD = turnoRepository.save(turno);
            turnoResponseDto = convertirTurnoEnResponse(turnoDesdeBD);
        }
        return turnoResponseDto;
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        TurnoResponseDto turnoRespuesta = convertirTurnoEnResponse(turno.get());
        return Optional.of(turnoRespuesta);
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnosDesdeBD = turnoRepository.findAll();
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for(Turno t: turnosDesdeBD){
            // manera manual
            //turnosRespuesta.add(obtenerTurnoResponse(t));
            // model mapper
            turnosRespuesta.add(convertirTurnoEnResponse(t));
        }
        return turnosRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModifyDto turnoModifyDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoModifyDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoModifyDto.getOdontologo_id());
        if(paciente.isPresent() && odontologo.isPresent()){
            Turno turno = new Turno(
                    turnoModifyDto.getId(),
                    paciente.get(), odontologo.get(), LocalDate.parse(turnoModifyDto.getFecha())
            );
            turnoRepository.save(turno);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }
}
