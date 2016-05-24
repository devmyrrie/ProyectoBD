/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

/**
 *
 * @author Programacion
 */
public class Estudios {

    private int finalizacion;
    private String nombreInst;
    private int tipoEstudio;
    //Si es tecnico,Universitario u Otro se llena lo siguiente
    private String gradoAcademico;
    private String nombreCarrera;
    private boolean extender;

    public Estudios(int f, String nIns, int tEst, String gAcad, String nomCa) {
        finalizacion = f;
        nombreInst = nIns;
        tipoEstudio = tEst;
        gradoAcademico = gAcad;
        nombreCarrera = nomCa;
        extender = false;
    }

    public Estudios() {
        extender = false;

    }

    public void setFinalizacion(int f) {
        finalizacion = f;
    }

    public boolean getExtender() {
        return extender;
    }

    public void setExtender(boolean x) {

        extender = x;
    }

    public int getFinalizacion() {
        return finalizacion;
    }

    public void setNombreInst(String nIns) {
        nombreInst = nIns;
    }

    public String getNombreInst() {
        return nombreInst;
    }

    public void setTipoEstudio(int tEst) {
        tipoEstudio = tEst;
    }

    public int getTipoEstudio() {
        return tipoEstudio;
    }

    public void setGradoAcademico(String gAcad) {
        gradoAcademico = gAcad;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setNombreCarrera(String nomCa) {
        nombreCarrera = nomCa;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

}
