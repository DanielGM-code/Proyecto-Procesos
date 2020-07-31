/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author angel
 */
public class ConexionFTP {
    private static InputStream archivoPropiedades = null;
    private static FileInputStream fis = null;
    private static String url;
    private static String usuario;
    private static String contraseña;
    
    public static void subirArchivo(String ruta, String nombreArchivo){
        try {
            Properties configuracion = new Properties();
            archivoPropiedades = new FileInputStream("src/sistemadegestióndeplanesdecurso/datos/conexion/ConexionFTP.txt");
            configuracion.load(archivoPropiedades);
            url = configuracion.getProperty("url");
            usuario = configuracion.getProperty("usuario");
            contraseña = configuracion.getProperty("contrasena");
            fis = new FileInputStream(ruta);

            FTPClient cliente = new FTPClient();
            cliente.connect(url,21);
            cliente.login(usuario,contraseña);
            cliente.enterLocalPassiveMode();
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            cliente.storeFile(nombreArchivo + ".pdf",fis);
            cliente.logout();
            cliente.disconnect();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivoPropiedades.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean descargarArchivo(String ruta, String nombreArchivo){
        boolean descargaExitosa = false;
        FTPClient cliente = new FTPClient();
        FileOutputStream fos = null;
        Properties configuracion = new Properties();
        try {
            archivoPropiedades = new FileInputStream("src/sistemadegestióndeplanesdecurso/datos/conexion/ConexionFTP.txt");
            configuracion.load(archivoPropiedades);
            url = configuracion.getProperty("url");
            usuario = configuracion.getProperty("usuario");
            contraseña = configuracion.getProperty("contrasena");
            cliente.connect(url);
            cliente.login(usuario, contraseña);
            fos = new FileOutputStream(ruta + File.separatorChar + nombreArchivo + ".pdf");
            descargaExitosa = cliente.retrieveFile( File.separatorChar + nombreArchivo + ".pdf", fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }finally{
            try {
                fos.close();
                archivoPropiedades.close();
            } catch (IOException ex) {
                Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return descargaExitosa;
    }
}
