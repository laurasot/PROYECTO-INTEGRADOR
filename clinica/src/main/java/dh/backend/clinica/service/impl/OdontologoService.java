package dh.backend.clinica.service.impl;

import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService  implements IOdontologoService {


    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return null;
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return Optional.empty();
    }
}
