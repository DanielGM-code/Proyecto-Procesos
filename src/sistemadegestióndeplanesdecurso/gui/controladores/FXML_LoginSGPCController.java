/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.gui.controladores;

import sistemadegestióndeplanesdecurso.entidades.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sistemadegestióndeplanesdecurso.datos.daoImpl.UsuarioDaoImpl;
import sistemadegestióndeplanesdecurso.logica.Hash;
import sistemadegestióndeplanesdecurso.logica.Ventana;

/**
 * FXML Controller class
 *
 * @author dagam
 */
public class FXML_LoginSGPCController implements Initializable {

    @FXML
    private Button buttonEntrar;
    @FXML
    private TextField textFieldMatricula;
    @FXML
    private PasswordField textFieldContraseña;
    @FXML
    private Label labelMensajeError;
    Ventana ventana = new Ventana();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        if(textFieldMatricula.getText().isEmpty() || textFieldContraseña.getText().isEmpty()){
            labelMensajeError.setText("Asegúrese de llenar todos los campos");
        }else{
            try {
                String matricula = textFieldMatricula.getText();
                String contraseña = String.valueOf(textFieldContraseña.getText());
                String contraseñaHash = Hash.getHash(contraseña);
                
                UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
                List<Object> usuarios =  usuarioDaoImpl.getAll();
                Usuario usuario = new Usuario();
                boolean usuarioEncontrado = false;
                
                for(Object usuarioBusqueda : usuarios){
                    Usuario usuarioCast = (Usuario) usuarioBusqueda;
                    if(usuarioCast.getNombreUsuario().equalsIgnoreCase(matricula) && usuarioCast.getEstadoUsuario().equalsIgnoreCase("Activo")){
                        usuarioEncontrado = true;
                        usuario = usuarioCast;
                        break;
                    }
                }
                if(!usuarioEncontrado){
                    labelMensajeError.setText("Nombre de Usuario incorrecto");
                    this.textFieldMatricula.setText("");
                    this.textFieldContraseña.setText("");
                }else{
                    String contraseñaUsuario = usuario.getContraseñaUsuario();
                    String tipoUsuario = usuario.getTipoUsuario();
                    
                    if(contraseñaUsuario.equals(contraseñaHash) && usuario.getEstadoUsuario().equalsIgnoreCase("Activo")){
                        if(tipoUsuario.equals("Administrador")){
                            Ventana.iniciarVentana(buttonEntrar, new FXML_MenuAdministradorController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_MenuAdministrador.fxml", Ventana.CERRAR);
                        }else if(tipoUsuario.equals("Docente")){
                            Ventana.iniciarVentana(buttonEntrar, new FXML_MenuDocenteController(this.textFieldMatricula.getText()), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_MenuDocente.fxml", Ventana.CERRAR);
                        }
                    }else{
                        labelMensajeError.setText("Contraseña incorrecta");
                        this.textFieldContraseña.setText("");
                    }
                }
            } catch (SQLException ex) {
                Ventana.iniciarVentana(buttonEntrar, new FXML_ErrorDeConexionController(), "/sistemadegestióndeplanesdecurso/gui/vistas/FXML_ErrorDeConexion.fxml", Ventana.CERRAR);
            }
        }
    }
}
