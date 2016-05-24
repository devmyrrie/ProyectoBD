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
public class Departamento {

    private int numeroDep;
    private String nombreDep;

    public Departamento(int numD, String nomD) {
        numeroDep = numD;
        nombreDep = nomD;
    }

    public Departamento() {

    }

    public void setNumero(int numD) {
        numeroDep = numD;
    }

    public int getNumero() {
        return numeroDep;
    }

    public void setNombre(String nomD) {
        nombreDep = nomD;
    }

    public String getNombre() {
        return nombreDep;
    }

}
