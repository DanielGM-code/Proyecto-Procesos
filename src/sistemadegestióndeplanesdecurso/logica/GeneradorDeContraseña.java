/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.logica;

/**
 *
 * @author angel
 */
public class GeneradorDeContraseña {
 
    public static final String NUMEROS = "0123456789";
    public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static final String ESPECIALES = "ñÑ";

    public static String obtenerContraseña() {
            return GeneradorDeContraseña.obtenerContraseña(8);
    }

    public static String obtenerContraseña(int longitud) {
            return obtenerContraseña(NUMEROS + MAYUSCULAS + MINUSCULAS, longitud);
    }

    public static String obtenerContraseña(String clave, int longitud) {
            String contraseña = "";
            for (int i = 0; i < longitud; i++) {
                contraseña+=(clave.charAt((int)(Math.random() * clave.length())));
            }
            return contraseña;
    }
}
