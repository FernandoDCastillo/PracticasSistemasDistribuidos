package stream;

import java.io.DataInputStream;
import java.net.Socket;

/**
 *
 * @author Carlo
 */
public class StreamReaderServices {
    private DataInputStream input;
    
    public StreamReaderServices(Socket channel) {
        InitializeStreamReader(channel);
    }
    
    private void InitializeStreamReader(Socket channel) {
        try {
            input = new DataInputStream(channel.getInputStream());
        } catch (Exception e) {
            
        }
    }
    
    public String getInputText(){
        String accept = null;
        try {
             accept = input.readUTF();
        } catch (Exception e) {
        }
        
        return accept;
    }
    
    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }
}
