package dh.backend.clinica.service;

import dh.backend.clinica.dao.impl.DaoH2Turno;
import dh.backend.clinica.model.Turno;

import java.util.List;

public class TurnoService {

    private DaoH2Turno daoH2Turno;

    public TurnoService(DaoH2Turno daoH2Turno) {
        this.daoH2Turno = daoH2Turno;
    }
    public Turno buscarPorId(Integer id){
        return daoH2Turno.buscarPorId(id);
    }
    public List<Turno> buscarTodos(){
        return daoH2Turno.listaTodos();
    }
}
