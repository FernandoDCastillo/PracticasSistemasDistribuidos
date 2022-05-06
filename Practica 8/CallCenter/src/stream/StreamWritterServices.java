package stream;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Carlo
 */
public class StreamWritterServices{
    private DataOutputStream output;
    
    public StreamWritterServices(Socket channel) {
        InitializeStreamWritter(channel);
    }
    
    private void InitializeStreamWritter(Socket channel) {
        try {
            output = new DataOutputStream(channel.getOutputStream());
        } catch (Exception e) {
            
        }
    }
    
    public boolean sendText(String text){
        try {
             output.writeUTF("Call: " + text);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
}
