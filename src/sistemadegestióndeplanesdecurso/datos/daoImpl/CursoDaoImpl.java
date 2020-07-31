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
import sistemadegestióndeplanesdecurso.entidades.Curso;

/**
 *
 * @author angel
 */
public class CursoDaoImpl implements Dao{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public CursoDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }

    @Override
    public List<Object> getAll() {
        Curso curso = new Curso();
        List<Object> cursos = new ArrayList<Object>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from curso";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                curso = new Curso();
                curso.setNombreCurso(resultadoConsulta.getString("nombreCurso"));
                curso.setAñoCurso(resultadoConsulta.getString("añoCurso"));
                curso.setEstadoCurso(resultadoConsulta.getString("estadoCurso"));
                curso.setPeriodoCurso(resultadoConsulta.getString("periodoCurso"));
                curso.setTurnoCurso(resultadoConsulta.getString("turnoCurso"));
                curso.setNrc(resultadoConsulta.getString("nrcCurso"));
                curso.setDocenteAsignado(resultadoConsulta.getString("docenteAsignado"));
                cursos.add(curso);
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    @Override
    public Object getByID(String id) {
        Curso curso = new Curso();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from curso where nombreCurso = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, id);
            resultadoConsulta = sentencia.executeQuery();
            sentencia.close();
            while(resultadoConsulta.next()){
                curso = new Curso();
                curso.setNombreCurso(resultadoConsulta.getString("nombreCurso"));
                curso.setAñoCurso(resultadoConsulta.getString("añoCurso"));
                curso.setEstadoCurso(resultadoConsulta.getString("estadoCurso"));
                curso.setPeriodoCurso(resultadoConsulta.getString("periodoCurso"));
                curso.setTurnoCurso(resultadoConsulta.getString("turnoCurso"));
                curso.setNrc(resultadoConsulta.getString("nrc"));
                curso.setDocenteAsignado(resultadoConsulta.getString("docenteAsignado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curso;
    }

    @Override
    public void save(Object object) {
        Curso curso = (Curso) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "insert into curso(nombreCurso, añoCurso, "
                    + "estadoCurso, periodoCurso, turnoCurso, rfc, docenteAsignado) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, curso.getNombreCurso());
            sentencia.setString(2, curso.getAñoCurso());
            sentencia.setString(3, curso.getEstadoCurso());
            sentencia.setString(4, curso.getPeriodoCurso());
            sentencia.setString(5, curso.getTurnoCurso());
            sentencia.setString(6, curso.getNrc());
            sentencia.setString(5, curso.getDocenteAsignado());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        Curso curso = (Curso) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Update curso set estadoCurso = \"Inactivo\" where nombreCurso = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, curso.getNombreCurso());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
