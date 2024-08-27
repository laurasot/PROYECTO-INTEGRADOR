package dh.backend.clinica.dao.impl;

import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.db.H2Connection;
import dh.backend.clinica.model.Domicilio;
import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.model.Paciente;
import dh.backend.clinica.model.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class DaoH2Turno implements IDao<Turno> {

    public static final Logger logger = LoggerFactory.getLogger(DaoH2Turno.class);

    private DaoH2Paciente daoH2Paciente;
    private DaoH2Odontologo daoH2Odontologo;

    public static final String INSERT = "INSERT INTO TURNOS VALUES(DEFAULT,?,?,?)";
    public static final String SELECT_ID = "SELECT * FROM TURNOS WHERE ID = ?";
    public static final String SELECT_ALL = "SELECT * FROM TURNOS";
    public DaoH2Turno(DaoH2Paciente daoH2Paciente, DaoH2Odontologo daoH2Odontologo) {
        this.daoH2Paciente = daoH2Paciente;
        this.daoH2Odontologo = daoH2Odontologo;
    }


    @Override
    public Turno guardar(Turno turno) {
        Connection connection = null;
        Turno turnoARetornar = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,turno.getPaciente().getId() );
            preparedStatement.setInt(2,turno.getOdontologo().getId());
            preparedStatement.setDate(3,Date.valueOf(turno.getFecha()));
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                turnoARetornar = new Turno(id, turno.getPaciente(), turno.getOdontologo(), turno.getFecha());
            }
            logger.info("Turno "+ turnoARetornar);

        }catch (Exception e){
            if(connection != null){ //si se cayó a pesar de tener conexion con BD
                try {
                    connection.rollback(); //se hace rollback
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally { //si o si se commitea
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return turnoARetornar;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Connection connection = null;
        Turno turnoEncontrado = null;

        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                Integer idPaciente = resultSet.getInt(2);
                Integer idOdontologo = resultSet.getInt(3);
                LocalDate fecha = resultSet.getDate(4).toLocalDate();

                Paciente paciente = daoH2Paciente.buscarPorId(idPaciente);
                Odontologo odontologo = daoH2Odontologo.buscarPorId(idOdontologo);
                turnoEncontrado = new Turno(paciente,odontologo,fecha);
            }
            if (turnoEncontrado!= null){
                logger.info("turno encontrado "+ turnoEncontrado);
            }else {
                logger.info("turno no encontrado");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return turnoEncontrado;
    }

    @Override
    public List<Turno> listaTodos() {
        Connection connection = null;
        List<Turno> turnos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            Turno turnoDesdeDB = null;
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                Integer idPaciente = resultSet.getInt(2);
                Integer idOdontologo = resultSet.getInt(3);
                LocalDate fecha = resultSet.getDate(4).toLocalDate();

                Paciente paciente = daoH2Paciente.buscarPorId(idPaciente);
                Odontologo odontologo = daoH2Odontologo.buscarPorId(idOdontologo);
                turnoDesdeDB = new Turno(idDB, paciente, odontologo,fecha);
                logger.info("Turno"+turnoDesdeDB);
                turnos.add(turnoDesdeDB);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return turnos;
    }

    @Override
    public void modificar(Turno turno) {

    }

    @Override
    public void eliminar(Integer id) {

    }
}
