import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]) throws Exception{
        JEditorPane website = new JEditorPane("https://www.google.nl/");
        website.setEditable(false);

        JFrame frame = new JFrame("Google");
        frame.add(new JScrollPane(website));
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
