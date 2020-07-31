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
import sistemadegestióndeplanesdecurso.entidades.PlanDeCurso;

/**
 *
 * @author angel
 */
public class PlanDeCursoDaoImpl implements Dao{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public PlanDeCursoDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }

    @Override
    public List<Object> getAll() {
        PlanDeCurso planDeCurso = null;
        List<Object> planesDeCurso = new ArrayList<Object>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from planDeCurso";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                planDeCurso = new PlanDeCurso();
                planDeCurso.setNombreArchivoPlanDeCurso(resultadoConsulta.getString("nombreArchivoPlanDeCurso"));
                planDeCurso.setNombreCurso(resultadoConsulta.getString("nombreCurso"));
                planDeCurso.setPeriodoCurso(resultadoConsulta.getString("periodoCurso"));
                planDeCurso.setNombreDocente(resultadoConsulta.getString("nombreDocente"));
                planDeCurso.setNrc(resultadoConsulta.getString("nrc"));
                planDeCurso.setObjetivo(resultadoConsulta.getString("objetivo"));
                planDeCurso.setPorcentajePrimerParcial(resultadoConsulta.getInt("porcentajePrimerParcial"));
                planDeCurso.setPorcentajeSegundoParcial(resultadoConsulta.getInt("porcentajeSegundoParcial"));
                planDeCurso.setPorcentajeTareas(resultadoConsulta.getInt("porcentajeTareas"));
                planDeCurso.setPorcentajeProyectos(resultadoConsulta.getInt("porcentajeProyectos"));
                planDeCurso.setPorcentajeExposiciones(resultadoConsulta.getInt("porcentajeExposiciones"));
                planesDeCurso.add(planDeCurso);
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return planesDeCurso;
    }

    @Override
    public Object getByID(String id) {
        PlanDeCurso planDeCurso = null;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from planDeCurso where nrc = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, id);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                planDeCurso = new PlanDeCurso();
                planDeCurso.setNombreArchivoPlanDeCurso(resultadoConsulta.getString("nombreArchivoPlanDeCurso"));
                planDeCurso.setNombreCurso(resultadoConsulta.getString("nombreCurso"));
                planDeCurso.setPeriodoCurso(resultadoConsulta.getString("periodoCurso"));
                planDeCurso.setNombreDocente(resultadoConsulta.getString("nombreDocente"));
                planDeCurso.setNrc(resultadoConsulta.getString("nrc"));
                planDeCurso.setObjetivo(resultadoConsulta.getString("objetivo"));
                planDeCurso.setPorcentajePrimerParcial(resultadoConsulta.getInt("porcentajePrimerParcial"));
                planDeCurso.setPorcentajeSegundoParcial(resultadoConsulta.getInt("porcentajeSegundoParcial"));
                planDeCurso.setPorcentajeTareas(resultadoConsulta.getInt("porcentajeTareas"));
                planDeCurso.setPorcentajeProyectos(resultadoConsulta.getInt("porcentajeProyectos"));
                planDeCurso.setPorcentajeExposiciones(resultadoConsulta.getInt("porcentajeExposiciones"));
            }
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return planDeCurso;
    }

    @Override
    public void save(Object object) {
        PlanDeCurso planDeCurso = (PlanDeCurso) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Insert into planDeCurso(nombreCurso, periodoCurso, "
                    + "nombreArchivoPlanDeCurso, nrc, nombreDocente, objetivo, porcentajePrimerParcial, "
                    + "porcentajeSegundoParcial, porcentajeTareas, porcentajeProyectos, "
                    + "porcentajeExposiciones) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, planDeCurso.getNombreCurso());
            sentencia.setString(2, planDeCurso.getPeriodoCurso());
            sentencia.setString(3, planDeCurso.getNombreArchivoPlanDeCurso());
            sentencia.setString(4, planDeCurso.getNrc());
            sentencia.setString(5, planDeCurso.getNombreDocente());
            sentencia.setString(6, planDeCurso.getObjetivo());
            sentencia.setInt(7, planDeCurso.getPorcentajePrimerParcial());
            sentencia.setInt(8, planDeCurso.getPorcentajeSegundoParcial());
            sentencia.setInt(9, planDeCurso.getPorcentajeTareas());
            sentencia.setInt(10, planDeCurso.getPorcentajeProyectos());
            sentencia.setInt(11, planDeCurso.getPorcentajeExposiciones());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        PlanDeCurso planDeCurso = (PlanDeCurso) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Delete from planDeCurso where nrc = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, planDeCurso.getNrc());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
