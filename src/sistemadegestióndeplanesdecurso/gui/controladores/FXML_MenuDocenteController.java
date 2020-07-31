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
public class FXML_MenuDocenteController implements Initializable {

    @FXML
    private Button buttonCerrarSesion;
    @FXML
    private Button buttonConsultarPlanCurso;
    @FXML
    private Button buttonModificarPlanCurso;
    @FXML
    private Button buttonRegistrarPlanCurso;
    @FXML
    private Button buttonRegistrarAvanceCurso;
    @FXML
    private Button buttonConsultarAvanceCurso;
    @FXML
    private Button buttonSolicitarModificacion;
    private String numeroPersonal;

    FXML_MenuDocenteController(String numeroPersonal) {
    this.numeroPersonal = numeroPersonal;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_LoginSGPCController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_LoginSGPC.fxml", Ventana.CERRAR);
    }

    @FXML
    private void consultarPlanCurso(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ListaCursosConsultaController(numeroPersonal), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ListaCursosConsulta.fxml", Ventana.CERRAR);
    }

    @FXML
    private void registrarPlanCurso(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ListaCursosController(numeroPersonal), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ListaCursos.fxml", Ventana.CERRAR);
    }
    
    @FXML
    private void modeificarPlanCurso(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ErrorVentanaController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorVentana.fxml", Ventana.NO_CERRAR);
    }

    @FXML
    private void registrarAvanceCurso(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ErrorVentanaController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorVentana.fxml", Ventana.NO_CERRAR);
    }

    @FXML
    private void consultarAvanceCurso(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ErrorVentanaController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorVentana.fxml", Ventana.NO_CERRAR);
    }

    @FXML
    private void solicitarModificacion(ActionEvent event) {
        Ventana.iniciarVentana(buttonCerrarSesion, new FXML_ErrorVentanaController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorVentana.fxml", Ventana.NO_CERRAR);
    }
}
