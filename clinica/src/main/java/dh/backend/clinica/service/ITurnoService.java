package dh.backend.clinica.service;

import dh.backend.clinica.dto.request.TurnoModifyDto;
import dh.backend.clinica.dto.request.TurnoReuquestDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.model.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoReuquestDto turnoRequestDto);

    Optional<TurnoResponseDto> buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();

    void modificarTurno(TurnoModifyDto turno);

    void eliminarTurno(Integer id);
}
