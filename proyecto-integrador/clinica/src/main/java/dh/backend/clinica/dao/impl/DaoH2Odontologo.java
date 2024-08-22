package dh.backend.clinica.dao.impl;


import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.db.H2Connection;
import dh.backend.clinica.model.Odontologo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DaoH2Odontologo  implements IDao<Odontologo> {
    public static final Logger logger = LoggerFactory.getLogger(DaoH2Odontologo.class);

    public static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES(DEFAULT,?,?,?)";

    public static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    public static final String SELECT_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();//devuelve un objeto ResultSet que contiene las claves generadas.
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(idDB, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }
            logger.info( "Odontologo persistido "+ odontologoARetornar);

        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally {
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
        return odontologoARetornar;

    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo odontologoEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                String matricula = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologoEncontrado = new Odontologo(idDB, matricula,nombre,apellido);
            }
            if(odontologoEncontrado!= null){
                logger.info("odontologo encontrado "+ odontologoEncontrado);
            } else {
                logger.info("odontologo no encontrado");
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
        return odontologoEncontrado;
    }


    @Override
    public List<Odontologo> listaTodos() {
        Connection connection = null;
        Odontologo odontologoARetornar = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){ //por cada registro encontrado
                Integer id = resultSet.getInt(1);
                String matricula = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologoARetornar = new Odontologo(id,matricula, nombre, apellido);
                odontologos.add(odontologoARetornar);
                logger.info("odontologo"+ odontologoARetornar);
            }
            connection.setAutoCommit(false);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    return  odontologos;
    }
}
