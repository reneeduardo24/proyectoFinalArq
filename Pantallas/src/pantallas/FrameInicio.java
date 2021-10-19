/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import controles.FacadadeControl;
import controles.IFacadaDeNegocio;
import datos.IpServidor;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import timbiriche.Cliente;
import timbiriche.Jugador;

/**
 *
 * @author rene_
 */
public class FrameInicio extends javax.swing.JFrame {

    private Cliente jugador;
    private IFacadaDeNegocio facadaDeControl;
    private Menu menu;

    public FrameInicio() {
        initComponents();
        this.jugador = new Jugador();
        this.jugador.setIpHost(IpServidor.ipServidor);
        this.jugador.setPuertoHost(IpServidor.puertoServidor);

        this.facadaDeControl = new FacadadeControl();
        this.jButton_CrearPartida.setEnabled(false);
        this.jButton_UnirseAPartida.setEnabled(false);

        this.menu = new Menu();
        menu.setCommand("Registrarse", new RegistrarseCommand(this));
        menu.setCommand("Crear Partida", new CrearPartidaCommand(this));
        menu.setCommand("Unirse a Partida", new UnirsePartidaCommand(this));
        menu.setCommand("Salir", new SalirCommand(this));
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_NombreJugador = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton_RegistrarJugador = new javax.swing.JButton();
        jButton_CrearPartida = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton_UnirseAPartida = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel_NombreJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(2786, 113, 94, 46));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Timbiriche");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 11, -1, 96));

        jButton_RegistrarJugador.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jButton_RegistrarJugador.setText("Registrarse");
        jButton_RegistrarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarJugadorActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 150, -1));

        jButton_CrearPartida.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jButton_CrearPartida.setText("Crear Partida");
        jButton_CrearPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CrearPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_CrearPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 150, 150, -1));

        jButton3.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 205, 150, -1));

        jButton_UnirseAPartida.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jButton_UnirseAPartida.setText("Unirse a Partida");
        jButton_UnirseAPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UnirseAPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_UnirseAPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 205, 150, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.jpg"))); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(750, 450));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents
        public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getID());
        menu.runCommand(e.getActionCommand());
    }
    private void jButton_UnirseAPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UnirseAPartidaActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_jButton_UnirseAPartidaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton_CrearPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CrearPartidaActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_jButton_CrearPartidaActionPerformed

    private void jButton_RegistrarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarJugadorActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_jButton_RegistrarJugadorActionPerformed

    public void actualizarPantalla() {
        if (((Jugador) this.jugador).getNombre() != null) {
            jLabel_NombreJugador.setText(((Jugador) jugador).getNombre());
            this.jButton_CrearPartida.setEnabled(true);
            this.jButton_UnirseAPartida.setEnabled(true);
            this.jButton_RegistrarJugador.setVisible(false);
        }
    }

    public int obtenerCapacidadDePartida() {
        int capacidadDePartida = 0;
        SpinnerNumberModel sModel = new SpinnerNumberModel(2, 2, 4, 1);
        JSpinner spinner = new JSpinner(sModel);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        int option = JOptionPane.showOptionDialog(null, spinner, "Ingrese el número de jugadores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            capacidadDePartida = (Integer) spinner.getValue();
        }
        return capacidadDePartida;
    }

    public Cliente obtenerJugador() {
        return jugador;
    }

    public IFacadaDeNegocio obtenerFacadaControl() {
        return this.facadaDeControl;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_CrearPartida;
    private javax.swing.JButton jButton_RegistrarJugador;
    private javax.swing.JButton jButton_UnirseAPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_NombreJugador;
    // End of variables declaration//GEN-END:variables
}
