/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vistas.ExportacionVista;
import Modelo.Exportacion;
import Modelo.Metodos;
import Modelo.usuario;
import Vistas.Login;
import Vistas.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        met.actualizarExportacion(ex);
    }

    /*
public void actualizar() {
    int fila = exv.tabla.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar una exportación");
    } else {
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
            int id = Integer.parseInt(exv.tabla.getValueAt(fila, 0).toString());
            ex.setId(id);
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
            
                    }
    }
}
      public void actualizar() {

        int id = Integer.parseInt(exv.TxtId.getText());
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
        ex.setId(id);
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
        int r = met.Actualizar();
        
        if (r == 1) {
            JOptionPane.showMessageDialog(exv, "Exportación acutiliza con exito");
        } else {
            JOptionPane.showMessageDialog(exv, "No se pudo actualizar exportación");
        }

    }*/
    public void Registrar() {

        String nombre = reg.TxtNombres.getText();
        String apellidos = reg.TxtApellidos.getText();
        String correo = reg.txtCorreo.getText();
        String usuario = reg.TxtUsuario.getText();
        String contrasena = reg.TxtContrasena.getText();

        if (nombre.equals("") || apellidos.equals("") || correo.equals("") || usuario.equals("") || contrasena.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe completar todos los datos");

        } else {
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

        reg.TxtNombres.setText("");
        reg.TxtApellidos.setText("");
        reg.txtCorreo.setText("");
        reg.TxtUsuario.setText("");
        reg.TxtContrasena.setText("");

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

    //Eventos
    @Override
    public void actionPerformed(ActionEvent e) {

        //Botones Vista exportación
        if (e.getSource() == exv.BtnGuardar) {

            agregar();
            limpiarFormulario();
            limpiarTabla();
            Listar(exv.tabla);

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

            int fila = exv.tabla.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(exv, "Debe seleccionar una expotación");
            } else {

                String NombrePro = (String) exv.tabla.getValueAt(fila, 1);
                int id = Integer.parseInt(exv.tabla.getValueAt(exv.tabla.getSelectedRow(), 0).toString());
                String PresentacionPro = (String) exv.tabla.getValueAt(fila, 2).toString();
                String CantidadPro = (String) exv.tabla.getValueAt(fila, 3);
                String TipoEnvio = (String) exv.tabla.getValueAt(fila, 4).toString();
                String PaisDestino = (String) exv.tabla.getValueAt(fila, 5).toString();
                String CiudadOrigen = (String) exv.tabla.getValueAt(fila, 6);
                String CiudadDestino = (String) exv.tabla.getValueAt(fila, 7);
                String MonedaPago = (String) (String) exv.tabla.getValueAt(fila, 8).toString();
                String NombreEmpleado = (String) exv.tabla.getValueAt(fila, 9);
                String NombreFuncionario = (String) exv.tabla.getValueAt(fila, 10);

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

            }

        }
        if (e.getSource() == exv.BtnActualizar) {

            try {
                actualizarExportacion();
                limpiarFormulario();
                limpiarTabla();
                Listar(exv.tabla);
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "No se pudo actualizar exportación");
            }

        }
        if (e.getSource() == exv.BtnEiminar) {

            delete();
            limpiarFormulario();
            limpiarTabla();
            Listar(exv.tabla);

        }
        if (e.getSource() == exv.BtnLimpiarF) {

            limpiarFormulario();
            Listar(exv.tabla);

        }

        // Botones registrar usurio
        if (e.getSource() == reg.BtnRegistrar) {

            Registrar();

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

        }
        if (e.getSource() == log.BtnRegistro) {

            reg.setVisible(true);
            log.setVisible(false);

        }

        if (e.getSource() == log.BtnSalir) {

            System.exit(0);

        }

    }
}
