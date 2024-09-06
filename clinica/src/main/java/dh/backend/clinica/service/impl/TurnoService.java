package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.TurnoModifyDto;
import dh.backend.clinica.dto.request.TurnoReuquestDto;
import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.exception.BadRequestException;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import dh.backend.clinica.repository.ITurnoRepository;
import dh.backend.clinica.service.IOdontologoService;
import dh.backend.clinica.service.IPacienteService;
import dh.backend.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);

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
        try{
            Optional<PacienteResponseDto> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
            Optional<OdontologoResponseDto> odontologo = odontologoService.buscarPorId(turnoRequestDto.getOdontologo_id());
            Turno turno = new Turno();
            Turno turnoDesdeBD = null;
            TurnoResponseDto turnoResponseDto = null;
            turno.setPaciente(modelMapper.map(paciente, Paciente.class));
            turno.setOdontologo(modelMapper.map(odontologo, Odontologo.class));
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoDesdeBD = turnoRepository.save(turno);
            turnoResponseDto = convertirTurnoEnResponse(turnoDesdeBD);
            System.out.println("hola");

            return turnoResponseDto;

        }catch (ResourceNotFoundException e){
            System.out.println("no hola");
            throw new BadRequestException("Paciente u odontologo no existen en la base de datos");
        }
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()){
            TurnoResponseDto turnoRespuesta = convertirTurnoEnResponse(turno.get());
            return Optional.of(turnoRespuesta);
        }else{
            throw new ResourceNotFoundException("El turno no fue encontrado");
        }
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnosDesdeBD = turnoRepository.findAll();
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for(Turno t: turnosDesdeBD){
            TurnoResponseDto turnoRespuesta =convertirTurnoEnResponse(t);
            logger.info("turno "+ turnoRespuesta);
            turnosRespuesta.add(turnoRespuesta);
        }
        return turnosRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModifyDto turnoModifyDto) {
        Optional<PacienteResponseDto> paciente = pacienteService.buscarPorId(turnoModifyDto.getPaciente_id());
        Optional<OdontologoResponseDto> odontologo = odontologoService.buscarPorId(turnoModifyDto.getOdontologo_id());
        if(paciente.isPresent() && odontologo.isPresent()){
            Turno turno = new Turno(
                    turnoModifyDto.getId(),
                    modelMapper.map(paciente.get(), Paciente.class),modelMapper.map(odontologo.get(), Odontologo.class),
                    LocalDate.parse(turnoModifyDto.getFecha())
            );
            turnoRepository.save(turno);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Optional<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido) {
        Optional<Turno> turno = turnoRepository.buscarPorApellidoPaciente(pacienteApellido);
        TurnoResponseDto turnoParaResponder = null;
        if(turno.isPresent()) {
            turnoParaResponder = convertirTurnoEnResponse(turno.get());
        }
        return Optional.ofNullable(turnoParaResponder);
    }
}
