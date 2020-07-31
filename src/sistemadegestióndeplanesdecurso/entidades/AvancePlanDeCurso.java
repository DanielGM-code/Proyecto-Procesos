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
public class AvancePlanDeCurso {
    private String nombreCurso;
    private String periodoCurso;
    private String nombreArchivoAcancePlanDeCurso;

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

    public String getNombreArchivoAcancePlanDeCurso() {
        return nombreArchivoAcancePlanDeCurso;
    }

    public void setNombreArchivoAcancePlanDeCurso(String nombreArchivoAcancePlanDeCurso) {
        this.nombreArchivoAcancePlanDeCurso = nombreArchivoAcancePlanDeCurso;
    }
}
