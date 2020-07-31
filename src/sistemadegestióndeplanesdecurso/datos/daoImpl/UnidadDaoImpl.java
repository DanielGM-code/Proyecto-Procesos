/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.datos.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemadegestióndeplanesdecurso.datos.ConexionMySQL;
import sistemadegestióndeplanesdecurso.entidades.Unidad;

/**
 *
 * @author angel
 */
public class UnidadDaoImpl{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public UnidadDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }

    public List<Unidad> getAll() {
        Unidad unidad = null;
        List<Unidad> unidades = new ArrayList<Unidad>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from unidad";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                unidad = new Unidad();
                unidad.setNrc(resultadoConsulta.getString("nrc"));
                unidad.setNumeroUnidad(resultadoConsulta.getInt("numeroUnidad"));
                unidad.setTemaUnidad(resultadoConsulta.getString("temaUnidad"));
                unidad.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                unidad.setFechaFin(resultadoConsulta.getString("fechaFin"));
                unidad.setTareas(resultadoConsulta.getString("tareas"));
                unidad.setTecnicas(resultadoConsulta.getString("tecnicas"));
                unidades.add(unidad);
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidades;
    }

    public List<Unidad> getByID(String nrc) {
        Unidad unidad = null;
        List<Unidad> unidades = new ArrayList<Unidad>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from unidad where nrc = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, nrc);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                unidad = new Unidad();
                unidad.setNrc(resultadoConsulta.getString("nrc"));
                unidad.setNumeroUnidad(resultadoConsulta.getInt("numeroUnidad"));
                unidad.setTemaUnidad(resultadoConsulta.getString("temaUnidad"));
                unidad.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                unidad.setFechaFin(resultadoConsulta.getString("fechaFin"));
                unidad.setTareas(resultadoConsulta.getString("tareas"));
                unidad.setTecnicas(resultadoConsulta.getString("tecnicas"));
                unidades.add(unidad);
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidades;
    }

    public void save(Unidad unidad) {
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "insert into unidad(nrc, numeroUnidad, "
                    + "temaUnidad, fechaInicio, fechaFin, tareas, tecnicas) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, unidad.getNrc());
            sentencia.setInt(2, unidad.getNumeroUnidad());
            sentencia.setString(3, unidad.getTemaUnidad());
            sentencia.setString(4, unidad.getFechaInicio());
            sentencia.setString(5, unidad.getFechaFin());
            sentencia.setString(6, unidad.getTareas());
            sentencia.setString(7, unidad.getTecnicas());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(Unidad unidad){
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Delete from unidad where numeroUnidad = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setInt(1, unidad.getNumeroUnidad());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
