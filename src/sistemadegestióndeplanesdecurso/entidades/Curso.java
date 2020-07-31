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
public class Curso {
    private String nombreCurso;
    private String periodoCurso;
    private String añoCurso;
    private String turnoCurso;
    private String estadoCurso;
    private String nrc;
    private String docenteAsignado;

    public String getDocenteAsignado() {
        return docenteAsignado;
    }

    public void setDocenteAsignado(String docenteAsignado) {
        this.docenteAsignado = docenteAsignado;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getPeriodoCurso() {
        return periodoCurso;
    }

    public void setPeriodoCurso(String periodoCurso) {
        this.periodoCurso = periodoCurso;
    }

    public String getAñoCurso() {
        return añoCurso;
    }

    public void setAñoCurso(String añoCurso) {
        this.añoCurso = añoCurso;
    }

    public String getTurnoCurso() {
        return turnoCurso;
    }

    public void setTurnoCurso(String turnoCurso) {
        this.turnoCurso = turnoCurso;
    }

    public String getEstadoCurso() {
        return estadoCurso;
    }

    public void setEstadoCurso(String estadoCurso) {
        this.estadoCurso = estadoCurso;
    }
}
