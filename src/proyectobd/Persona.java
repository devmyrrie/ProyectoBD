/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.sql.Date;
import java.math.BigDecimal;


/**
 *
 * @author Programacion
 */
public class Persona {
    
    Persona(){
    
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumDep(int d) {
        numeroDepCand = d;
    }

    public void setFechaAsp(Date fecha) {
        this.fechaAsp = fecha;
    }

    public Date getFechaAsp() {
        return this.fechaAsp;
    }

    public void setNombreDepartamentoCandidato(String nombre) {
        nombreDep = nombre;
    }

    public String getNombreDepartamentoCandidato() {
        return nombreDep;
    }

    public void setSexo(char sex) {
        this.sexo = sex;
    }

    public char getSexo() {
        return sexo;

    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumDepCand() {
        return numeroDepCand;

    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public void setTipoEmpresa(int tip) {
        tipoempresa = tip;

    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public void setSueldoAspirado(BigDecimal sueldoAspirado) {
        this.sueldoAspirado = sueldoAspirado;
    }

    public void setEC(String EC) {
        this.EC = EC;
    }

    public void setExpLab(String expLab) {
        this.expLab = expLab;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setGrupoOrden(char grupoOrden) {
        this.grupoOrden = grupoOrden;
    }

    public void setCedula(BigDecimal cedula) {
        this.cedula = cedula;
    }

    public BigDecimal getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCanton() {
        return canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public BigDecimal getSueldoAspirado() {
        return sueldoAspirado;
    }

    public String getEC() {
        return EC;
    }

    public String getExpLab() {
        return expLab;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public String getObs() {
        return obs;
    }

    public int getTipoEmpresa() {
        return tipoempresa;

    }

    public char getGrupoOrden() {
        return grupoOrden;
    }

    public String getCargo() {
        return cargo;

    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void imprimir() {
        String x = nombre + "\n"
                + apellido1 + "\n"
                + apellido2 + "\n"
                + fechaNac + "\n"
                + provincia + "\n"
                + canton + "\n"
                + distrito + "\n"
                + direccionCompleta + "\n"
                + sueldoAspirado + "\n"
                + EC + "\n"
                + expLab + "\n"
                + numHijos + "\n"
                + obs + "\n"
                + grupoOrden + "\n"
                + cedula + "\n"
                + sexo + "\n"
                + nombreDep + "\n"
                + fechaAsp + "\n"
                + numeroDepCand + "\n"
                + tipoempresa;
        System.out.println(x);

    }
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNac;
    private String provincia;
    private String canton;
    private String distrito;
    private String direccionCompleta;
    private BigDecimal sueldoAspirado;
    private String EC;
    private String expLab;
    private int numHijos;
    private String obs;
    private char grupoOrden;
    private BigDecimal cedula;
    private char sexo;
    private String nombreDep;
    private Date fechaAsp;
    private int numeroDepCand;
    private int tipoempresa;
    private String cargo;
}
