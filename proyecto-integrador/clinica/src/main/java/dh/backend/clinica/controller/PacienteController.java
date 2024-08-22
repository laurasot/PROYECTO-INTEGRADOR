package dh.backend.clinica.controller;

import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.service.OdontologoService;
import dh.backend.clinica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PacienteController {

    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public PacienteController(PacienteService pacienteService, OdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/index/{idPaci}/{idOdo}")
    public String mostrarClinica(Model model, @PathVariable Integer idPaci, @PathVariable Integer idOdo){
        Paciente paciente = pacienteService.buscarPorId(idPaci);

        Odontologo odontologo = odontologoService.buscaPorId(idOdo);

        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        model.addAttribute("odontologo", odontologo);
        return "index";
    }
}
