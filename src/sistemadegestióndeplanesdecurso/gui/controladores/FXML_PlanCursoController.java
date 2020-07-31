/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sistemadegestióndeplanesdecurso.datos.daoImpl.PlanDeCursoDaoImpl;
import sistemadegestióndeplanesdecurso.datos.daoImpl.UnidadDaoImpl;
import sistemadegestióndeplanesdecurso.entidades.Curso;
import sistemadegestióndeplanesdecurso.entidades.PlanDeCurso;
import sistemadegestióndeplanesdecurso.entidades.Unidad;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 * FXML Controller class
 *
 * @author dagam
 */
public class FXML_PlanCursoController implements Initializable {

    @FXML
    private Button buttonRegresar;
    @FXML
    private Label labelAcademico;
    @FXML
    private Label labelNRC;
    @FXML
    private Label labelCurso;
    @FXML
    private Label labelPeriodo;
    @FXML
    private Label labelObjetivo;
    @FXML
    private Label labelPorcentajePrimerExamenParcial;
    @FXML
    private Label labelPorcentajeSegundoExamenParcial;
    @FXML
    private Label labelPorcentajeTareas;
    @FXML
    private Label labelPorcentajeProyectos;
    @FXML
    private Label labelPorcentajeExposiciones;
    @FXML
    private TableView<Unidad> tableUnidades;
    @FXML
    private TableColumn columnaUnidades;
    @FXML
    private Button buttonDescargar;
    ObservableList<Unidad> listaUnidades;
    private Curso curso;
    private PlanDeCurso planDeCurso;
    private String numeroPersonal;

    FXML_PlanCursoController(Curso curso, String numeroPersonal) {
        this.curso = curso;
        this.numeroPersonal = numeroPersonal;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            llenarCampos();
            llenarTablaUnidades();
        } catch (SQLException ex) {
            Ventana.iniciarVentana(buttonRegresar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
        }
    }    

    @FXML
    private void regresar(ActionEvent event) {
        Ventana.iniciarVentana(buttonRegresar, new FXML_ListaCursosConsultaController(numeroPersonal), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ListaCursosConsulta.fxml", Ventana.CERRAR);
    }

    @FXML
    private void seleccionarUnidad(MouseEvent event) {
        Unidad unidadSeleccionada = this.tableUnidades.getSelectionModel().getSelectedItem();
        Ventana.iniciarVentana(buttonRegresar, new FXML_InformacionUnidadController(unidadSeleccionada), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_InformacionUnidad.fxml", Ventana.NO_CERRAR);
    }

    @FXML
    private void descargarPlanCurso(ActionEvent event) {
    }
    
    private void llenarTablaUnidades() throws SQLException{
        this.columnaUnidades.setCellValueFactory(new PropertyValueFactory<Unidad,String>("numeroUnidad"));

        UnidadDaoImpl unidadDaoImpl = new UnidadDaoImpl();
        List<Unidad> unidades = unidadDaoImpl.getByID(this.labelNRC.getText());
        listaUnidades = FXCollections.observableArrayList(unidades);
        this.tableUnidades.setItems(listaUnidades);
    }

    private void llenarCampos() throws SQLException {
        PlanDeCursoDaoImpl planDeCursoDaoImpl = new PlanDeCursoDaoImpl();
        planDeCurso = (PlanDeCurso) planDeCursoDaoImpl.getByID(curso.getNrc());
        this.labelNRC.setText(planDeCurso.getNrc());
        this.labelCurso.setText(planDeCurso.getNombreCurso());
        this.labelPeriodo.setText(planDeCurso.getPeriodoCurso());
        this.labelAcademico.setText(planDeCurso.getNombreDocente());
        this.labelObjetivo.setText(planDeCurso.getObjetivo());
        this.labelPorcentajeExposiciones.setText(String.valueOf(planDeCurso.getPorcentajeExposiciones()));
        this.labelPorcentajePrimerExamenParcial.setText(String.valueOf(planDeCurso.getPorcentajePrimerParcial()));
        this.labelPorcentajeProyectos.setText(String.valueOf(planDeCurso.getPorcentajeProyectos()));
        this.labelPorcentajeSegundoExamenParcial.setText(String.valueOf(planDeCurso.getPorcentajeSegundoParcial()));
        this.labelPorcentajeTareas.setText(String.valueOf(planDeCurso.getPorcentajeTareas()));
    }
}
