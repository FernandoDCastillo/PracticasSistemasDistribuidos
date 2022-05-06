package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron
 */
public class Servidor extends javax.swing.JFrame implements Runnable {
    
    ArrayList ipUserOnline = new ArrayList();
    ArrayList UserNick = new ArrayList();
    
    public Servidor() {
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
        
    }
    
    private void reenviarPaquete(String ip, Paquete recibido) {
        try {
            if (!recibido.getIp().equals(ip)) {
                Socket Reenvio = new Socket(ip, 9032);
                ObjectOutputStream ReenvioPaquete = new ObjectOutputStream(Reenvio.getOutputStream());
                ReenvioPaquete.writeObject(recibido);
                ReenvioPaquete.close();
                Reenvio.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SERVIDOR");
        setResizable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            Paquete recibido = new Paquete();
            ServerSocket ss = new ServerSocket(9001);
            jTextArea1.append("SERVIDOR EN LINEA\n");
            while (true) {
                Socket s = ss.accept();
                ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
                recibido = (Paquete) entrada.readObject();

                //desconectar usuario-------------------------------
                if (recibido.getMensaje().equals("2ñs*ñsfd/45*E")) {
                    InetAddress obtIp = s.getInetAddress();
                    String ipOrigen = obtIp.getHostAddress();
                    recibido.setIp(ipOrigen);
                    jTextArea1.append("Se desconectó " + ipOrigen + "\n");
                    
                    for (int i = 0; i < ipUserOnline.size(); i++) {
                        if (ipUserOnline.get(i).equals(recibido.getIp())) {
                            ipUserOnline.remove(i);
                            UserNick.remove(i);
                        }
                    }
                    for (int i = 0; i < ipUserOnline.size(); i++) {
                        String ipDest = (String) ipUserOnline.get(i);
                        reenviarPaquete(ipDest, recibido);
                        s.close();
                        
                    }
                }
                //---------detectar usuarios conectados y enviar a todos los usuarios, cada vez q se conectan por primera vez               

                if (recibido.getMensaje().equals("7/2*ghg456Z")) {
                    InetAddress obtIp = s.getInetAddress();
                    String ipOrigen = obtIp.getHostAddress();
                    recibido.setIp(ipOrigen);
                    jTextArea1.append("Se conectó " + ipOrigen + "\n");
                    //envia ip nueva a todos los conectados-----------
                    for (int i = 0; i < ipUserOnline.size(); i++) {
                        String ipDest = (String) ipUserOnline.get(i);
                        reenviarPaquete(ipDest, recibido);
                        s.close();
                    }
                    //enviar ip de conectados a quien recien conecto
                    for (int i = 0; i < ipUserOnline.size(); i++) {
                        String ipConectados = (String) ipUserOnline.get(i);
                        String user = (String) UserNick.get(i);
                        recibido.setUsuario(user);
                        recibido.setIp(ipConectados);
                        reenviarPaquete(ipOrigen, recibido);
                        s.close();
                    }
                    
                    ipUserOnline.add(ipOrigen);
                    UserNick.add(recibido.getUsuario());
                    
                }
                //Se envia el mensaje a todos
                if ((!recibido.getMensaje().equals("7/2*ghg456Z")) && (!recibido.getMensaje().equals("2ñs*ñsfd/45*E"))) {
                    InetAddress obtIp = s.getInetAddress();
                    String ipOrigen = obtIp.getHostAddress();
                    for (int i = 0; i < ipUserOnline.size(); i++) {
                        if (ipOrigen.equals(ipUserOnline.get(i))) {
                            
                        } else {
                            Socket Reenvio = new Socket((String) ipUserOnline.get(i), 9032);
                            ObjectOutputStream ReenvioPaquete = new ObjectOutputStream(Reenvio.getOutputStream());
                            ReenvioPaquete.writeObject(recibido);
                            ReenvioPaquete.close();
                            Reenvio.close();
                            s.close();
                        }
                        
                    }
                    
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Paquete implements Serializable {
    
    String usuario, ip, mensaje;
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
