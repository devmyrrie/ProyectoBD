/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.math.BigDecimal;

/**
 *
 * @author Programacion
 */
public class Consultas {

    ConexionBD conexion;

    public Consultas() {

        conexion = new ConexionBD();
    }

    @Override
    protected void finalize() throws Throwable {
        conexion.desconectar();
        super.finalize();

    }

    public Persona consultaInfo(BigDecimal keyCedula) {
        Persona nuevaPersona = this.consultaInfo(keyCedula, "", "", "");
        return nuevaPersona;
    }

    public Persona consultaInfo(String nom, String apell, String apell2) {
        Persona person = new Persona();
        person = this.consultaInfo(new BigDecimal("-1"), nom, apell, apell2);

        return person;

    }

    public void insertarEstudios(Estudios estudio, BigDecimal cedula) {
        String query = "INSERT INTO estudios  (idmayorestudio, finalizacion, cedula, nombreinst) values(?,?,?,?) RETURNING id ";
        String query2 = "INSERT INTO titulo (cedpersona, idmayorestudio, finalizacion, nombrecarrera, gradoacademico,id) values(?,?,?,?,?,?)";
        boolean extender = estudio.getExtender();

        PreparedStatement consulta = null;
        ResultSet result = null;
        try {
            consulta = conexion.getConexion().prepareStatement(query);
            consulta.setInt(1, estudio.getTipoEstudio());
            consulta.setInt(2, estudio.getFinalizacion());
            consulta.setBigDecimal(3, cedula);
            consulta.setString(4, estudio.getNombreInst());
            int miId = -1;
            result = consulta.executeQuery();
            if (result.next()) {
                miId = result.getInt(1);
                consulta.close();
                result.close();
                if (estudio.getExtender()) {
                    consulta = conexion.getConexion().prepareStatement(query2);
                    consulta.setBigDecimal(1, cedula);
                    consulta.setInt(2, estudio.getTipoEstudio());
                    consulta.setInt(3, estudio.getFinalizacion());
                    consulta.setString(4, estudio.getNombreCarrera());
                    consulta.setString(5, estudio.getGradoAcademico());
                    consulta.setInt(6, miId);

                    consulta.executeUpdate();
                    consulta.close();
                    result.close();

                }

            } else {

                JOptionPane.showMessageDialog(null, "No existe esa persona\n");

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            ex.getSQLState();
            ex.getMessage();

        }

    }

    public boolean insertarPersona(Persona persona) {
        PreparedStatement consulta = null;
        boolean ret = true;
        try {

            int dato = 0;
            String query = "INSERT INTO persona (cedula, nombre, apellido, apellido2, sexo, fechanac, provincia,"
                    + "canton, distrito, sueldoasp, ec, numhijos, explab, observaciones,"
                    + "grupoorden, numerodepcand, tipoempresa, fechaasp,cargo)  VALUES " + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            persona.imprimir();
            consulta = conexion.getConexion().prepareStatement(query);
            consulta.setBigDecimal(1, persona.getCedula());
            consulta.setString(2, persona.getNombre());
            consulta.setString(3, persona.getApellido1());
            consulta.setString(4, persona.getApellido2());
            consulta.setString(5, String.valueOf(persona.getSexo()));
            consulta.setDate(6, persona.getFechaNac());
            consulta.setString(7, persona.getProvincia());
            consulta.setString(8, persona.getCanton());
            consulta.setString(9, persona.getDistrito());
            consulta.setBigDecimal(10, persona.getSueldoAspirado());
            consulta.setString(11, persona.getEC());
            consulta.setInt(12, persona.getNumHijos());
            consulta.setString(13, persona.getExpLab());
            consulta.setString(14, persona.getObs());
            consulta.setString(15, String.valueOf(persona.getGrupoOrden()));
            consulta.setInt(16, persona.getNumDepCand());
            consulta.setInt(17, persona.getTipoEmpresa());
            consulta.setDate(18, persona.getFechaAsp());
            consulta.setString(19, persona.getCargo());

            consulta.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            ex.getSQLState();
            ex.getMessage();
            JOptionPane.showMessageDialog(null, "Esta persona existe en la base de datos\n");
            ret = false;
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return ret;
        }

    }

    public boolean actualizarNombre(String nombre, BigDecimal cedula) {
        PreparedStatement consulta;
        boolean todoBien = true;

        try {
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET nombre = ? WHERE cedula = ?");
            consulta.setString(1, nombre);
            consulta.setBigDecimal(2, cedula);
            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }

    }

    public boolean actualizarDepartamento(int depa, BigDecimal cedula) {
        PreparedStatement consulta;
        boolean todoBien = true;

        try {
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET numerodepcand = ? WHERE cedula = ?");
            consulta.setInt(1, depa);
            consulta.setBigDecimal(2, cedula);
            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarEmpresa(int empresa, BigDecimal cedula) {
        PreparedStatement consulta;
        boolean todoBien = true;
        try {
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET tipoEmpresa = ? WHERE cedula = ?");
            consulta.setInt(1, empresa);
            consulta.setBigDecimal(2, cedula);
            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarApellido(String apellido1, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET apellido = ? WHERE cedula = ?");
            consulta.setString(1, apellido1);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarSegundoApellido(String apellido2, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET apellido2 = ? WHERE cedula = ?");
            consulta.setString(1, apellido2);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula     

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarSexo(String sexo, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET sexo = ? WHERE cedula = ?");
            consulta.setString(1, sexo);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula     

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }

    }

    public boolean actualizarProvincia(String prov, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET provincia = ? WHERE cedula = ?");
            consulta.setString(1, prov);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula     

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarCargo(String cargo, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET cargo = ? WHERE cedula = ?");
            consulta.setString(1, cargo);
            consulta.setBigDecimal(2, cedula);

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarCanton(String cant, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET canton = ? WHERE cedula = ?");
            consulta.setString(1, cant);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula     

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarDistrito(String dst, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET distrito = ? WHERE cedula = ?");
            consulta.setString(1, dst);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula     

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarSueldo(BigDecimal sueldo, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET sueldoasp = ? WHERE cedula = ?");
            consulta.setBigDecimal(1, sueldo);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula     

            consulta.executeQuery();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarEC(String ec, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET ec = ? WHERE cedula = ?");
            consulta.setString(1, ec);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public boolean actualizarNumHijos(int numHijos, BigDecimal cedula) {
        boolean todoBien = true;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("UPDATE persona  SET numhijos = ? WHERE cedula = ?");
            consulta.setInt(1, numHijos);     //El primer "?" es  cedula     
            consulta.setBigDecimal(2, cedula);

            consulta.executeUpdate();
            consulta.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            todoBien = false;
        } finally {
            return todoBien;

        }
    }

    public void insertarTelefonos(BigDecimal cedula, Vector<Integer> tel) {
        PreparedStatement consulta;
        while (tel.isEmpty() == false) {
            try {
                int dato = 0;

                consulta = conexion.getConexion().prepareStatement("INSERT INTO telefono (numerotelefono, cedula)  VALUES (?,?)");
                consulta.setBigDecimal(2, cedula);     //El primer "?" es  cedula  
                System.out.println(tel.elementAt(0));
                consulta.setInt(1, tel.remove(0));
                consulta.executeUpdate();
                consulta.close();

            } catch (SQLException ex) {
                Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public Vector<Integer> consultarTelefonos(BigDecimal cedula) {
        Vector<Integer> telefonos = new Vector();
        try {
            PreparedStatement consulta;
            ResultSet res = null;
            int dato = 0;

            consulta = conexion.getConexion().prepareStatement("SELECT numerotelefono FROM " + "telefono" + " WHERE" + " cedula = " + " ? ");
            consulta.setBigDecimal(1, cedula);     //El primer "?" es  cedula     

            res = consulta.executeQuery();

            while (res.next()) {
                dato = res.getInt("numerotelefono");
                telefonos.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return telefonos;

    }

    public Vector<String> consultarPersonasFecha(Date fechaDesde, Date fechaHasta) {
        Vector<String> personas = new Vector();
        try {
            PreparedStatement consulta;
            ResultSet res = null;
            String dato = "";
            consulta = conexion.getConexion().prepareStatement("SELECT cedula,nombre,apellido,apellido2 FROM " + "persona" + " WHERE" + " fechaasp BETWEEN " + " ? " + "AND  " + "? ");
            consulta.setDate(1, fechaDesde);     //El primer "?" es  fechaDesde     
            consulta.setDate(2, fechaHasta);     //El primer "?" es fechaHasta.    

            res = consulta.executeQuery();

            while (res.next()) {
                dato = res.getString("Cedula") + "-" + res.getString("Nombre") + " " + res.getString("Apellido") + " " + res.getString("Apellido2") + "\n";
                personas.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personas;

    }

    public int eliminarPersona(BigDecimal cedula) {
        int ok = 0;
        PreparedStatement consulta;
        String dato = "";
        try {
            System.out.println(cedula + " por eliminar");
            consulta = conexion.getConexion().prepareStatement("DELETE FROM persona " + " WHERE" + " cedula = ?  ");
            consulta.setBigDecimal(1, cedula);        //El primero "?" es el cargo
            ok = consulta.executeUpdate();
            consulta.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public Vector<String> consultarPersonasGFC(Date fechaDesde, Date fechaHasta, String grupo, String cargo) {
        Vector<String> personas = new Vector();
        String consString = "SELECT cedula,nombre,apellido,apellido2 FROM " + "persona" + " WHERE" + " cargo " + " ILIKE ? " + " AND grupoorden " + "  ILIKE ? " + " AND fechaasp BETWEEN " + " ? " + "AND  " + "? ";
        try {
            PreparedStatement consulta;
            ResultSet res = null;
            String dato = "";
            consulta = conexion.getConexion().prepareStatement(consString);
            consulta.setString(1, cargo);        //El primero "?" es el cargo
            consulta.setString(2, grupo);
            consulta.setDate(3, fechaDesde);     //El tercero "?" es  fechaDesde     
            consulta.setDate(4, fechaHasta);     //El cuarto "?" es fechaHasta.    

            res = consulta.executeQuery();

            while (res.next()) {
                System.out.println("Entre");
                dato = res.getString("Cedula") + "-" + res.getString("Nombre") + " " + res.getString("Apellido") + " " + res.getString("Apellido2") + "\n";
                System.out.println(dato);
                personas.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personas;
    }

    public Vector<String> consultarPersonasGF(Date fechaDesde, Date fechaHasta, String grupo) {
        Vector<String> personas = new Vector();
        try {
            PreparedStatement consulta;
            ResultSet res = null;
            String dato = "";
            System.out.println("Fecha Desde: " + fechaDesde);
            System.out.println("Fecha Hasta: " + fechaHasta);

            consulta = conexion.getConexion().prepareStatement("SELECT cedula,nombre,apellido,apellido2 FROM " + "persona" + " WHERE" + " grupoorden " + " ILIKE ? " + " AND fechaasp BETWEEN " + " ? " + "AND  " + "? ");
            consulta.setString(1, grupo);        //El primero "?" es el grupo
            consulta.setDate(2, fechaDesde);     //El primer "?" es  fechaDesde     
            consulta.setDate(3, fechaHasta);     //El primer "?" es fechaHasta.    

            res = consulta.executeQuery();

            while (res.next()) {
                dato = res.getString("Cedula") + "-" + res.getString("Nombre") + " " + res.getString("Apellido") + " " + res.getString("Apellido2") + "\n";
                personas.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personas;
    }

    private Persona consultaInfo(BigDecimal keyCedula, String nom, String apell, String apell2) {
        Persona person = new Persona();
        try {
            PreparedStatement consulta;
            ResultSet res;
            String dato = "";

            if (0 == keyCedula.compareTo(new BigDecimal("-1"))) {
                consulta = conexion.getConexion().prepareStatement("SELECT * FROM " + "persona" + " WHERE" + " nombre " + "= ? " + "AND apellido " + "= ? " + " AND apellido2 " + "= ? ");
                consulta.setString(1, nom);     //El primer "?" es cedula.       
                consulta.setString(2, apell);
                consulta.setString(3, apell2);
            } else {
                consulta = conexion.getConexion().prepareStatement("SELECT * FROM " + "persona" + " WHERE" + " cedula " + "= ? ");
                consulta.setBigDecimal(1, keyCedula);

            }

            res = consulta.executeQuery();

            if (res.next()) {
                person.setGrupoOrden(res.getString("GrupoOrden").charAt(0));
                person.setEC(res.getString("EC"));
                person.setSueldoAspirado(res.getBigDecimal("SueldoAsp"));
                person.setObs(res.getString("Observaciones"));
                person.setFechaNac(res.getDate("FechaNac"));
                person.setDireccionCompleta(res.getString("Provincia") + " \n" + res.getString("Canton") + " \n" + res.getString("Distrito"));
                person.setExpLab(res.getString("ExpLab"));
                person.setNumHijos(res.getInt("numhijos"));
                person.setSexo(res.getString("Sexo").charAt(0));
                person.setFechaAsp(res.getDate("fechaasp"));
                person.setCargo(res.getString("cargo"));
                person.setProvincia(res.getString("Provincia"));
                person.setCanton(res.getString("Canton"));
                person.setDistrito(res.getString("Distrito"));
                if (keyCedula.compareTo(new BigDecimal("-1")) == 0) {
                    person.setCedula(res.getBigDecimal("Cedula"));
                    person.setNombre(nom);
                    person.setApellido1(apell);
                    person.setApellido2(apell2);
                } else {
                    person.setNombre(res.getString("Nombre"));
                    person.setApellido1(res.getString("apellido"));
                    person.setApellido2(res.getString("apellido2"));
                    person.setCedula((keyCedula));
                }
            } else {
                person = null;
            }
            res.close();
            consulta.close();

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "no se pudo consultar la persona con el nombre dado\n" + e);
        }
        return person;
    }

    public String getNombreDep(int key) {
        String nombreDep = null;
        try {
            PreparedStatement consulta;
            consulta = conexion.getConexion().prepareStatement("SELECT nombre FROM " + "departamento" + " WHERE" + " numero " + "= ? ");
            consulta.setInt(1, key);     //El primer "?" es cedula.       
            ResultSet res;
            res = consulta.executeQuery();
            if (res.next()) {
                nombreDep = (res.getString("nombre"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encuentra el departamento en la base de datos\n");
            }
            res.close();
            consulta.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "no se pudo consultar el departamento\n" + e);
        }
        return nombreDep;

    }

    public Vector<String> listaNombresIguales(String nombre, String apellido, String apellido2, BigDecimal cedulaExcluir) {
        Vector<String> listaPersonas = new Vector();
        try {
            PreparedStatement consulta;
            ResultSet res;
            String dato = "";

            consulta = conexion.getConexion().prepareStatement("SELECT cedula,nombre FROM " + "persona" + " WHERE" + " nombre " + "= ? " + "AND apellido " + "= ? " + " AND apellido2 " + "= ? " + "AND" + " cedula " + "!= ?");
            consulta.setString(1, nombre);     //El primer "?" es cedula.       
            consulta.setString(2, apellido);     //El primer "?" es cedula.       
            consulta.setString(3, apellido2);     //El primer "?" es cedula.  
            consulta.setBigDecimal(4, cedulaExcluir);

            res = consulta.executeQuery();

            while (res.next()) {
                dato = res.getString("Cedula") + "-" + nombre + " " + apellido + " " + apellido2 + "\n";
                System.out.println(dato);
                listaPersonas.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonas;
    }

    public int consultarMayorIndiceEstudio() {
        int numero = 0;
        String consString = "SELECT COUNT * AS NumeroDeTipos FROM CategoriaAcademica";
        PreparedStatement consultaEstudios = null;
        ResultSet resultado;
        try {
            consultaEstudios = conexion.getConexion().prepareStatement(consString);
            resultado = consultaEstudios.executeQuery();
            if (resultado.next()) {
                numero = resultado.getInt("NumeroDeTipos");
                consultaEstudios.close();
                resultado.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }

    public void consultarEstudiosExtendidos(Estudios est, BigDecimal cedula, int cursor) {
        String consString = "SELECT * FROM titulo WHERE cedpersona " + "=? " + " AND " + " idmayorEstudio " + " =?" + " AND finalizacion " + " =?" + " AND id " + " =?";
        PreparedStatement consultaTitulos = null;
        ResultSet resultado;
        try {
            consultaTitulos = conexion.getConexion().prepareStatement(consString);
            consultaTitulos.setBigDecimal(1, cedula);
            consultaTitulos.setInt(2, est.getTipoEstudio());
            consultaTitulos.setInt(3, est.getFinalizacion());
            consultaTitulos.setInt(4, cursor);
            resultado = consultaTitulos.executeQuery();
            System.out.println("Entrare");

            if (resultado.next()) {
                est.setNombreCarrera(resultado.getString("nombrecarrera"));
                System.out.println(resultado.getString("nombrecarrera"));
                est.setGradoAcademico(resultado.getString("GradoAcademico"));
                System.out.println(resultado.getString("gradoAcademico"));

            }
            consultaTitulos.close();
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Estudios> consultarEstudios(BigDecimal cedula) {
        Vector<Estudios> est = new Vector();
        String consString = "SELECT * FROM Estudios WHERE cedula " + "=? ";
        PreparedStatement consultaEstudios = null;
        ResultSet resultado;
        try {
            consultaEstudios = conexion.getConexion().prepareStatement(consString);
            consultaEstudios.setBigDecimal(1, cedula);     //El primer "?" es cedula.  
            resultado = consultaEstudios.executeQuery();
            while (resultado.next()) {
                Estudios estudios = new Estudios();
                estudios.setNombreInst(resultado.getString("NombreInst"));
                estudios.setFinalizacion(resultado.getInt("Finalizacion"));
                estudios.setTipoEstudio(resultado.getInt("idmayorestudio"));
                int idCursor = resultado.getInt("id");
                System.out.println(" TIPO ESTUDIO " + estudios.getTipoEstudio());
                if (estudios.getTipoEstudio() >= 2 && estudios.getTipoEstudio() < 4) {
                    estudios.setExtender(true);
                    System.out.println("ENTRE");
                    consultarEstudiosExtendidos(estudios, cedula, idCursor);
                }
                est.add(estudios);
                System.out.println(estudios.getNombreInst());

            }
            resultado.close();
            consultaEstudios.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return est;
    }

    public Departamento consultarDepartamento(BigDecimal cedula) {
        Departamento miDepa = new Departamento();

        try {
            String consString = "SELECT numerodepcand FROM persona WHERE cedula " + "=? ";
            PreparedStatement consultaDeparta = null;
            ResultSet resultado;
            consultaDeparta = conexion.getConexion().prepareStatement(consString);
            consultaDeparta.setBigDecimal(1, cedula);     //El primer "?" es cedula.  
            resultado = consultaDeparta.executeQuery();
            if (resultado.next()) {

                miDepa.setNumero(resultado.getInt("numerodepcand"));
                consultaDeparta.close();
                resultado.close();
                try {
                    consultaDeparta = conexion.getConexion().prepareStatement("SELECT nombre FROM " + "departamento" + " WHERE" + " numero " + "= ? ");
                    consultaDeparta.setInt(1, miDepa.getNumero());
                    ResultSet res;
                    res = consultaDeparta.executeQuery();
                    if (res.next()) {
                        miDepa.setNombre(res.getString("nombre"));
                    }
                    consultaDeparta.close();
                    resultado.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return miDepa;
    }

    public Vector<String> listaNombresIguales(BigDecimal cedula) {
        Vector<String> listaPersonas = new Vector();
        String nombre;
        String apellido;
        String apellido2;
        String consString = "SELECT nombre,apellido,apellido2 FROM persona WHERE cedula " + "=? ";
        PreparedStatement consultaNombre = null;
        ResultSet resultado;
        String dato;
        try {
            consultaNombre = conexion.getConexion().prepareStatement(consString);
            consultaNombre.setBigDecimal(1, cedula);     //El primer "?" es cedula.  
            resultado = consultaNombre.executeQuery();
            if (resultado.next()) {
                nombre = resultado.getString("nombre");
                apellido = resultado.getString("apellido");
                apellido2 = resultado.getString("apellido2");
                consultaNombre.close();
                resultado.close();

                consString = "SELECT cedula from persona WHERE " + " nombre " + " =? " + "AND " + "apellido " + "=?" + " AND " + "apellido2  " + "=?" + " AND " + "cedula " + " != ?";
                consultaNombre = conexion.getConexion().prepareStatement(consString);
                consultaNombre.setString(1, nombre);
                consultaNombre.setString(2, apellido);
                consultaNombre.setString(3, apellido2);
                consultaNombre.setBigDecimal(4, cedula);
                resultado = consultaNombre.executeQuery();
                while (resultado.next()) {
                    dato = resultado.getString("Cedula") + "-" + nombre + " " + apellido + " " + apellido2 + "\n";
                    listaPersonas.add(dato);
                }
                consultaNombre.close();
                resultado.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonas;
    }

    public Vector<String> consultarPersonasFSC(Date fechaDesde, Date fechaHasta, String genero, String cargoEn) {
        Vector<String> personas = new Vector();
        try {
            PreparedStatement consulta;
            ResultSet res = null;
            String dato = "";
            System.out.println("Fecha Desde: " + fechaDesde);
            System.out.println("Fecha Hasta: " + fechaHasta);

            consulta = conexion.getConexion().prepareStatement("SELECT cedula,nombre,apellido,apellido2 FROM " + "persona" + " WHERE " + "sexo " + " ILIKE ?" + " AND" + " cargo " + " ILIKE ?" + " AND" + " fechaasp BETWEEN " + " ? " + "AND  " + "? ");

            consulta.setString(1, genero);     //El primer "?" es  fechaDesde  
            consulta.setString(2, cargoEn);
            consulta.setDate(3, fechaDesde);     //El primer "?" es  fechaDesde    
            consulta.setDate(4, fechaHasta);     //El primer "?" es fechaHasta.    

            res = consulta.executeQuery();

            while (res.next()) {
                System.out.println("Entre");
                dato = res.getString("Cedula") + "-" + res.getString("Nombre") + " " + res.getString("Apellido") + " " + res.getString("Apellido2") + "\n";
                System.out.println(dato);
                personas.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personas;

    }

    public Vector<String> consultarPersonasFC(Date fechaDesde, Date fechaHasta, String cargoEn) {
        Vector<String> personas = new Vector();
        try {
            PreparedStatement consulta;
            ResultSet res = null;
            String dato = "";

            consulta = conexion.getConexion().prepareStatement("SELECT cedula,nombre,apellido,apellido2 FROM " + "persona" + " WHERE " + "cargo ILIKE " + "?" + " AND" + " fechaasp BETWEEN " + " ? " + "AND  " + "? ");

            consulta.setString(1, cargoEn);
            consulta.setDate(2, fechaDesde);     //El primer "?" es  fechaDesde    
            consulta.setDate(3, fechaHasta);     //El primer "?" es fechaHasta.    

            res = consulta.executeQuery();

            while (res.next()) {
                System.out.println("Entre");
                dato = res.getString("Cedula") + "-" + res.getString("Nombre") + " " + res.getString("Apellido") + " " + res.getString("Apellido2") + "\n";
                System.out.println(dato);
                personas.add(dato);
            }
            consulta.close();
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProyectoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personas;

    }

}
