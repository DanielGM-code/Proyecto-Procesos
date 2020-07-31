/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sistemadegestióndeplanesdecurso.gui.controladores.FXML_LoginSGPCController;
import sistemadegestióndeplanesdecurso.logica.GeneradorPDF;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 *
 * @author angel
 */
public class SistemaDeGestiónDePlanesDeCurso extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Ventana.iniciarVentana(new Button(), new FXML_LoginSGPCController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_LoginSGPC.fxml", Ventana.NO_CERRAR);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GeneradorPDF.generarPlanDeCurso("pdfPrueba", "Paradigmas de Programacion", "xdxdd", "asdasdasd", "asdasdad", "adsadasdasd", "asdsdada");
            String path = "src/pdfPrueba.pdf";
            
            launch(args);
        } catch (SQLException ex) {
            Logger.getLogger(SistemaDeGestiónDePlanesDeCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
