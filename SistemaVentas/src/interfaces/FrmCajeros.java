/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FrmCajeros extends javax.swing.JFrame {

    DefaultTableModel modelo;
    Utilidades md5 = new Utilidades();

    public FrmCajeros() {
        initComponents();
//        jpfPasswd.setEchoChar('☻');
//        cargartablausuarios("");
//        bloqueartxt();
//        botoneslimpios();
//        cargarDatos();
    }

    private String getCed(String ced) {
        System.out.println("CED: " + ced);
        ced = ced.replace('-', ' ').replaceAll(" ", "");
        System.out.println("ced1: " + ced);
        return ced;
    }


    private void mostrarPasswd() {
        //String ver=jpfPasswd.getText();
        if (jchbxVer.isSelected()) {
            jpfPasswd.setEchoChar((char) 0);
        } else {
            jpfPasswd.setEchoChar('☻');

        }
    }



    
  
//    private void cargarDatos() {
//        jtblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (jtblUsers.getSelectedRow() != -1) {
//                    int fila = jtblUsers.getSelectedRow();
//                    jtxtCedula.setText(jtblUsers.getValueAt(fila, 0).toString().trim());
//                    String c = (jtblUsers.getValueAt(fila, 0).toString().trim());
//                    cargarclave(c);
//                    cbperfil.setSelectedItem(jtblUsers.getValueAt(fila, 1).toString().trim());
//                    txtcontacto.setText(jtblUsers.getValueAt(fila, 2).toString().trim());
//                    txtobservacion.setText(jtblUsers.getValueAt(fila, 3).toString().trim());
//                    desbloqueartxt();
//                    jtxtCedula.setEnabled(false);
//                    btnmodificar.setEnabled(true);
//                    btneliminar.setEnabled(true);
//                    btncancelar.setEnabled(true);
//                    jchbxVer.setEnabled(true);
//                }
//            }
//        });
//    }

    private void botonnuevo() {
        btnnuevo.setEnabled(false);
        btnguardar.setEnabled(true);
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        jchbxVer.setEnabled(true);
        btncancelar.setEnabled(true);
        btnsalir.setEnabled(true);
        jtxtCedula.requestFocus();
    }

    private void botoneslimpios() {
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btnmodificar.setEnabled(false);
        jchbxVer.setEnabled(false);
        btneliminar.setEnabled(false);
        btncancelar.setEnabled(false);
        btnsalir.setEnabled(true);
    }
//
//    public void borrar() {
//        if (JOptionPane.showConfirmDialog(null, "SEGURO QUE QUIERE BORRAR", "BORRAR REGISTRO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            Conexion cc = new Conexion();
//            Connection cn = cc.getConexion();
//            String ced = jtxtCedula.getText();
//            String ced1 = getCed(ced);
//            String codigo = ced1;
//            String sql = " ";
//            sql = "delete from usuarios WHERE usu_cedula ='" + codigo + "'";
//            try {
//                PreparedStatement psd = cn.prepareStatement(sql);
//                int i = psd.executeUpdate();
//                if (i > 0) {
//                    JOptionPane.showMessageDialog(null, "REGISTRO BORRADO");
//                    limpiartxt();
//                    cargartablausuarios("");
//
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//    }

//    public void cargarclave(String cedu) {
//
//        try {
//            conexion cc = new conexion();
//            Connection cn = cc.getConexion();
//            String sql = " ";
//
//
//            sql = "select * from usuarios where usu_cedula='" + cedu + "'";
//            Statement psd = cn.createStatement();
//            ResultSet rs = psd.executeQuery(sql);
//            while (rs.next()) {
//                jpfPasswd.setText(rs.getString("usu_clave"));
//                String desencriptado = md5.Desencriptar(jpfPasswd.getText());
//                jpfPasswd.setText(desencriptado);
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "NO HAY PROV");
//        }
//    }
 
    private boolean verifCedula(String ced) {
        boolean verifica = false;
        int n = ced.length();
        int sumPar = 0, sumaImpar = 0, rpar, rimpar, sumTotal, deceSup, nVerif;
        String convn;
        if (n < 10 || n > 10) {
            verifica = false;
            JOptionPane.showMessageDialog(null, "La cédula debe tener 10 digitos");
            System.out.println("cedula incorrecta");
        } else {
            String a = String.valueOf(ced.charAt(9));
            int nVerificador = Integer.valueOf(a);
        System.out.println("numero verificador: "+nVerificador);
            for (int i = 0; i < 10; i += 2) {
                convn = String.valueOf(ced.charAt(i));
                System.out.println("numero: "+convn);
                rpar = Integer.valueOf(convn) * 2;
                System.out.println("rpar*2: "+rpar);
                if (rpar >= 10) {
                    rpar = rpar - 9;
                }
                sumPar += rpar;
            }
            System.out.println("suma par: "+sumPar);
            for (int i = 1; i < 9; i += 2) {
                convn = String.valueOf(ced.charAt(i));
                System.out.println("numero: "+convn);
                rimpar = Integer.valueOf(convn);
                sumaImpar += rimpar;
            }
            System.out.println("suma impar: "+sumaImpar);
            sumTotal = sumPar + sumaImpar;
            System.out.println("suma total: "+sumTotal);
            deceSup = ((int) sumTotal / 10) * 10 + 10;
            nVerif = deceSup - sumTotal;
            System.out.println("num v: " + nVerif);
            System.out.println(deceSup);
            if (nVerif == nVerificador || nVerif == 10) {
                verifica = true;
                JOptionPane.showMessageDialog(null, "cedula correcta");
            }
        }
        if (!verifica) {
            JOptionPane.showMessageDialog(null, "Cedula incorrecta, Ingrese nueamente");
        }
                System.out.println("cedula es; " + verifica);
        return verifica;
    }

//    public void cargartablausuarios(String Dato) {
//        String titulos[] = {"USUARIO", "PERFIL", "CONTACTO", "OBSERVACION"};
//        String registros[] = new String[4];
//        modelo = new DefaultTableModel(null, titulos);
//
//        Conexion cc = new Conexion();
//        Connection cn = cc.getConexion();
//        String sql = "";
//        sql = "select * from usuarios  where USU_CEDULA like '%" + Dato + "%' order by USU_CEDULA";
//         sql="select * from auto where pla_aut = '"+Dato+"'";
//        try {
//            Statement psd = cn.createStatement();
//            ResultSet rs = psd.executeQuery(sql);
//            while (rs.next()) {
//                registros[0] = rs.getString("USU_CEDULA");
//                registros[1] = rs.getString("USU_PERFIL");
//                registros[2] = rs.getString("USU_CONTACTO");
//                registros[3] = rs.getString("USU_OBSERVACION");
//
//
//                modelo.addRow(registros);
//            }
//            jtblUsers.setModel(modelo);
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//    }
      public void desbloqueartxt() {
        jtxtCedula.setEnabled(true);
        jpfPasswd.setEnabled(true);
        cbperfil.setEnabled(true);
        txtcontacto.setEnabled(true);
        txtobservacion.setEnabled(true);
    }

    public void bloqueartxt() {
        jtxtCedula.setEnabled(false);
        jpfPasswd.setEnabled(false);
        cbperfil.setEnabled(false);
        txtcontacto.setEnabled(false);
        txtobservacion.setEnabled(false);
    }

    public void limpiartxt() {
        jtxtCedula.setText("");
        jpfPasswd.setText("");
        cbperfil.setSelectedItem("SELECCIONE");
        txtcontacto.setText("");
        jchbxVer.setSelected(false);
        txtobservacion.setText("");
    }

    private String getPasswd(JPasswordField pswd) {
        String pass = "";
        char[] passwd = pswd.getPassword();
        for (int i = 0; i < passwd.length; i++) {
            pass += passwd[i];
        }
        return pass;
    }
//
//    private void guardar() {
//        if (jtxtCedula.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar la cedula");
//            jtxtCedula.requestFocus();
//        } else if (jpfPasswd.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar la contraseña");
//            jpfPasswd.requestFocus();
//        } else if (cbperfil.getSelectedItem() == "SELECCIONE") {
//            JOptionPane.showMessageDialog(null, "Debe seleccionar el perfil de usuario");
//            cbperfil.requestFocus();
//        } else if (txtcontacto.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar el conrtacto");
//            txtcontacto.requestFocus();
//        } else {
//
//            Conexion cc = new Conexion();
//            Connection cn = cc.getConexion();
//            String sql = "";
//            String USU_CEDULA, USU_CLAVE, USU_PERFIL, USU_CONTACTO, USU_OBSERBACION;
//
//            String ced = jtxtCedula.getText().trim();
//            String ced1 = getCed(ced);
//            USU_CEDULA = ced1;
//            String pass = getPasswd(jpfPasswd);
//            System.out.println("clave sin encr: " + pass);
//            USU_CLAVE = md5.Encriptar(pass);
//            USU_PERFIL = String.valueOf(cbperfil.getSelectedItem());
//            USU_CONTACTO = txtcontacto.getText().trim();
//            if (txtobservacion.getText().isEmpty()) {
//                USU_OBSERBACION = "NINGUNA";
//            } else {
//                USU_OBSERBACION = txtobservacion.getText();
//            }
//            sql = "insert into usuarios VALUES(?,?,?,?,?)";
//            try {
//                PreparedStatement psd = cn.prepareStatement(sql);
//                psd.setString(1, USU_CEDULA);
//                psd.setString(2, USU_CLAVE);
//                psd.setString(3, USU_PERFIL);
//                psd.setString(4, USU_CONTACTO);
//                psd.setString(5, USU_OBSERBACION);
//
//                int n = psd.executeUpdate();
//                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, "Se inserto crrectamente");
//                    limpiartxt();
//                    cargartablausuarios("");
//                }
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error al guardad");
//            }
//        }
//    }

//    private void botonactualizar() {
//        String passwd, pass, encry;
//        pass = getPasswd(jpfPasswd);
//        encry = md5.Encriptar(pass);
//        String ced = jtxtCedula.getText().trim();
//        String ced1 = getCed(ced);
//        if (pass.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Ingrese contaseña");
//            jpfPasswd.requestFocus();
//        }
//        if (cbperfil.getSelectedItem().toString().equals("SELECCIONE")) {
//            JOptionPane.showMessageDialog(null, "Seleccione PERFIL");
//            cbperfil.requestFocus();
//        } else {
//            Conexion cc = new Conexion();
//            Connection cn = cc.getConexion();
//            String sql = "";
//            sql = "update usuarios "
//                    + "set usu_clave='" + encry
//                    + "',usu_perfil='" + cbperfil.getSelectedItem()
//                    + "',usu_contacto='" + txtcontacto.getText().trim()
//                    + "',usu_observacion='" + txtobservacion.getText()
//                    + "' where usu_cedula='" + ced1 + "'";
//
//            try {
//                PreparedStatement psd = cn.prepareStatement(sql);
//                int n = psd.executeUpdate();
//                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO");
//                    limpiartxt();
//                    bloqueartxt();
//                    cargartablausuarios("");
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error al actualizar registro, verifique.");
//            }
//        }
//    }  
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jchbxVer = new javax.swing.JCheckBox();
        jpfPasswd = new javax.swing.JPasswordField();
        txtobservacion = new javax.swing.JTextField();
        cbperfil = new javax.swing.JComboBox();
        jtxtCedula = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUsers = new javax.swing.JTable();
        txtcontacto = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("USUARIOS");

        jLabel1.setText("CEDULA");

        jLabel2.setText("CONTRASEÑA");

        jLabel3.setText("PERFIL");

        jLabel4.setText("CONTACTO");

        jLabel5.setText("OBSERVACION");

        jchbxVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchbxVerActionPerformed(evt);
            }
        });

        jpfPasswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpfPasswdActionPerformed(evt);
            }
        });
        jpfPasswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpfPasswdKeyTyped(evt);
            }
        });

        txtobservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtobservacionKeyReleased(evt);
            }
        });

        cbperfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "ADMINISTRADOR", "SUPERVISOR" }));

        try {
            jtxtCedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtCedulaFocusLost(evt);
            }
        });

        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reload.png"))); // NOI18N
        btnmodificar.setText("ACTUALIZAR");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jtblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblUsers);

        try {
            txtcontacto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpfPasswd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbperfil, javax.swing.GroupLayout.Alignment.LEADING, 0, 151, Short.MAX_VALUE)
                            .addComponent(jtxtCedula, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcontacto)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtobservacion)))
                .addGap(18, 18, 18)
                .addComponent(jchbxVer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jpfPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jchbxVer))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jchbxVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbxVerActionPerformed

        // TODO add your handling code here:
        mostrarPasswd();
    }//GEN-LAST:event_jchbxVerActionPerformed

    private void jpfPasswdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfPasswdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jpfPasswdKeyTyped

    private void jpfPasswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpfPasswdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpfPasswdActionPerformed

    private void jtxtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtCedulaFocusLost
//focoCedula();
    }//GEN-LAST:event_jtxtCedulaFocusLost

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
//        guardar();
//        botoneslimpios();
//        bloqueartxt();
    }//GEN-LAST:event_btnguardarActionPerformed

//    void focoCedula(){
//        String ced = jtxtCedula.getText();
//        String ced1 = getCed(ced);
//
//        if (!verifCedula(ced1)) {
//            jtxtCedula.setText("");
//            jtxtCedula.requestFocus();
//        }
//    }
    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
//        botonactualizar();
//        botoneslimpios();
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
//        borrar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
//        limpiartxt();
//        bloqueartxt();
//        botoneslimpios();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
//        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        desbloqueartxt();
        limpiartxt();
//        botonnuevo();
//        btnguardar.setEnabled(true);
//        btnnuevo.setEnabled(false);
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtobservacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobservacionKeyReleased
//        txtobservacion.setText(txtobservacion.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtobservacionKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCajeros().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbperfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jchbxVer;
    private javax.swing.JPasswordField jpfPasswd;
    private javax.swing.JTable jtblUsers;
    private javax.swing.JFormattedTextField jtxtCedula;
    private javax.swing.JFormattedTextField txtcontacto;
    private javax.swing.JTextField txtobservacion;
    // End of variables declaration//GEN-END:variables
}
