package dh.backend.clinica.service;

import dh.backend.clinica.dao.impl.DaoH2Turno;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService {

    private DaoH2Turno daoH2Turno;

    public TurnoService(DaoH2Turno daoH2Turno) {
        this.daoH2Turno = daoH2Turno;
    }

    public Turno guardarTurno(Turno turno){
        return daoH2Turno.guardar(turno);
    }
    public Turno buscarPorId(Integer id){
        return daoH2Turno.buscarPorId(id);
    }
    public List<Turno> buscarTodos(){
        return daoH2Turno.listaTodos();
    }

    public void eliminarTurno(Integer id){
    }

    public Paciente modificarTurno(Turno turno){
        return null;
    }
}
