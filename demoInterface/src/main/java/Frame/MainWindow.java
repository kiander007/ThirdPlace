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

public class MainWindow extends JFrame{
    private static final int CANDIDATE_BUTTON_SIZE = 60;
    private int nrCandidates;
    private ArrayList<JButton> buttons;
    private List<CompanyTweet> states;
    private Browser browser;
    private JPanel list;

    public MainWindow(List<CompanyTweet> states){
        this.states = states;
        nrCandidates = states.size();

        this.setTitle("Our Wonderful Program");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

         browser = new Browser();

        addComponentsToPane();
    }

    public void addComponentsToPane() {


        BrowserView view = new BrowserView(browser);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(view, BorderLayout.CENTER);
        browser.loadURL("https://twitter.com");


        list = new JPanel();
        list.setPreferredSize(new Dimension(350, nrCandidates*CANDIDATE_BUTTON_SIZE));

        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        list.setLayout(layout);

        updateButtons(states);
        JScrollPane vertical = new JScrollPane(list);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(vertical, BorderLayout.LINE_START);




        JButton button = new JButton("Button 4 (LINE_START)");
        this.add(button, BorderLayout.PAGE_START);

        this.add(panel);
        this.add(new ToolBar(states,this), BorderLayout.PAGE_START);
    }

    /**
     * Create the GUI and show it.
     */
    private void createAndShowGUI() {

        JFrame frame = new JFrame("MainWindow");

    }

    public void updateButtons(List<CompanyTweet> states){
        list.removeAll();
        JButton newButton;
        for(int i = 0; i < nrCandidates; ++i) {
            String companyName = states.get(i).getCompanyName();
            String companyTwit = states.get(i).getStatus().getText();
            newButton = new JButton("<html>"+ ((companyName.length() > 37) ? (companyName.substring(0,  34) + "...") : companyName) +
                    "<br />"+ (companyTwit.length() > 40 ? (companyTwit.substring(0, 37) + "...") : companyTwit) +"</html>\""); //new Integer(i).toString()
            newButton.setHorizontalAlignment(SwingConstants.LEFT);
            newButton.setPreferredSize(new Dimension(350, CANDIDATE_BUTTON_SIZE));
            //System.out.println(i/50.0*255);
            newButton.setBackground(new Color((int)(i/(double)nrCandidates*255), 140+(int)(i/(double)nrCandidates*110), 225)); // 0 140 255 255 255 255
//            newButton.setBackground(new Color(0));

            list.add(newButton);
            Status tweet = states.get(i).getStatus();
            newButton.addActionListener(e ->{
                browser.loadURL("http://twitter.com/"+tweet.getUser().getId()+"/status/"+tweet.getId());
            });
        }
        revalidate();
        repaint();
    }

}