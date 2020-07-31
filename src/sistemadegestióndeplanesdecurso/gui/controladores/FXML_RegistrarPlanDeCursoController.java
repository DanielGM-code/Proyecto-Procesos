/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import sistemadegestióndeplanesdecurso.datos.daoImpl.DocenteDaoImpl;
import sistemadegestióndeplanesdecurso.datos.daoImpl.PlanDeCursoDaoImpl;
import sistemadegestióndeplanesdecurso.datos.daoImpl.UnidadDaoImpl;
import sistemadegestióndeplanesdecurso.entidades.Curso;
import sistemadegestióndeplanesdecurso.entidades.Docente;
import sistemadegestióndeplanesdecurso.entidades.PlanDeCurso;
import sistemadegestióndeplanesdecurso.entidades.Unidad;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 *
 * @author dagam
 */
public class FXML_RegistrarPlanDeCursoController implements Initializable{

    @FXML
    private Button buttonRegistrar;
    @FXML
    private Button buttonRegresar;
    @FXML
    private Label labelAcademico;
    @FXML
    private TextArea textAreaObjetivo;
    @FXML
    private Label labelNRC;
    @FXML
    private Label labelCurso;
    @FXML
    private Label labelPeriodo;
    @FXML
    private Label numeroUnidad;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextArea textAreaTema;
    @FXML
    private TextArea textAreaTarea;
    @FXML
    private Button buttonNuevaUnidad;
    @FXML
    private TextField textFielPorcentajePrimerParcial;
    @FXML
    private TextField textFielPorcentajeSegundoParcial;
    @FXML
    private TextField textFielPorcentajeTareas;
    @FXML
    private TextField textFielPorcentajeProyectos;
    @FXML
    private TextField textFielPorcentajeExposiciones;
    @FXML
    private TextArea textAreaTécnica;
    private Curso curso;
    private int contadorUnidades = 1;
    private List<Unidad> unidadesRegistradas;
    private boolean registroEjecutado;
    private String numeroPersonal;
    
    FXML_RegistrarPlanDeCursoController(Curso curso, String numeroPersonal) {
        this.curso = curso;
        this.numeroPersonal = numeroPersonal;
        unidadesRegistradas = new ArrayList<Unidad>();
        registroEjecutado = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarDatosCurso();
        this.numeroUnidad.setDisable(true);
        this.numeroUnidad.setText("1");
        
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            } else {
                return null;
            }
        };
        
        this.textFielPorcentajeExposiciones.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.textFielPorcentajeSegundoParcial.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.textFielPorcentajeTareas.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.textFielPorcentajeProyectos.setTextFormatter(new TextFormatter<String>(integerFilter));
        this.textFielPorcentajePrimerParcial.setTextFormatter(new TextFormatter<String>(integerFilter));
    }

    @FXML
    private void registrar(ActionEvent event) {
        int sumaPorcentajes = 0;
        if(this.textAreaObjetivo.getText().isEmpty() ||
                this.textAreaTema.getText().isEmpty() ||
                this.textAreaTarea.getText().isEmpty() ||
                this.textAreaTécnica.getText().isEmpty() ||
                this.dateInicio.getValue() == null ||
                this.dateFin.getValue() == null ||
                this.textFielPorcentajeExposiciones.getText().isEmpty() ||
                this.textFielPorcentajePrimerParcial.getText().isEmpty() ||
                this.textFielPorcentajeProyectos.getText().isEmpty() ||
                this.textFielPorcentajeSegundoParcial.getText().isEmpty() ||
                this.textFielPorcentajeTareas.getText().isEmpty()){
            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Aegúrese de llenar todos los campos.");
            alertaConfirmacion.showAndWait();
        }else{
            sumaPorcentajes += Integer.parseInt(this.textFielPorcentajeExposiciones.getText());
            sumaPorcentajes += Integer.parseInt(this.textFielPorcentajePrimerParcial.getText());
            sumaPorcentajes += Integer.parseInt(this.textFielPorcentajeProyectos.getText());
            sumaPorcentajes += Integer.parseInt(this.textFielPorcentajeSegundoParcial.getText());
            sumaPorcentajes += Integer.parseInt(this.textFielPorcentajeTareas.getText());
            if(sumaPorcentajes != 100){
                Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
                alertaConfirmacion.setHeaderText(null);
                alertaConfirmacion.setTitle("Alerta");
                alertaConfirmacion.setContentText("La suma de los porcentajes de evaluacion debe ser igual a 100.");
                alertaConfirmacion.showAndWait();
            }else{
                try {
                    Unidad unidad = new Unidad();
                    UnidadDaoImpl unidadDaoImpl = new UnidadDaoImpl();
                    unidad.setNrc(this.labelNRC.getText());
                    unidad.setNumeroUnidad(Integer.parseInt(this.numeroUnidad.getText()));
                    unidad.setTemaUnidad(this.textAreaTema.getText());
                    unidad.setTareas(this.textAreaTarea.getText());
                    unidad.setFechaInicio(this.dateInicio.getValue().toString());
                    unidad.setFechaFin(this.dateFin.getValue().toString());
                    unidad.setTecnicas(this.textAreaTécnica.getText());
                    unidadDaoImpl.save(unidad);

                    PlanDeCurso planDeCurso = new PlanDeCurso();
                    PlanDeCursoDaoImpl planDeCursoDaoImpl = new PlanDeCursoDaoImpl();
                    planDeCurso.setNrc(this.labelNRC.getText());
                    planDeCurso.setNombreCurso(this.labelCurso.getText());
                    planDeCurso.setNombreDocente(this.labelAcademico.getText());
                    planDeCurso.setObjetivo(this.textAreaObjetivo.getText());
                    planDeCurso.setPeriodoCurso(this.labelPeriodo.getText());
                    planDeCurso.setPorcentajeExposiciones(Integer.parseInt(this.textFielPorcentajeExposiciones.getText()));
                    planDeCurso.setPorcentajePrimerParcial(Integer.parseInt(this.textFielPorcentajePrimerParcial.getText()));
                    planDeCurso.setPorcentajeSegundoParcial(Integer.parseInt(this.textFielPorcentajeSegundoParcial.getText()));
                    planDeCurso.setPorcentajeTareas(Integer.parseInt(this.textFielPorcentajeTareas.getText()));
                    planDeCurso.setPorcentajeProyectos(Integer.parseInt(this.textFielPorcentajeProyectos.getText()));
                    planDeCursoDaoImpl.save(planDeCurso);
                    Alert alertaConfirmacion = new Alert(Alert.AlertType.INFORMATION);
                    alertaConfirmacion.setHeaderText(null);
                    alertaConfirmacion.setTitle("Información");
                    alertaConfirmacion.setContentText("Se ha registrado el Plan de Curso de manera exitosa.");
                    registroEjecutado = true;
                    alertaConfirmacion.showAndWait();
                    regresar(new ActionEvent());
                } catch (SQLException ex) {
                    Ventana.iniciarVentana(buttonRegresar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
                }
            }
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        if(!registroEjecutado){
            UnidadDaoImpl unidadDaoImpl = null;
            for(Unidad unidad : unidadesRegistradas){
                try {
                    unidadDaoImpl = new UnidadDaoImpl();
                    unidadDaoImpl.delete(unidad);
                } catch (SQLException ex) {
                    Ventana.iniciarVentana(buttonRegresar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
                }
            }
        }
        
        Ventana.iniciarVentana(buttonRegresar, new FXML_MenuDocenteController(numeroPersonal), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_MenuDocente.fxml", Ventana.CERRAR);
    }

    @FXML
    private void agregarNuevaUnidad(ActionEvent event) {
    	if(this.textAreaTema.getText().isEmpty() ||
    		this.textAreaTarea.getText().isEmpty() ||
    		this.textAreaTécnica.getText().isEmpty() ||
    		this.dateInicio.getValue() == null ||
            this.dateFin.getValue() == null){

    		Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Favor de llenar todos los datos de la unidad");
            alertaConfirmacion.showAndWait();
    	}else{
	        try {
	            Unidad unidad = new Unidad();
	            UnidadDaoImpl unidadDaoImpl = new UnidadDaoImpl();
	            unidad.setNrc(this.labelNRC.getText());
	            unidad.setNumeroUnidad(Integer.parseInt(this.numeroUnidad.getText()));
	            unidad.setTemaUnidad(this.textAreaTema.getText());
	            unidad.setTareas(this.textAreaTarea.getText());
	            unidad.setFechaInicio(this.dateInicio.getValue().toString());
	            unidad.setFechaFin(this.dateFin.getValue().toString());
	            unidad.setTecnicas(this.textAreaTécnica.getText());
	            unidadDaoImpl.save(unidad);
	            this.contadorUnidades++;
	            limpiarCampos();
	            unidadesRegistradas.add(unidad);
	        } catch (SQLException ex) {
	            Ventana.iniciarVentana(buttonRegresar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
	        }
    	}
    }
    
    private void llenarDatosCurso(){
        try {
            this.labelNRC.setText(curso.getNrc());
            this.labelCurso.setText(curso.getNombreCurso());
            this.labelPeriodo.setText(curso.getPeriodoCurso());
            Docente docente = new Docente();
            DocenteDaoImpl docenteDaoImpl = new DocenteDaoImpl();
            docente = (Docente) docenteDaoImpl.getByID(curso.getDocenteAsignado());
            String nombreDocente = docente.getNombreDocente() + " " + docente.getApellidoPaternoDocente() +
                    " " + docente.getApellidoMaternoDocente();
            this.labelAcademico.setText(nombreDocente);
        } catch (SQLException ex) {
            Ventana.iniciarVentana(buttonRegresar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
        }
    }
    
    private void limpiarCampos(){
        this.textAreaTema.setText("");
        this.textAreaTarea.setText("");
        this.textAreaTécnica.setText("");
        this.dateInicio.setValue(null);
        this.dateFin.setValue(null);
        this.numeroUnidad.setText(String.valueOf(this.contadorUnidades));
    }
}
