package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.repository.IOdontologoRepository;
import dh.backend.clinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService  implements IOdontologoService {

    private final IOdontologoRepository odontologoRepository;

    private final ModelMapper modelMapper;

    public OdontologoService(IOdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
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
    public OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo){
        OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
        return odontologoResponseDto;
    }
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }
    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);
    }

    public void modificarOdontologo(Odontologo odontologo) { odontologoRepository.save(odontologo);
    }
}
