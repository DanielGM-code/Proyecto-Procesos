/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.gui.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 * FXML Controller class
 *
 * @author dagam
 */
public class FXML_ErrorDeConexionController implements Initializable {

    @FXML
    private Button buttonAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        Ventana.iniciarVentana(buttonAceptar, new FXML_LoginSGPCController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_LoginSGPC.fxml", Ventana.CERRAR);
    }
    
}
