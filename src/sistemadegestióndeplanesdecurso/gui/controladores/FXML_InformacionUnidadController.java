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
import javafx.scene.control.Label;
import sistemadegestióndeplanesdecurso.entidades.Unidad;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 * FXML Controller class
 *
 * @author dagam
 */
public class FXML_InformacionUnidadController implements Initializable {

    @FXML
    private Button buttonRegresar;
    @FXML
    private Label labelFechas;
    @FXML
    private Label labelTemas;
    @FXML
    private Label labelTareas;
    @FXML
    private Label labelTecnicas;
    Unidad unidad;

    FXML_InformacionUnidadController(Unidad unidadSeleccionada) {
        unidad = unidadSeleccionada;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCampos();
    }    

    @FXML
    private void regresar(ActionEvent event) {
        Ventana.cerrar(buttonRegresar);
    }
    
    private void llenarCampos(){
        this.labelFechas.setText(unidad.getFechaInicio() + " - " + unidad.getFechaFin());
        this.labelTareas.setText(unidad.getTareas());
        this.labelTecnicas.setText(unidad.getTecnicas());
        this.labelTemas.setText(unidad.getTemaUnidad());
    }
}
