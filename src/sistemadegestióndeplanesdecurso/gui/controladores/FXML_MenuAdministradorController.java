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
public class FXML_MenuAdministradorController implements Initializable {

    @FXML
    private Button buttonCerrarSesion;
    @FXML
    private Button buttonRegistrarDocente;
    @FXML
    private Button buttonRegistrarDirectivo;
    @FXML
    private Button buttonActualizarCatálogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_LoginSGPCController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_LoginSGPC.fxml", Ventana.CERRAR);
    }

    @FXML
    private void actualizarCatalogo(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ErrorVentanaController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorVentana.fxml", Ventana.NO_CERRAR);
    }
    
    @FXML
    private void registrarDocente(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_RegistrarDocenteController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_RegistrarDocente.fxml", Ventana.CERRAR);
    }
    
    @FXML
    private void registrarDirectivo(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ErrorVentanaController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorVentana.fxml", Ventana.NO_CERRAR);
    }
    
}
