/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.*;
import Modelo.*;
import Vistas.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


 */
/**
 *
 * @author Luis Felipe Usma C
 */
public class Metodos {

    usuario x = new usuario();
    Login log = new Login();
    Registro reg = new Registro();
    ExportacionVista exv = new ExportacionVista();

    public static boolean InsertarUsuario(usuario x) {

        Connection cn = Conexion.Conectar();
        PreparedStatement ps = null;

        String sql = "insert into usuario(nombres,apellidos,correo,usuario,contrasena) VALUES (?,?,?,?,?)";

        try {

            ps = cn.prepareStatement(sql);
            ps.setString(1, x.getNombres());
            ps.setString(2, x.getApellidos());
            ps.setString(3, x.getCorreo());
            ps.setString(4, x.getUsuario());
            ps.setString(5, x.getContrasena());
            ps.execute();
            cn.close();

            return true;

        } catch (Exception e) {

            System.out.println(e);
        }

        return false;
    }

    public static boolean Autentificacion(String PUsuario, String PContrasena) {

        Connection cn = Conexion.Conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select usuario, contrasena from usuario where usuario=? and contrasena=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, PUsuario);
            ps.setString(2, PContrasena);
            rs = ps.executeQuery();

            while (rs.next()) {
                cn.close();
                return true;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

//exportación
    public int agregar(Exportacion ex) {

        PreparedStatement ps = null;

        String sql = "insert into exportacion(NombrePro, PresentacionPro, CantidadPro, TipoEnvio, CiudadOrigen, CiudadDestino, PaisDestino, MonedaPago,  NombreEmpleado, NombreFuncionario) values(?,?,?,?,?,?,?,?,?,?)";

        try {

            Connection cn = Conexion.Conectar();
            ps = cn.prepareStatement(sql);

            //ps.setInt(1, ex.getId());
            ps.setString(1, ex.getNombrePro());
            ps.setString(2, ex.getPresentacionPro());
            ps.setString(3, ex.getCantidadPro());
            ps.setString(4, ex.getTipoEnvio());
            ps.setString(5, ex.getCiudadOrigen());
            ps.setString(6, ex.getCiudadDestino());
            ps.setString(7, ex.getPaisDestino());
            ps.setString(8, ex.getMonedaPago());
            ps.setString(9, ex.getNombreEmpleado());
            ps.setString(10, ex.getNombreFuncionario());

            ps.executeUpdate();
            cn.close();

        } catch (SQLException e) {

            System.out.println(e);
        }

        return 1;
    }
    
    


    public void Actualizar() {
        Connection cn = Conexion.Conectar();

        try {
            int id = Integer.parseInt(exv.tabla.getValueAt(exv.tabla.getSelectedRow(), 0).toString());
            String NombrePro = exv.TxtNombreP.getText();
            String PresentacionPro = exv.TxtPresentacionProducto.getSelectedItem().toString();
            String CantidadPro = exv.TxtCantidad.getText();
            String TipoEnvio = exv.TxtEnvio.getSelectedItem().toString();
            String PaisDestino = exv.TxtPaises.getSelectedItem().toString();
            String CiudadOrigen = exv.TxtCuidadOrigen.getText();
            String CiudadDestino = exv.TxtCiudadDestino.getText();
            String MonedaPago = exv.TxtMonedaPago.getSelectedItem().toString();
            String NombreEmpleado = exv.TxtNombreEmp.getText();
            String NombreFuncionario = exv.TxtNombreFunci.getText();

            if (NombrePro.equals("") || PresentacionPro.equals("") || CantidadPro.equals("") || TipoEnvio.equals("") || PaisDestino.equals("") || CiudadOrigen.equals("") || CiudadDestino.equals("") || MonedaPago.equals("") || NombreEmpleado.equals("") || NombreFuncionario.equals("")) {

                JOptionPane.showMessageDialog(null, "Debe completar todos los datos");

            } else {
                

                PreparedStatement ps = cn.prepareStatement("UPDATE exportacion SET "
                        
                        + "NombrePro='" + NombrePro + "', "
                        + "PresentacionPro='" + PresentacionPro + "', "
                        + "CantidadPro='" + CantidadPro + "', "
                        + "TipoEnvio='" + TipoEnvio + "', "
                        + "CiudadOrigen='" + CiudadOrigen + "', "
                        + "CiudadDestino='" + CiudadDestino + "', "
                        + "PaisDestino='" + PaisDestino + "', "
                        + "MonedaPago='" + MonedaPago + "', "
                        + "NombreEmpleado='" + NombreEmpleado + "', "
                        + "NombreFuncionario='" + NombreFuncionario + "' "
                        + "WHERE id='" + id + "'");

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Exportación actualizada con éxito");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error  " + ex);
        }
    }
    
/*
    PreparedStatement ps = null;
        int r = 0;

        String sql = "update exportacion set NombrePro=?, PresentacionPro=?, CantidadPro=?, TipoEnvio=?, CiudadOrigen=?, CiudadDestino=?, PaisDestino=?, MonedaPago=?,  NombreEmpleado=?, NombreFuncionario=?";

        try {
            Connection cn = Conexion.Conectar();
            ps = cn.prepareStatement(sql);
            ps.setString(1, ex.getNombrePro());
            ps.setString(2, ex.getPresentacionPro());
            ps.setString(3, ex.getCantidadPro());
            ps.setString(4, ex.getTipoEnvio());
            ps.setString(5, ex.getCiudadOrigen());
            ps.setString(6, ex.getCiudadDestino());
            ps.setString(7, ex.getPaisDestino());
            ps.setString(8, ex.getMonedaPago());
            ps.setString(9, ex.getNombreEmpleado());
            ps.setString(10, ex.getNombreFuncionario());
            ps.setInt(11, ex.getId());

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;

            } else {
                return 0;
            }

        } catch (SQLException e) {
        }
        return r;
    //}

*/
    
    
    public List Listar() {

        Connection cn = Conexion.Conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Exportacion> datos = new ArrayList<>();
        String sql = "select * from exportacion";

        try {
            cn = Conexion.Conectar();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Exportacion ex = new Exportacion();
                ex.setId(rs.getInt(1));
                ex.setNombrePro(rs.getString(2));
                ex.setPresentacionPro(rs.getString(3));
                ex.setCantidadPro(rs.getString(4));
                ex.setTipoEnvio(rs.getString(5));
                ex.setCiudadOrigen(rs.getString(6));
                ex.setCiudadDestino(rs.getString(7));
                ex.setPaisDestino(rs.getString(8));
                ex.setMonedaPago(rs.getString(9));
                ex.setNombreEmpleado(rs.getString(10));
                ex.setNombreFuncionario(rs.getString(11));

                datos.add(ex);

            }

        } catch (Exception e) {
        }
        return datos;
    }

  


    public void delete(int id) {
        Connection cn = Conexion.Conectar();
        PreparedStatement ps = null;

        String sql = " delete from exportacion where id='" + id + "'";

        try {
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
        }

    }

}