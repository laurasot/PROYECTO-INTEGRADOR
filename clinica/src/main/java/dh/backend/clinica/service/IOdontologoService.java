package dh.backend.clinica.service;

import dh.backend.clinica.dto.request.OdontologoRequestDto;
import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.model.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    OdontologoResponseDto guardarOdontologo(OdontologoRequestDto odontologoRequestDto);

    Optional<OdontologoResponseDto> buscarPorId(Integer id);


    List<OdontologoResponseDto> buscarTodos();
}
