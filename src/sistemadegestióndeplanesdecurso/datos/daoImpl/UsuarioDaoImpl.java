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
import sistemadegestióndeplanesdecurso.entidades.Usuario;

/**
 *
 * @author angel
 */
public class UsuarioDaoImpl implements Dao{
    private  ConexionMySQL conexion;
    private ResultSet resultadoConsulta;
    
    public UsuarioDaoImpl() throws SQLException{
        conexion = new ConexionMySQL();
    }
    
    @Override
    public List<Object> getAll() {
        Usuario usuario = null;
        List<Object> usuarios = new ArrayList<Object>();
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from usuario";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                usuario = new Usuario();
                usuario.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                usuario.setContraseñaUsuario(resultadoConsulta.getString("contraseña"));
                usuario.setTipoUsuario(resultadoConsulta.getString("tipoUsuario"));
                usuario.setEstadoUsuario(resultadoConsulta.getString("estadoUsuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    @Override
    public Object getByID(String id) {
        Usuario usuario = null;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "Select * from usuario where nombreUsuario = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, id);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                usuario = new Usuario();
                usuario.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                usuario.setContraseñaUsuario(resultadoConsulta.getString("contraseña"));
                usuario.setTipoUsuario(resultadoConsulta.getString("tipoUsuario"));
                usuario.setEstadoUsuario(resultadoConsulta.getString("estadoUsuario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    @Override
    public void save(Object object) {
        Usuario usuario = (Usuario) object;
        System.out.println(usuario.getNombreUsuario());
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "call insertarUsuario(?, ?, ?, ?)";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContraseñaUsuario());
            sentencia.setString(3, usuario.getTipoUsuario());
            sentencia.setString(4, usuario.getEstadoUsuario());
            sentencia.execute();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        Usuario usuario = (Usuario) object;
        try(Connection conectar = conexion.obtenerConexion()){
            String consulta = "update usuario set estadoUsuario = \"Inactivo\" where nombreUsuario = ?";
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            sentencia.setString(1, usuario.getNombreUsuario());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
