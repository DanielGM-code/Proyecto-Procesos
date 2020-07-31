/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.entidades;

/**
 *
 * @author angel
 */
public class Directivo {
    private String numeroPersonalDirectivo;
    private String nombreDirectivo;
    private String apellidoPaternoDirectivo;
    private String apellidoMaternoDirectivo;
    private String telefonoDirectivo;
    private String emailDirectivo;
    private String estadoDirectivo;

    public String getNumeroPersonalDirectivo() {
        return numeroPersonalDirectivo;
    }

    public void setNumeroPersonalDirectivo(String numeroPersonalDirectivo) {
        this.numeroPersonalDirectivo = numeroPersonalDirectivo;
    }

    public String getNombreDirectivo() {
        return nombreDirectivo;
    }

    public void setNombreDirectivo(String nombreDirectivo) {
        this.nombreDirectivo = nombreDirectivo;
    }

    public String getApellidoPaternoDirectivo() {
        return apellidoPaternoDirectivo;
    }

    public void setApellidoPaternoDirectivo(String apellidoPaternoDirectivo) {
        this.apellidoPaternoDirectivo = apellidoPaternoDirectivo;
    }

    public String getApellidoMaternoDirectivo() {
        return apellidoMaternoDirectivo;
    }

    public void setApellidoMaternoDirectivo(String apellidoMaternoDirectivo) {
        this.apellidoMaternoDirectivo = apellidoMaternoDirectivo;
    }

    public String getTelefonoDirectivo() {
        return telefonoDirectivo;
    }

    public void setTelefonoDirectivo(String telefonoDirectivo) {
        this.telefonoDirectivo = telefonoDirectivo;
    }

    public String getEmailDirectivo() {
        return emailDirectivo;
    }

    public void setEmailDirectivo(String emailDirectivo) {
        this.emailDirectivo = emailDirectivo;
    }

    public String getEstadoDirectivo() {
        return estadoDirectivo;
    }

    public void setEstadoDirectivo(String estadoDirectivo) {
        this.estadoDirectivo = estadoDirectivo;
    }
}
