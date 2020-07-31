/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author angel
 */
public class Validacion {
    public static boolean esEmail(String correo) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();
    }
    
    public static boolean esNumeroPersonal(String matricula) {
        Pattern pattern = Pattern.compile("[0123456789]{8,8}");

        Matcher mather = pattern.matcher(matricula);

        return mather.find();
    }
    
    public static boolean esRFC(String rfc){
        Pattern pattern = Pattern.compile("^([A-Z,Ñ,&]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z|\\d]{3})$");
        Matcher matcher = pattern.matcher(rfc);
        return matcher.find();
    }
    
    public static boolean esCURP(String curp){
        Pattern pattern = Pattern.compile("[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
            "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
            "[HM]{1}" +
            "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
            "[B-DF-HJ-NP-TV-Z]{3}" +
            "[0-9A-Z]{1}[0-9]{1}$");
        Matcher matcher = pattern.matcher(curp);
        return matcher.find();
    }
}
