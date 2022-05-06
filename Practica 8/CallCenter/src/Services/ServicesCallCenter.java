package Services;

import GUI.GuiCallCenter;
import GUI.JLabelCustom;
import javax.swing.JPanel;
import socket.SocketManage;
import stream.StreamReaderServices;
import stream.StreamWritterServices;

/**
 *
 * @author Carlo
 */
public class ServicesCallCenter extends Thread{
    private SocketManage socket;
    private StreamReaderServices input;
    private StreamWritterServices output;
    private static boolean meSend = false;
    private static boolean clientReceived = false;
    public static JPanel panel;
    
    public ServicesCallCenter() {
        System.out.println("Iniziando servicios");
    }
    
    public synchronized void manageServicesCallCenter() {
        socket = new SocketManage();
        input = new StreamReaderServices(socket.getChannel());
        
        while(true) {
            String entrada = input.getInputText();
            if (entrada != null){
                if (GuiCallCenter.thisLineY ==  GuiCallCenter.clientLineY) {//valor inicial
                    panel.add(new JLabelCustom(entrada, GuiCallCenter.clientLineX, GuiCallCenter.clientLineY, 1));
                    GuiCallCenter.clientLineY += 40;
                } else if (GuiCallCenter.thisLineY <  GuiCallCenter.clientLineY) {
                    panel.add(new JLabelCustom(entrada, GuiCallCenter.clientLineX, GuiCallCenter.clientLineY, 1));
                    GuiCallCenter.clientLineY += 40;
                } else {
                    panel.add(new JLabelCustom(entrada, GuiCallCenter.clientLineX, GuiCallCenter.thisLineY, 1));
                    GuiCallCenter.clientLineY = GuiCallCenter.thisLineY + 40;
                }
                panel.updateUI();
            } else {
                socket.closeResources();
                socket = new SocketManage();
                input = new StreamReaderServices(socket.getChannel());
            }
            
        }
    }
    
    public void InitialiceWritter(String text) {
        output = new StreamWritterServices(socket.getChannel());
        output.sendText(text);
    }
    
    public SocketManage getSocket() {
        return socket;
    }

    public void setSocket(SocketManage socket) {
        this.socket = socket;
    }

    public StreamReaderServices getInput() {
        return input;
    }

    public void setInput(StreamReaderServices input) {
        this.input = input;
    }

    public StreamWritterServices getOutput() {
        return output;
    }

    public void setOutput(StreamWritterServices output) {
        this.output = output;
    }

    @Override
    public synchronized void run() {
        manageServicesCallCenter();
    }
    
    
}
