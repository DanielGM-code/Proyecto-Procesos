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
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import sistemadegestióndeplanesdecurso.datos.daoImpl.DocenteDaoImpl;
import sistemadegestióndeplanesdecurso.datos.daoImpl.UsuarioDaoImpl;
import sistemadegestióndeplanesdecurso.entidades.Docente;
import sistemadegestióndeplanesdecurso.entidades.Usuario;
import sistemadegestióndeplanesdecurso.logica.Email;
import sistemadegestióndeplanesdecurso.logica.GeneradorDeContraseña;
import sistemadegestióndeplanesdecurso.logica.Hash;
import sistemadegestióndeplanesdecurso.logica.Validacion;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 * FXML Controller class
 *
 * @author dagam
 */
public class FXML_RegistrarDocenteController implements Initializable {

    @FXML
    private Button buttonRegresar;
    @FXML
    private TextField textFieldNumeroPersonal;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidoPaterno;
    @FXML
    private TextField textFieldApellidoMaterno;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldRFC;
    @FXML
    private TextField textFieldCURP;
    @FXML
    private Button buttonAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UnaryOperator<TextFormatter.Change> characterFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z ]*")) { 
                return change;
            } else {
                return null;
            }
        };
        
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            } else {
                return null;
            }
        };
        
        UnaryOperator<TextFormatter.Change> textLimitFilter = change -> {
            if (change.isContentChange()) {
                int newLength = change.getControlNewText().length();
                if (newLength > 8) {
                    String trimmedText = change.getControlNewText().substring(0, 8);
                    change.setText(trimmedText);
                    int oldLength = change.getControlText().length();
                    change.setRange(0, oldLength);
                }
            }
            return change;
        };
        
        textFieldNombre.setTextFormatter(new TextFormatter<String>(characterFilter));
        textFieldApellidoPaterno.setTextFormatter(new TextFormatter<String>(characterFilter));
        textFieldApellidoMaterno.setTextFormatter(new TextFormatter<String>(characterFilter));
        textFieldTelefono.setTextFormatter(new TextFormatter<String>(integerFilter));
        textFieldNumeroPersonal.setTextFormatter(new TextFormatter<String>(integerFilter));
        textFieldNumeroPersonal.setTextFormatter(new TextFormatter<String>(textLimitFilter));
    }    

    @FXML
    private void regresar(ActionEvent event) {
        Ventana.iniciarVentana(buttonAceptar, new FXML_MenuAdministradorController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_MenuAdministrador.fxml", Ventana.CERRAR);
    }

    @FXML
    private void registrarDatosDocente(ActionEvent event) {
        if(this.textFieldNumeroPersonal.getText().isEmpty() || 
                this.textFieldNombre.getText().isEmpty() ||
                this.textFieldApellidoPaterno.getText().isEmpty() ||
                this.textFieldApellidoMaterno.getText().isEmpty() ||
                this.textFieldTelefono.getText().isEmpty() ||
                this.textFieldEmail.getText().isEmpty() ||
                this.textFieldCURP.getText().isEmpty() ||
                this.textFieldRFC.getText().isEmpty()){
            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Aegúrese de llenar todos los campos.");
            alertaConfirmacion.showAndWait();
        }else if(!Validacion.esNumeroPersonal(this.textFieldNumeroPersonal.getText())){
            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Ingrese un número de personal valido.");
            alertaConfirmacion.showAndWait();
        }else if(!Validacion.esEmail(this.textFieldEmail.getText())){
            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Ingrese un email Valido.");
            alertaConfirmacion.showAndWait();
        }else if(!Validacion.esCURP(this.textFieldCURP.getText())){    
            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Ingrese una CURP Valida.");
            alertaConfirmacion.showAndWait();
        }else if(!Validacion.esRFC(this.textFieldRFC.getText())){
            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Ingrese un RFC Valido.");
            alertaConfirmacion.showAndWait();
        }else{
            try {
                DocenteDaoImpl docenteDaoImpl = new DocenteDaoImpl();
                List<Object> docentes = docenteDaoImpl.getAll();
                boolean existeNumeroPersonal = false;
                boolean existeRFC = false;
                boolean existeCURP = false;
                for(Object docenteDao : docentes){
                    Docente docente = (Docente) docenteDao;
                    if(docente.getNumeroPersonalDocente().equalsIgnoreCase(this.textFieldNumeroPersonal.getText())){
                        existeNumeroPersonal = true;
                        break;
                    }else if(docente.getRfcDocente().equalsIgnoreCase(this.textFieldRFC.getText())){
                        existeRFC = true;
                        break;
                    }else if(docente.getCurpDocente().equalsIgnoreCase(this.textFieldCURP.getText())){
                        existeCURP = true;
                        break;
                    }
                }
                
                if(existeNumeroPersonal){
                    Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
                    alertaConfirmacion.setHeaderText(null);
                    alertaConfirmacion.setTitle("Alerta");
                    alertaConfirmacion.setContentText("El numero de Personal ya se encuentra registrado.");
                    this.textFieldNumeroPersonal.setText("");
                    alertaConfirmacion.showAndWait();
                }else if(existeRFC){
                    Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
                    alertaConfirmacion.setHeaderText(null);
                    alertaConfirmacion.setTitle("Alerta");
                    alertaConfirmacion.setContentText("El RFC ya se encuentra registrado.");
                    this.textFieldRFC.setText("");
                    alertaConfirmacion.showAndWait();
                }else if(existeCURP){
                    Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
                    alertaConfirmacion.setHeaderText(null);
                    alertaConfirmacion.setTitle("Alerta");
                    alertaConfirmacion.setContentText("La CURP ya se encuentra registrada.");
                    this.textFieldCURP.setText("");
                    alertaConfirmacion.showAndWait();
                }else{
                    Docente docente = new Docente();
                    docente.setNumeroPersonalDocente(this.textFieldNumeroPersonal.getText());
                    docente.setNombreDocente(this.textFieldNombre.getText());
                    docente.setApellidoPaternoDocente(this.textFieldApellidoPaterno.getText());
                    docente.setApellidoMaternoDocente(this.textFieldApellidoMaterno.getText());
                    docente.setEmailDocente(this.textFieldEmail.getText());
                    docente.setTelefonoDocente(this.textFieldTelefono.getText());
                    docente.setCurpDocente(this.textFieldCURP.getText());
                    docente.setRfcDocente(this.textFieldRFC.getText());
                    docente.setEstadoDocente("Activo");
                    docente.setRfcDocente(this.textFieldRFC.getText());
                    docente.setCurpDocente(this.textFieldCURP.getText());

                    docenteDaoImpl = new DocenteDaoImpl();
                    docenteDaoImpl.save(docente);
                    String contraseña = GeneradorDeContraseña.obtenerContraseña();
                    String contraseñaHash = Hash.getHash(contraseña);
                    UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(this.textFieldNumeroPersonal.getText());
                    usuario.setContraseñaUsuario(contraseñaHash);
                    usuario.setTipoUsuario("Docente");
                    usuario.setEstadoUsuario("Activo");
                    usuarioDaoImpl.save(usuario);
                    Email.enviarEmail(this.textFieldNumeroPersonal.getText(), contraseña, this.textFieldEmail.getText());
                    limpiarCampos();
                    Alert alertaConfirmacion = new Alert(Alert.AlertType.INFORMATION);
                    alertaConfirmacion.setHeaderText(null);
                    alertaConfirmacion.setTitle("Informacion");
                    alertaConfirmacion.setContentText("Docente registrado Exitosamente.");
                    alertaConfirmacion.showAndWait();
                }
            } catch (SQLException ex) {
                Ventana.iniciarVentana(buttonAceptar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
            }
        }
    }
    
    public void limpiarCampos(){
        this.textFieldApellidoMaterno.setText("");
        this.textFieldApellidoPaterno.setText("");
        this.textFieldEmail.setText("");
        this.textFieldNombre.setText("");
        this.textFieldNumeroPersonal.setText("");
        this.textFieldTelefono.setText("");
        this.textFieldCURP.setText("");
        this.textFieldRFC.setText("");
    }
    
}
