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
import sistemadegestióndeplanesdecurso.entidades.Docente;

/**
 *
 * @author angel
 */
public class DocenteDaoImpl implements Dao{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public DocenteDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }
    
    @Override
    public List<Object> getAll() {
        Docente docente = null;
        List<Object> docentes = new ArrayList<Object>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from docente";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                docente = new Docente();
                docente.setNumeroPersonalDocente(resultadoConsulta.getString("numeroPersonalDocente"));
                docente.setNombreDocente(resultadoConsulta.getString("nombreDocente"));
                docente.setApellidoPaternoDocente(resultadoConsulta.getString("apellidoPaternoDocente"));
                docente.setApellidoMaternoDocente(resultadoConsulta.getString("apellidoMaternoDocente"));
                docente.setTelefonoDocente(resultadoConsulta.getString("telefonoDocente"));
                docente.setEmailDocente(resultadoConsulta.getString("emailDocente"));
                docente.setEstadoDocente(resultadoConsulta.getString("estadoDocente"));
                docente.setRfcDocente(resultadoConsulta.getString("rfcDocente"));
                docente.setCurpDocente(resultadoConsulta.getString("curpDocente"));
                docentes.add(docente);
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return docentes;
    }

    @Override
    public Object getByID(String id) {
        Docente docente = null;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from docente where numeroPersonalDocente = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, id);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                docente = new Docente();
                docente.setNumeroPersonalDocente(resultadoConsulta.getString("numeroPersonalDocente"));
                docente.setNombreDocente(resultadoConsulta.getString("nombreDocente"));
                docente.setApellidoPaternoDocente(resultadoConsulta.getString("apellidoPaternoDocente"));
                docente.setApellidoMaternoDocente(resultadoConsulta.getString("apellidoMaternoDocente"));
                docente.setTelefonoDocente(resultadoConsulta.getString("telefonoDocente"));
                docente.setEmailDocente(resultadoConsulta.getString("emailDocente"));
                docente.setEstadoDocente(resultadoConsulta.getString("estadoDocente"));
                docente.setRfcDocente(resultadoConsulta.getString("rfcDocente"));
                docente.setCurpDocente(resultadoConsulta.getString("curpDocente"));
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return docente;
    }

    @Override
    public void save(Object object) {
        Docente directivo = (Docente) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "insert into docente (numeroPersonalDocente, nombreDocente, "
                    + "apellidoPaternoDocente, apellidoMaternoDocente, telefonoDocente, "
                    + "emailDocente, estadoDocente, rfcDocente, curpDocente) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, directivo.getNumeroPersonalDocente());
            sentencia.setString(2, directivo.getNombreDocente());
            sentencia.setString(3, directivo.getApellidoPaternoDocente());
            sentencia.setString(4, directivo.getApellidoMaternoDocente());
            sentencia.setString(5, directivo.getTelefonoDocente());
            sentencia.setString(6, directivo.getEmailDocente());
            sentencia.setString(7, directivo.getEstadoDocente());
            sentencia.setString(8, directivo.getRfcDocente());
            sentencia.setString(9, directivo.getCurpDocente());
            sentencia.execute();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        Docente directivo = (Docente) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "update docente set estadoDocente = \"Inactivo\" where numeroPersonalDocente = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, directivo.getNumeroPersonalDocente());
            sentencia.execute();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DirectivoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
