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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sistemadegestióndeplanesdecurso.datos.daoImpl.CursoDaoImpl;
import sistemadegestióndeplanesdecurso.datos.daoImpl.PlanDeCursoDaoImpl;
import sistemadegestióndeplanesdecurso.entidades.Curso;
import sistemadegestióndeplanesdecurso.entidades.PlanDeCurso;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 * FXML Controller class
 *
 * @author angel
 */
public class FXML_ListaCursosController implements Initializable {

    @FXML
    private Button buttonRegresar;
    @FXML
    private TableView<Curso> tablaCursos;
    @FXML
    private TableColumn columnaNombreCurso;
    @FXML
    private TableColumn columnaPeriodoCurso;
    @FXML
    private TableColumn columnaAñoCurso;
    @FXML
    private TableColumn columnaNRCCurso;
    private ObservableList<Curso> cursos;
    private String numeroPersonal;

    FXML_ListaCursosController(String numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            llenarTablaCursos();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_ListaCursosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void regresar(ActionEvent event) {
        Ventana.iniciarVentana(buttonRegresar, new FXML_MenuDocenteController(numeroPersonal), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_MenuDocente.fxml", Ventana.CERRAR);
    }

    @FXML
    private void seleccionarCurso(MouseEvent event) {
        Curso curso = this.tablaCursos.getSelectionModel().getSelectedItem();
        if(curso != null){
            Ventana.iniciarVentana(buttonRegresar, new FXML_RegistrarPlanDeCursoController(curso, numeroPersonal), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_RegistrarPlanCurso.fxml", Ventana.CERRAR);
        }
    }
    
    private void llenarTablaCursos() throws SQLException{
        this.columnaNombreCurso.setCellValueFactory(new PropertyValueFactory<PlanDeCurso,String>("nombreCurso"));
        this.columnaAñoCurso.setCellValueFactory(new PropertyValueFactory<PlanDeCurso,String>("añoCurso"));
        this.columnaPeriodoCurso.setCellValueFactory(new PropertyValueFactory<PlanDeCurso,String>("periodoCurso"));
        this.columnaNRCCurso.setCellValueFactory(new PropertyValueFactory<PlanDeCurso, String>("nrc"));
        
        CursoDaoImpl cursoDaoImpl = new CursoDaoImpl();
        List<Object> todosLosCursos = cursoDaoImpl.getAll();
        List<Curso> cursosActivos = new ArrayList<Curso>();
        for(Object cursoDao : todosLosCursos){
            Curso curso = (Curso) cursoDao;
            if(curso.getEstadoCurso().equalsIgnoreCase("Activo")){
                cursosActivos.add(curso);
            }
        }
        
        PlanDeCursoDaoImpl planDeCursoDaoImpl = new PlanDeCursoDaoImpl();
        List<Object> planesDeCurso = planDeCursoDaoImpl.getAll();
        List<Curso> cursosSinPlan = new ArrayList<Curso>();
        boolean tienePlan = false;
        for(Curso cursoActivo : cursosActivos){
            tienePlan = false;
            for(Object planDeCursoDao : planesDeCurso){
                PlanDeCurso planDeCurso = (PlanDeCurso) planDeCursoDao;
                if(cursoActivo.getNombreCurso().equalsIgnoreCase(planDeCurso.getNombreCurso())){
                    tienePlan = true;
                    break;
                }
            }
            if(!tienePlan){
                cursosSinPlan.add(cursoActivo);
            }
        }
        List<Curso> cursosDelDocente = new ArrayList<Curso>();
        if(cursosSinPlan.size() != 0){
            for(Curso cursoSinPlan : cursosSinPlan){
               if(cursosSinPlan != null && cursoSinPlan.getDocenteAsignado().equalsIgnoreCase(numeroPersonal)){
                   cursosDelDocente.add(cursoSinPlan);
               }
           }
        }

        
        cursos = FXCollections.observableArrayList(cursosDelDocente);
        this.tablaCursos.setItems(cursos);
    }
    
}
