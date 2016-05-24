/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobd;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Clase que permite conectar con la base de datos
 *
 *
 */
public class ConexionBD {

    /**
     * Parametros de conexion
     */
    String bd = "";
    String login = "";
    String password = "";
    String url = "jdbc:postgresql://localhost:5432/";

    Connection connection = null;
    boolean ok;

    /**
     * Constructor de DbConnection
     */
    public int mostrarDialogo() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JPasswordField field3 = new JPasswordField();

        Object[] message = {
            "Ingrese el nombre de la base de datos:", field1,
            "Ingrese el usuario:", field2,
            "Ingrese la contraseña de la base de datos:", field3,};
        int option = JOptionPane.showConfirmDialog(null, message, "Conectando a la base de datos...", JOptionPane.OK_CANCEL_OPTION);
        bd = field1.getText();
        login = field2.getText();
        password = String.valueOf(field3.getPassword());
        return option;
    }

    public ConexionBD() {
        ok = false;
        int resultado = mostrarDialogo();
        url += bd;
        if (resultado == JOptionPane.OK_OPTION) {

            try {
                //obtenemos el driver de para mysql
                Class.forName("org.postgresql.Driver");
                //obtenemos la conexión
                connection = DriverManager.getConnection(url, login, password);

                if (connection != null) {
                    ok = true;
                }

            } catch (SQLException e) {

                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
                System.exit(1);

            } catch (ClassNotFoundException e) {
                System.out.println(e);

            } catch (Exception e) {
                System.out.println(e);

            }
        } else {

            System.exit(0);
        }
    }

    /**
     * Permite retornar la conexión
     *
     * @return n
     */
    public Connection getConexion() {
        return connection;
    }

    public void desconectar() {
        connection = null;
    }

    public boolean getEstado() {
        return ok;
    }

}
