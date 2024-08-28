package dh.backend.clinica.controller;

import dh.backend.clinica.service.impl.OdontologoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


}
