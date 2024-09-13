package dh.backend.clinica;

import dh.backend.clinica.dto.request.OdontologoRequestDto;
import dh.backend.clinica.dto.response.OdontologoResponseDto;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    private OdontologoRequestDto odontologo;
    private OdontologoResponseDto odontologoDesdeDB;

    @BeforeEach
    void cargarDatos(){
        odontologo = new OdontologoRequestDto();
        odontologo.setNombre("Juancho");
        odontologo.setMatricula("123");
        odontologo.setApellido("Toro");

        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);
    }

    @Test
    @DisplayName("Testear que un odontologo fue persistido")
    void caso1(){
        assertNotNull(odontologoDesdeDB.getId());
    }


}
