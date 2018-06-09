package Frame;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainWindow {
    private int HEIGHT = 720;
    private int WEIDTH = 1280;

    public void addComponentsToPane(JFrame frame) {

        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(view, BorderLayout.CENTER);


        browser.loadURL("https://google.com");


        JButton button = new JButton("Button 3 (LINE_START)");
        frame.add(button, BorderLayout.LINE_START);

        DataList data = new DataList();
        data.TextAreaList(frame,panel);

        frame.add(panel);

        /*
        JButton button1 = new JButton("Button 2 (CENTER)");
        button1.setPreferredSize(new Dimension(WEIDTH/10, HEIGHT));
        pane.add(button1, BorderLayout.LINE_START);

        DataList data = new DataList();
        data.TextAreaList(pane);
        */
    }

    /**
     * Create the GUI and show it.
     */
    private void createAndShowGUI() {

        JFrame frame = new JFrame("MainWindow");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addComponentsToPane(frame);

        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void run() {
        createAndShowGUI();
    }
}