package dh.backend.clinica.service;

import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.model.Odontologo;

import java.util.Optional;

public interface IOdontologoService {

    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional<OdontologoResponseDto> buscarPorId(Integer id);
}
