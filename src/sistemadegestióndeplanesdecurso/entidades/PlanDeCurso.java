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
public class PlanDeCurso {
    private String nombreCurso;
    private String periodoCurso;
    private String nombreArchivoPlanDeCurso;
    private String nrc;
    private String nombreDocente;
    private String objetivo;
    private int porcentajePrimerParcial;
    private int porcentajeSegundoParcial;
    private int porcentajeTareas;
    private int porcentajeProyectos;
    private int porcentajeExposiciones;

    public int getPorcentajePrimerParcial() {
        return porcentajePrimerParcial;
    }

    public void setPorcentajePrimerParcial(int porcentajePrimerParcial) {
        this.porcentajePrimerParcial = porcentajePrimerParcial;
    }

    public int getPorcentajeSegundoParcial() {
        return porcentajeSegundoParcial;
    }

    public void setPorcentajeSegundoParcial(int porcentajeSegundoParcial) {
        this.porcentajeSegundoParcial = porcentajeSegundoParcial;
    }

    public int getPorcentajeTareas() {
        return porcentajeTareas;
    }

    public void setPorcentajeTareas(int porcentajeTareas) {
        this.porcentajeTareas = porcentajeTareas;
    }

    public int getPorcentajeProyectos() {
        return porcentajeProyectos;
    }

    public void setPorcentajeProyectos(int porcentajeProyectos) {
        this.porcentajeProyectos = porcentajeProyectos;
    }

    public int getPorcentajeExposiciones() {
        return porcentajeExposiciones;
    }

    public void setPorcentajeExposiciones(int porcentajeExposiciones) {
        this.porcentajeExposiciones = porcentajeExposiciones;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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

    public String getNombreArchivoPlanDeCurso() {
        return nombreArchivoPlanDeCurso;
    }

    public void setNombreArchivoPlanDeCurso(String nombreArchivoPlanDeCurso) {
        this.nombreArchivoPlanDeCurso = nombreArchivoPlanDeCurso;
    }
}
