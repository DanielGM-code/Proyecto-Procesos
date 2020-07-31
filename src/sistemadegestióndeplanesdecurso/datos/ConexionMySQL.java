/*
 * Copyright (C) {2020}
 * Todos los derechos reservados
 * Desarrollado para {Universidad Veracruzana}
 */
package sistemadegestióndeplanesdecurso.datos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angel
 */
public class ConexionMySQL {
    
   public static Properties configuracion = new Properties();
   public static InputStream configInput = null;
   
    private static Connection conexion;
    private static  String driver = null;
    private static  String usuario = null;
    private static  String contraseña = null;
    private static  String url = null;
    
     
    
    public ConexionMySQL() throws SQLException{
        conexion = null;
        try{
            configInput = new FileInputStream("src/sistemadegestióndeplanesdecurso/datos/conexion/ConexionMySQL.txt");
            configuracion.load(configInput);
            driver = configuracion.getProperty("driver");
            usuario = configuracion.getProperty("usuario");
            contraseña = configuracion.getProperty("contrasena");
            url = configuracion.getProperty("url");
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException excepcion) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, excepcion);
        } catch (FileNotFoundException excepcion) {
           Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, excepcion);
       } catch (IOException excepcion) {
           Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, excepcion);
       }
    }
    
    public Connection obtenerConexion(){
        return conexion;
    }
    
    public void desconectar(){
        try {
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, excepcion);
        }
    }
}
