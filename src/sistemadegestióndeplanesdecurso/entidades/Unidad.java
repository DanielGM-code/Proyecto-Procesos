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
public class Unidad {
    private String nrc;
    private int numeroUnidad;
    private String temaUnidad;
    private String tecnicas;

    public String getTecnicas() {
        return tecnicas;
    }

    public void setTecnicas(String tecnicas) {
        this.tecnicas = tecnicas;
    }
    private String fechaInicio;
    private String fechaFin;
    private String tareas;

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public int getNumeroUnidad() {
        return numeroUnidad;
    }

    public void setNumeroUnidad(int numeroUnidad) {
        this.numeroUnidad = numeroUnidad;
    }

    public String getTemaUnidad() {
        return temaUnidad;
    }

    public void setTemaUnidad(String temaUnidad) {
        this.temaUnidad = temaUnidad;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTareas() {
        return tareas;
    }

    public void setTareas(String tareas) {
        this.tareas = tareas;
    }
}
