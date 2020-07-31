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
public class Docente {
    private String numeroPersonalDocente;
    private String nombreDocente;
    private String apellidoPaternoDocente;
    private String apellidoMaternoDocente;
    private String telefonoDocente;
    private String emailDocente;
    private String estadoDocente;
    private String rfcDocente;
    private String curpDocente;

    public String getRfcDocente() {
        return rfcDocente;
    }

    public void setRfcDocente(String rfcDocente) {
        this.rfcDocente = rfcDocente;
    }

    public String getCurpDocente() {
        return curpDocente;
    }

    public void setCurpDocente(String curpDocente) {
        this.curpDocente = curpDocente;
    }

    public String getNumeroPersonalDocente() {
        return numeroPersonalDocente;
    }

    public void setNumeroPersonalDocente(String numeroPersonalDocente) {
        this.numeroPersonalDocente = numeroPersonalDocente;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getApellidoPaternoDocente() {
        return apellidoPaternoDocente;
    }

    public void setApellidoPaternoDocente(String apellidoPaternoDocente) {
        this.apellidoPaternoDocente = apellidoPaternoDocente;
    }

    public String getApellidoMaternoDocente() {
        return apellidoMaternoDocente;
    }

    public void setApellidoMaternoDocente(String apellidoMaternoDocente) {
        this.apellidoMaternoDocente = apellidoMaternoDocente;
    }

    public String getTelefonoDocente() {
        return telefonoDocente;
    }

    public void setTelefonoDocente(String telefonoDocente) {
        this.telefonoDocente = telefonoDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }

    public String getEstadoDocente() {
        return estadoDocente;
    }

    public void setEstadoDocente(String estadoDocente) {
        this.estadoDocente = estadoDocente;
    }
}
