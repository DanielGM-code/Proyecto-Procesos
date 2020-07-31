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
import sistemadegestióndeplanesdecurso.entidades.AvancePlanDeCurso;

/**
 *
 * @author angel
 */
public class AvancePlanDeCursoDaoImpl implements Dao{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public AvancePlanDeCursoDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }

    @Override
    public List<Object> getAll() {
        AvancePlanDeCurso avancePlanDeCurso = null;
        List<Object> avancesPlanDeCurso = new ArrayList<Object>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from avancePlanDeCurso";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            sentencia.close();
            while(resultadoConsulta.next()){
                avancePlanDeCurso = new AvancePlanDeCurso();
                avancePlanDeCurso.setNombreArchivoAcancePlanDeCurso(resultadoConsulta.getString("nombreArchivoAvancePlanDeCurso"));
                avancePlanDeCurso.setNombreCurso(resultadoConsulta.getString("nombreCurso"));
                avancePlanDeCurso.setPeriodoCurso(resultadoConsulta.getString("periodoCurso"));
                avancesPlanDeCurso.add(avancePlanDeCurso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avancesPlanDeCurso;
    }

    @Override
    public Object getByID(String id) {
        AvancePlanDeCurso avancePlanDeCurso = null;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from avancePlanDeCurso where nombreCurso = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, id);
            resultadoConsulta = sentencia.executeQuery();
            sentencia.close();
            while(resultadoConsulta.next()){
                avancePlanDeCurso = new AvancePlanDeCurso();
                avancePlanDeCurso.setNombreArchivoAcancePlanDeCurso(resultadoConsulta.getString("nombreArchivoAvancePlanDeCurso"));
                avancePlanDeCurso.setNombreCurso(resultadoConsulta.getString("nombreCurso"));
                avancePlanDeCurso.setPeriodoCurso(resultadoConsulta.getString("periodoCurso"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avancePlanDeCurso;
    }

    @Override
    public void save(Object object) {
        AvancePlanDeCurso avancePlanDeCurso = (AvancePlanDeCurso) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Insert into avancePlanDeCurso(nombreCurso, periodoCurso, "
                    + "nombreArchivoAvancePlanDeCurso) values (?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, avancePlanDeCurso.getNombreCurso());
            sentencia.setString(2, avancePlanDeCurso.getPeriodoCurso());
            sentencia.setString(3, avancePlanDeCurso.getNombreArchivoAcancePlanDeCurso());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        AvancePlanDeCurso avancePlanDeCurso = (AvancePlanDeCurso) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Delete from avancePlanDeCurso where nombreCurso = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, avancePlanDeCurso.getNombreCurso());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvancePlanDeCursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
