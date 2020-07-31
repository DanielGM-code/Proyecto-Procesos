/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.datos.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemadegestióndeplanesdecurso.datos.ConexionMySQL;
import sistemadegestióndeplanesdecurso.datos.dao.Dao;
import sistemadegestióndeplanesdecurso.entidades.Directivo;

/**
 *
 * @author angel
 */
public class DirectivoDaoImpl implements Dao{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public DirectivoDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }
    @Override
    public List<Object> getAll() {
        Directivo directivo = null;
        List<Object> directivos = new ArrayList<Object>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from directivo";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            sentencia.close();
            while(resultadoConsulta.next()){
                directivo = new Directivo();
                directivo.setNumeroPersonalDirectivo(resultadoConsulta.getString("numeroPersonalDirectivo"));
                directivo.setNombreDirectivo(resultadoConsulta.getString("nombreDirectivo"));
                directivo.setApellidoPaternoDirectivo(resultadoConsulta.getString("apellidoPaternoDirectivo"));
                directivo.setApellidoMaternoDirectivo(resultadoConsulta.getString("apellidoMaternoDirectivo"));
                directivo.setTelefonoDirectivo(resultadoConsulta.getString("telefonoDirectivo"));
                directivo.setEmailDirectivo(resultadoConsulta.getString("emailDirectivo"));
                directivo.setEstadoDirectivo(resultadoConsulta.getString("estadoDirectivo"));
                directivos.add(directivo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return directivos;
    }

    @Override
    public Object getByID(String id) {
        Directivo directivo = null;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from directivo where numeroPersonal = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, id);
            resultadoConsulta = sentencia.executeQuery();
            sentencia.close();
            while(resultadoConsulta.next()){
                directivo = new Directivo();
                directivo.setNumeroPersonalDirectivo(resultadoConsulta.getString("numeroPersonalDirectivo"));
                directivo.setNombreDirectivo(resultadoConsulta.getString("nombreDirectivo"));
                directivo.setApellidoPaternoDirectivo(resultadoConsulta.getString("apellidoPaternoDirectivo"));
                directivo.setApellidoMaternoDirectivo(resultadoConsulta.getString("apellidoMaternoDirectivo"));
                directivo.setTelefonoDirectivo(resultadoConsulta.getString("telefonoDirectivo"));
                directivo.setEmailDirectivo(resultadoConsulta.getString("emailDirectivo"));
                directivo.setEstadoDirectivo(resultadoConsulta.getString("estadoDirectivo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return directivo;
    }

    @Override
    public void save(Object object) {
        Directivo directivo = (Directivo) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "insert into directivo (numeroPersonalDirectivo, nombreDirectivo"
                    + "apellidoPaternoDirectivo, apellidoMaternoDirectivo, telefonoDirectivo"
                    + "emailDirectivo, estadoDirectivo) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, directivo.getNumeroPersonalDirectivo());
            sentencia.setString(2, directivo.getNombreDirectivo());
            sentencia.setString(3, directivo.getApellidoPaternoDirectivo());
            sentencia.setString(4, directivo.getApellidoMaternoDirectivo());
            sentencia.setString(5, directivo.getTelefonoDirectivo());
            sentencia.setString(6, directivo.getEmailDirectivo());
            sentencia.setString(7, directivo.getEstadoDirectivo());
            sentencia.execute();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        Directivo directivo = (Directivo) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "update directivo set estadoDirectivo = \"Inactivo\" where numeroPersonal = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, directivo.getNumeroPersonalDirectivo());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
