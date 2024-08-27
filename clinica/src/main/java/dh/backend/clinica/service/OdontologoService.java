package dh.backend.clinica.service;

import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> buscarTodos(){
        return odontologoIDao.listaTodos();
    }

    public Odontologo buscaPorId(Integer id){
        return odontologoIDao.buscarPorId(id);
    }

}
