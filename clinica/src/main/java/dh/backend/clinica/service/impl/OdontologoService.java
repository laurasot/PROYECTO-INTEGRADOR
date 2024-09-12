package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.OdontologoModifyDto;
import dh.backend.clinica.dto.request.OdontologoRequestDto;
import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.exception.BadRequestException;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import dh.backend.clinica.repository.IOdontologoRepository;
import dh.backend.clinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService  implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private final IOdontologoRepository odontologoRepository;

    private final ModelMapper modelMapper;

    public OdontologoService(IOdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoResponseDto guardarOdontologo(OdontologoRequestDto odontologoRequestDto) {
        try{
            Odontologo odontologo = new Odontologo();
            odontologo.setApellido(odontologoRequestDto.getApellido());
            odontologo.setMatricula(odontologoRequestDto.getMatricula());
            odontologo.setNombre(odontologo.getNombre());
            Odontologo odontologodesdeDB = odontologoRepository.save(odontologo);
            OdontologoResponseDto odontologoResponseDto = convertirOdontologoEnResponse(odontologodesdeDB);
            return odontologoResponseDto;
        }catch (ResourceNotFoundException e){
            throw new BadRequestException("No se pudo persistir Odontologo");
        }
    }

    @Override
    public Optional<OdontologoResponseDto> buscarPorId(Integer id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()){
            OdontologoResponseDto odontologoRespuesta = convertirOdontologoEnResponse(odontologo.get());
            return Optional.of(odontologoRespuesta);
        }else{
            throw new ResourceNotFoundException("El Odontologo no fue encontrado");
        }

    }

    public List<OdontologoResponseDto> buscarTodos() {
        List<Odontologo> odontologosDesdeBD = odontologoRepository.findAll();
        List<OdontologoResponseDto> odontologosRespuesta = new ArrayList<>();
        for(Odontologo t: odontologosDesdeBD){
            OdontologoResponseDto odontologoRespuesta =convertirOdontologoEnResponse(t);
            logger.info("turno "+ odontologoRespuesta);
            odontologosRespuesta.add(odontologoRespuesta);
        }
        return odontologosRespuesta;
    }
    public void eliminarOdontologo(Integer id) {
        try{
            odontologoRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new BadRequestException("El odontologo no se pudo eliminar");
        }
    }

    public void modificarOdontologo(OdontologoModifyDto odontologoModifyDto) {
        Optional<OdontologoResponseDto> odontologoResponseDto = buscarPorId(odontologoModifyDto.getId());
        if(odontologoResponseDto.isPresent()){
            Odontologo odontologo = new Odontologo(
                    odontologoResponseDto.get().getId(),
                    odontologoModifyDto.getMatricula(),
                    odontologoModifyDto.getNombre(),
                    odontologoModifyDto.getApellido());
            odontologoRepository.save(odontologo);
        }
    }

    public OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo){
        OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
        return odontologoResponseDto;
    }
}
