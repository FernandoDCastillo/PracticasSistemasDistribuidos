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
    private Socket channel;
    private PrintWriter streamOut;

    public SocketManage(){
        initialiceSocket();
    }
    
    private void initialiceSocket(){
        try {
            channel = new Socket("localhost",12345);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo inciar el cliente, inicie el servidor", 
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
