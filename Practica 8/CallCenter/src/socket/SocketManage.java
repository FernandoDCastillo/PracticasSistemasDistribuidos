package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlo
 */
public class SocketManage {
    private ServerSocket connection;
    private Socket channel;
    private PrintWriter streamOut;

    public SocketManage(){
        initialiceSocket();
        initialiceChannel();
    }
    
    private void initialiceSocket(){
        try {
            connection = new ServerSocket(12345);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo inciar el servidor", 
                                            "Error server", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.toString());
        }
    }
    
    private void initialiceChannel() {
        try {
            channel = connection.accept();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo inciar el canal de conexion", 
                                            "Error server", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.toString());
        }
    }
    
    public void closeResources(){
        if (channel != null) { // cierre del socket
            try{
                channel.close();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo cerrar el canal", 
                                            "Error server", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error al cerrar el socket");
            }
        }
        
        if (connection != null){ // cierre del server socket
            try{
                connection.close();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo cerrar la conexion", 
                                            "Error server", JOptionPane.ERROR_MESSAGE);
                System.err.println("error al cerrar el server socket");
            }
        }
    }
    
    public ServerSocket getConnection() {
        return connection;
    }

    public void setConnection(ServerSocket connection) {
        this.connection = connection;
    }

    public Socket getChannel() {
        return channel;
    }

    public void setChannel(Socket channel) {
        this.channel = channel;
    }

    public PrintWriter getStreamOut() {
        return streamOut;
    }

    public void setStreamOut(PrintWriter streamOut) {
        this.streamOut = streamOut;
    }
}
