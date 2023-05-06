/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import Vistas.ExportacionVista;
import Modelo.Exportacion;
import Modelo.Metodos;
import Modelo.Validacion;
import Modelo.usuario;
import Vistas.Login;
import Vistas.Registro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Luis Felipe Usma C
 */
public class controlador implements ActionListener {

    Metodos met = new Metodos();
    usuario x = new usuario();
    Exportacion ex = new Exportacion();
    Login log = new Login();
    Registro reg = new Registro();
    ExportacionVista exv = new ExportacionVista();
    DefaultTableModel modelo = new DefaultTableModel();
    Validacion validacion = new Validacion();

    public controlador(Login log, Registro reg, usuario user, ExportacionVista exv, Exportacion ex, DefaultTableModel modelo, Metodos met) {

        this.log = log;
        this.reg = reg;
        this.x = user;
        this.ex = ex;
        this.exv = exv;
        this.modelo = modelo;
        this.met = met;
        //Listar(exv.tabla);  // si deseo listar inmediatamente se inicie el formulario

        //Botones registro usuarios
        this.reg.BtnRegistrar.addActionListener(this);
        this.reg.BtnMenu.addActionListener(this);
        this.reg.BtnSalir.addActionListener(this);
        this.reg.BtnLimpiar.addActionListener(this);

        //Botones login  
        this.log.btnIngresar.addActionListener(this);
        this.log.BtnRegistro.addActionListener(this);
        this.log.BtnSalir.addActionListener(this);

        //Botones Vista exportación
        this.exv.BtnGuardar.addActionListener(this);
        this.exv.Btnsalir.addActionListener(this);
        this.exv.BtnListar.addActionListener(this);
        this.exv.BtnEditar.addActionListener(this);
        this.exv.BtnActualizar.addActionListener(this);
        this.exv.BtnEiminar.addActionListener(this);
        this.exv.BtnLimpiarF.addActionListener(this);
        this.exv.btnBuscar.addActionListener(this);

    }

    //Eventos
    @Override
    public void actionPerformed(ActionEvent e) {

        //Botones Vista exportación
        if (e.getSource() == exv.BtnGuardar) {

            try {
                agregar();
                limpiarFormulario();
                limpiarTabla();
                Listar(exv.tabla);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(exv, "no se pudo crear Expotación");
            }

        }

        if (e.getSource() == exv.Btnsalir) {

            log.setVisible(true);
            exv.setVisible(false);

        }
        if (e.getSource() == exv.BtnListar) {

            Listar(exv.tabla);
            limpiarTabla();
            Listar(exv.tabla);

        }
        if (e.getSource() == exv.BtnEditar) {
            mostrarExportacionParaEditar();
        }
        if (e.getSource() == exv.BtnActualizar) {

            actualizarExportacion();
            limpiarFormulario();
            limpiarTabla();
            Listar(exv.tabla);

        }
        if (e.getSource() == exv.BtnEiminar) {

            delete();
            limpiarFormulario();
            limpiarTabla();
            Listar(exv.tabla);

        }
        if (e.getSource() == exv.BtnLimpiarF) {

            limpiarFormulario();
            limpiarTabla();
            Listar(exv.tabla);

        }

        if (e.getSource() == exv.btnBuscar) {

            buscarDatos();
        }

        // Botones registrar usurio
        if (e.getSource() == reg.BtnRegistrar) {

            Registrar();
            limpiarFormulario();

        }

        if (e.getSource() == reg.BtnSalir) {
            System.exit(0);

        }
        if (e.getSource() == reg.BtnMenu) {
            log.setVisible(true);
            reg.setVisible(false);

        }
        if (e.getSource() == reg.BtnLimpiar) {

            limpiarFormulario();

        }

        //Botones login  
        if (e.getSource() == log.btnIngresar) {
            Ingreso();
            limpiarFormulario();

        }
        if (e.getSource() == log.BtnRegistro) {

            reg.setVisible(true);
            log.setVisible(false);

        }

        if (e.getSource() == log.BtnSalir) {

            System.exit(0);

        }

    }

    public void iniciar() {
        log.setTitle("iniciar sesión ");
        log.pack();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setLocationRelativeTo(null);
        log.setVisible(true);

    }

    public void Listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Exportacion> lista = met.Listar();
        Object[] object = new Object[11];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombrePro();
            object[2] = lista.get(i).getPresentacionPro();
            object[3] = lista.get(i).getCantidadPro();
            object[4] = lista.get(i).getTipoEnvio();
            object[5] = lista.get(i).getPaisDestino();
            object[6] = lista.get(i).getCiudadOrigen();
            object[7] = lista.get(i).getCiudadDestino();
            object[8] = lista.get(i).getMonedaPago();
            object[9] = lista.get(i).getNombreEmpleado();
            object[10] = lista.get(i).getNombreFuncionario();
            modelo.addRow(object);

        }
        exv.tabla.setModel(modelo);

    }

    public void agregar() {

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
            ex.setNombrePro(NombrePro);
            ex.setPresentacionPro(PresentacionPro);
            ex.setCantidadPro(CantidadPro);
            ex.setTipoEnvio(TipoEnvio);
            ex.setPaisDestino(PaisDestino);
            ex.setCiudadOrigen(CiudadOrigen);
            ex.setCiudadDestino(CiudadDestino);
            ex.setMonedaPago(MonedaPago);
            ex.setNombreEmpleado(NombreEmpleado);
            ex.setNombreFuncionario(NombreFuncionario);

            int r = met.agregar(ex);

            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Exportación creada con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear exportación");
            }
        }
    }

    public void actualizarExportacion() {
        java.sql.Connection cn = Conexion.Conectar();

        // Verificar si se ha seleccionado una fila
        int filaSeleccionada = exv.tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila para actualizar");
            return;
        }

        try {
            int id = Integer.parseInt(exv.tabla.getValueAt(filaSeleccionada, 0).toString());
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

            if (exv.tabla.getValueAt(exv.tabla.getSelectedRow(), 0) == null
                    || exv.TxtNombreP.getText().isEmpty()
                    || exv.TxtPresentacionProducto.getSelectedItem() == null
                    || exv.TxtCantidad.getText().isEmpty()
                    || exv.TxtEnvio.getSelectedItem() == null
                    || exv.TxtPaises.getSelectedItem() == null
                    || exv.TxtCuidadOrigen.getText().isEmpty()
                    || exv.TxtCiudadDestino.getText().isEmpty()
                    || exv.TxtMonedaPago.getSelectedItem() == null
                    || exv.TxtNombreEmp.getText().isEmpty()
                    || exv.TxtNombreFunci.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar todos los datos");
                return;

            } else {
                PreparedStatement ps = cn.prepareStatement("UPDATE exportacion SET "
                        + "NombrePro=?, "
                        + "PresentacionPro=?, "
                        + "CantidadPro=?, "
                        + "TipoEnvio=?, "
                        + "CiudadOrigen=?, "
                        + "CiudadDestino=?, "
                        + "PaisDestino=?, "
                        + "MonedaPago=?, "
                        + "NombreEmpleado=?, "
                        + "NombreFuncionario=? "
                        + "WHERE id=?");

                ps.setString(1, NombrePro);
                ps.setString(2, PresentacionPro);
                ps.setString(3, CantidadPro);
                ps.setString(4, TipoEnvio);
                ps.setString(5, CiudadOrigen);
                ps.setString(6, CiudadDestino);
                ps.setString(7, PaisDestino);
                ps.setString(8, MonedaPago);
                ps.setString(9, NombreEmpleado);
                ps.setString(10, NombreFuncionario);
                ps.setInt(11, id);

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Exportación actualizada con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la exportación");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // Método para obtener los datos de la fila seleccionada y mostrarlos en el formulario
    public void mostrarExportacionParaEditar() {
        int fila = exv.tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(exv, "Debe seleccionar una expotación");
        } else {
            int id = Integer.parseInt(exv.tabla.getValueAt(fila, 0).toString());
            String NombrePro = (String) exv.tabla.getValueAt(fila, 1);
            String PresentacionPro = (String) exv.tabla.getValueAt(fila, 2).toString();
            String CantidadPro = (String) exv.tabla.getValueAt(fila, 3).toString();
            String TipoEnvio = (String) exv.tabla.getValueAt(fila, 4).toString();
            String PaisDestino = (String) exv.tabla.getValueAt(fila, 5).toString();
            String CiudadOrigen = (String) exv.tabla.getValueAt(fila, 6).toString();
            String CiudadDestino = (String) exv.tabla.getValueAt(fila, 7).toString();
            String MonedaPago = (String) exv.tabla.getValueAt(fila, 8).toString();
            String NombreEmpleado = (String) exv.tabla.getValueAt(fila, 9).toString();
            String NombreFuncionario = (String) exv.tabla.getValueAt(fila, 10).toString();

            exv.TxtId.setText("" + id);
            exv.TxtNombreP.setText(NombrePro);
            exv.TxtPresentacionProducto.setSelectedItem(PresentacionPro);
            exv.TxtCantidad.setText(CantidadPro);
            exv.TxtEnvio.setSelectedItem(TipoEnvio);
            exv.TxtPaises.setSelectedItem(PaisDestino);
            exv.TxtCuidadOrigen.setText(CiudadOrigen);
            exv.TxtCiudadDestino.setText(CiudadDestino);
            exv.TxtMonedaPago.setSelectedItem(MonedaPago);
            exv.TxtNombreEmp.setText(NombreEmpleado);
            exv.TxtNombreFunci.setText(NombreFuncionario);

            // Deshabilitar el botón "Guardar" y habilitar el botón "Actualizar"
            exv.BtnGuardar.setEnabled(false);
            exv.BtnActualizar.setEnabled(true);

        }
    }

   
    public void Registrar() {

        String nombre = reg.TxtNombres.getText();
        String apellidos = reg.TxtApellidos.getText();
        String correo = reg.txtCorreo.getText();
        String usuario = reg.TxtUsuario.getText();
        String contrasena = reg.TxtContrasena.getText();

        boolean camposValidos = true;

        if (nombre.equals("") || apellidos.equals("") || correo.equals("") || usuario.equals("") || contrasena.equals("")) {

            JOptionPane.showMessageDialog(null, "Debe completar todos los datos");
            camposValidos = false;

        }

        if (camposValidos && !validacion.validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido", "Error de validación", JOptionPane.ERROR_MESSAGE);
            camposValidos = false;
        }

        if (camposValidos) {
            x.setNombres(reg.TxtNombres.getText());
            x.setApellidos(reg.TxtApellidos.getText());
            x.setCorreo(reg.txtCorreo.getText());
            x.setUsuario(reg.TxtUsuario.getText());
            x.setContrasena(reg.TxtContrasena.getText());

            if (met.InsertarUsuario(x)) {

                JOptionPane.showMessageDialog(null, "Usuario registrado");

            } else {
                JOptionPane.showMessageDialog(null, "Usuario no registrado");
            }
        }

    }

    public void Ingreso() {

        String us = log.TxtUsuario.getText();
        String pw = log.TxtContrasena.getText();

        if (met.Autentificacion(us, pw)) {

            exv.setVisible(true);
            log.setVisible(false);

            JOptionPane.showMessageDialog(null, "Ingreso correcto");

        } else {

            JOptionPane.showMessageDialog(null, "Usuario no registrado");
        }

    }

    void limpiarTabla() {

        for (int i = 0; i < exv.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;

        }

    }

    void limpiarFormulario() {

        exv.TxtId.setText("");
        exv.TxtNombreP.setText("");
        exv.TxtPresentacionProducto.setSelectedItem("Seleccione");
        exv.TxtCantidad.setText("");
        exv.TxtEnvio.setSelectedItem("Seleccione");
        exv.TxtPaises.setSelectedItem("Seleccione");
        exv.TxtCuidadOrigen.setText("");
        exv.TxtCiudadDestino.setText("");
        exv.TxtMonedaPago.setSelectedItem("Seleccione");
        exv.TxtNombreEmp.setText("");
        exv.TxtNombreFunci.setText("");
        exv.txtBuscar.setText("");

        reg.TxtNombres.setText("");
        reg.TxtApellidos.setText("");
        reg.txtCorreo.setText("");
        reg.TxtUsuario.setText("");
        reg.TxtContrasena.setText("");
        
        log.TxtUsuario.setText("");
        log.TxtContrasena.setText("");

    }

    public void delete() {
        int fila = exv.tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(exv, "Debe seleccionar una expotación");

        } else {
            int id = Integer.parseInt(exv.tabla.getValueAt(exv.tabla.getSelectedRow(), 0).toString());
            met.delete(id);
            JOptionPane.showMessageDialog(exv, "Expotación eliminada");

        }
    }

    public void keyReleased(KeyEvent e) throws SQLException {
        buscarDatos();
    }

    public void buscarDatos() {
        String textoBusqueda = exv.txtBuscar.getText();
        java.sql.Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM exportacion WHERE id LIKE ? OR NombrePro LIKE ? OR PresentacionPro LIKE ? OR CantidadPro LIKE ? OR TipoEnvio LIKE ? OR CiudadOrigen LIKE ? OR CiudadDestino LIKE ? OR PaisDestino LIKE ? OR MonedaPago LIKE ? OR NombreEmpleado LIKE ? OR NombreFuncionario LIKE ?";

        try {
            cn = Conexion.Conectar();
            ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + textoBusqueda + "%");
            ps.setString(2, "%" + textoBusqueda + "%");
            ps.setString(3, "%" + textoBusqueda + "%");
            ps.setString(4, "%" + textoBusqueda + "%");
            ps.setString(5, "%" + textoBusqueda + "%");
            ps.setString(6, "%" + textoBusqueda + "%");
            ps.setString(7, "%" + textoBusqueda + "%");
            ps.setString(8, "%" + textoBusqueda + "%");
            ps.setString(9, "%" + textoBusqueda + "%");
            ps.setString(10, "%" + textoBusqueda + "%");
            ps.setString(11, "%" + textoBusqueda + "%");

            rs = ps.executeQuery();

            DefaultTableModel modelo = (DefaultTableModel) exv.tabla.getModel();
            modelo.setRowCount(0);

            while (rs.next()) {
                Object[] fila = new Object[11];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("NombrePro");
                fila[2] = rs.getString("PresentacionPro");
                fila[3] = rs.getString("CantidadPro");
                fila[4] = rs.getString("TipoEnvio");
                fila[5] = rs.getString("PaisDestino");
                fila[6] = rs.getString("CiudadOrigen");
                fila[7] = rs.getString("CiudadDestino");
                fila[8] = rs.getString("MonedaPago");
                fila[9] = rs.getString("NombreEmpleado");
                fila[10] = rs.getString("NombreFuncionario");

                modelo.addRow(fila);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar los datos: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

}
