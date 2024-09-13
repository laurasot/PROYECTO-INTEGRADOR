package dh.backend.clinica;

import dh.backend.clinica.dto.request.OdontologoRequestDto;
import dh.backend.clinica.dto.request.TurnoReuquestDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import dh.backend.clinica.service.impl.OdontologoService;
import dh.backend.clinica.service.impl.PacienteService;
import dh.backend.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    private TurnoReuquestDto turno;

    private TurnoResponseDto turnoDesdeDB;

    @BeforeEach
    void cargarDatos(){
        Paciente paciente = new Paciente(null, "juanchito", "apolo", "123",null, null,null);
        OdontologoRequestDto odontologo = new OdontologoRequestDto("hols","manolo", "perez");

        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);
        turno = new TurnoReuquestDto();
        turno.setFecha("2024-08-30");
        turno.setOdontologo_id(1);
        turno.setPaciente_id(1);

        turnoDesdeDB = turnoService.guardarTurno(turno);
    }

    @Test
    @DisplayName("Testear que un turno fue persistido")
    void caso1(){
        assertNotNull(turnoDesdeDB.getId());
    }

}
