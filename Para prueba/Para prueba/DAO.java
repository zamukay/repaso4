package Interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Samu
 */
public class DAO {

    public void insertEstudentDAO(Estudiante est) {
        try {
            //1er, 2do Paso
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "insert into estudiantes values(?,?,?,?,?)";
            //3er Paso, Statemnt preparando
            PreparedStatement psd = cc.prepareStatement(sql);
            //insertar el primer valor Hay string int etc   Comienza desde el 1 por que es de la BD
            psd.setString(1, est.getEstCedula());
            psd.setString(2, est.getEstNombre());
            psd.setString(3, est.getEstApellido());
            psd.setString(4, est.getEstDir());
            psd.setString(5, est.getEstTel());
            //4to Paso
            int res = psd.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "1 Fila insertada");
                //Ahora para que se actualice la tabla para que me aparezca lo que pusimos
                //selectEstudent();
                //limpiar();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente, comuniquese con el admin");
        }

    }

    public List<Estudiante> selectEstudentDAO() {
        try {
            List<Estudiante> lista = new ArrayList();
            //Conecta a la BD
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();
            String sql = "select * from estudiantes";

            Statement psd = cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                /*registros[0] = rs.getString("estCedula");
                registros[1] = rs.getString("estNombre");
                registros[2] = rs.getString("estApellido");
                registros[3] = rs.getString("estDireccion");
                registros[4] = rs.getString("estTelefono");
                //Agregar al modelo ahora
                model.addRow(registros);
                 */
                Estudiante est = new Estudiante(
                        rs.getString("estCedula"),
                        rs.getString("estNombre"),
                        rs.getString("estApellido"),
                        rs.getString("estDireccion"),
                        rs.getString("estTelefono"));
                lista.add(est);
            }
            return lista;
            //Ahora ira a la tabla -->
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public void deleteStudentDAO(String cedula) {
        try {
            //1 y 2
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();                      // las ' es por que es un String
            String sql = "delete from estudiantes where estCedula ='" + cedula + "'";
            //3
            PreparedStatement psd = cc.prepareStatement(sql);
            //4  Update es por que altera la table y eso va en delete
            int res = psd.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "1 Fila borrada");
                //Ahora para que se actualice la tabla para que me aparezca lo que pusimos
                //selectEstudent();
                //limpiar();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se borró correctamente, comuniquese con el admin");
        }

    }

    public void updateStudentDAO(Estudiante est) {
        try {
            Conexion cn = new Conexion();
            Connection cc = cn.conectar();

            String sql = "UPDATE estudiantes SET "
                    + "estNombre='" + est.getEstNombre() + "', "
                    + "estApellido='" + est.getEstApellido() + "', "
                    + "estDireccion='" + est.getEstDir() + "', "
                    + "estTelefono='" + est.getEstTel() + "' "
                    + "WHERE estCedula='" + est.getEstCedula() + "'";
            PreparedStatement psd = cc.prepareStatement(sql);
            int res = psd.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Actualizado Correctamente");
                //selectEstudent();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se actualizó correctamente");
        }
    }
}
