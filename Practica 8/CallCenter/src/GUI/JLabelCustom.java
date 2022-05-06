package GUI;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Carlo
 */
public class JLabelCustom extends JLabel{

    public JLabelCustom(String texto, int x, int y,int type) {
        setBackground(new Color(30, 144, 255));
        setText(texto);
        int ancho = (texto.length() <= 16) ? ((texto.length() + 10) * texto.length()) : ((texto.length() + 10) * 14);
        
        setBounds( ((type == 0) ? x : x - ancho) , y, ancho, 30);
        setOpaque(true);
    }
}
