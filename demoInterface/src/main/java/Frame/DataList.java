package Frame;

import javax.swing.*;
import java.awt.*;

public class DataList {
    private int HEIGHT = 720;
    private int WEIDTH = 1280;

    public void TextAreaList(JFrame frame, JPanel panel){
        JTextArea list = new JTextArea("");
        JScrollPane scroll = new JScrollPane(list);
        frame.add(list, BorderLayout.CENTER);
    }
}
