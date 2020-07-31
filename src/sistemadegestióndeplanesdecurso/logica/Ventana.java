/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.logica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author angel
 */
public class Ventana {
    public static final boolean CERRAR = true;
    public static final boolean NO_CERRAR = false;
    
    public static void iniciarVentana(Button boton, Object controlador, String nombreVentana, boolean cerrarVentana){
        try{
            FXMLLoader loader = new FXMLLoader(Ventana.class.getResource(nombreVentana));
            loader.setController(controlador);
            Stage stage = new Stage();
            Parent parent = (Parent) loader.load();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            if(cerrarVentana){
                cerrar(boton);
            }
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cerrar(Button boton){
        Stage stagePrincipal = (Stage) boton.getScene().getWindow();
        stagePrincipal.close();
    }
}
