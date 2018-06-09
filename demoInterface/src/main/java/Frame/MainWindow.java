package Frame;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import jdk.net.SocketFlow;
import twitter4j.Status;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class MainWindow {
    private int HEIGHT = 720;
    private int WEIDTH = 1280;
    private static final int CANDIDATE_BUTTON_SIZE = 60;
    private int nrCandidates;
    private ArrayList<JButton> buttons;
    private List<CompanyTweet> states;

    public MainWindow(List<CompanyTweet> states){
        this.states = states;
        nrCandidates = states.size();
    }

    public void addComponentsToPane(JFrame frame) {

        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(view, BorderLayout.CENTER);
        browser.loadURL("https://twitter.com");


        JPanel list = new JPanel();
        list.setPreferredSize(new Dimension(350, nrCandidates*CANDIDATE_BUTTON_SIZE));

        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        list.setLayout(layout);


        JScrollPane vertical = new JScrollPane(list);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(vertical, BorderLayout.LINE_START);

        buttons = new ArrayList<>();
        JButton newButton;
        for(int i = 0; i < nrCandidates; ++i) {
            String companyName = states.get(i).getCompanyName();
            String companyTwit = states.get(i).getStatus().getText();
            newButton = new JButton("<html>"+ ((companyName.length() > 20) ? (companyName.substring(0,  17) + "...") : companyName) +
                    "<br />"+ (companyTwit.length() > 40 ? (companyTwit.substring(0, 37) + "...") : companyTwit) +"</html>\""); //new Integer(i).toString()
            newButton.setHorizontalAlignment(SwingConstants.LEFT);
            newButton.setPreferredSize(new Dimension(350, CANDIDATE_BUTTON_SIZE));
            //System.out.println(i/50.0*255);
            newButton.setBackground(new Color((int)(i/(double)nrCandidates*255), 140+(int)(i/(double)nrCandidates*110), 225)); // 0 140 255 255 255 255
//            newButton.setBackground(new Color(0));
            buttons.add(newButton);
            list.add(newButton);
            Status tweet = states.get(i).getStatus();
            newButton.addActionListener(e ->{
                browser.loadURL("http://twitter.com/"+tweet.getUser().getId()+"/status/"+tweet.getId());
            });
        }

        JButton button = new JButton("Button 4 (LINE_START)");
        frame.add(button, BorderLayout.PAGE_START);

        frame.add(panel);
        frame.add(new ToolBar(), BorderLayout.PAGE_START);
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