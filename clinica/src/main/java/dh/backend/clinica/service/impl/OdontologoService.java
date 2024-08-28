package dh.backend.clinica.service.impl;

import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.repository.IOdontologoRepository;
import dh.backend.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService  implements IOdontologoService {

    private final IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
    }
}
