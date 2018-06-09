
import Frame.CompanyTweet;
import  Frame.MainWindow;
import twitter4j.Status;

import javax.swing.*;
import java.util.*;

/**
 * This sample demonstrates how to create Browser instance,
 * embed it into Swing BrowserView container, display it in JFrame and
 * navigate to the "www.google.com" web site.
 */
public class Main {
    public static void main(String[] args) {

        DBConnection db = new DBConnection();
        InitTwitter twit = new InitTwitter();
        Saver saver  = new Saver();

        List<CompanyTweet> statusses = saver.loadGraph("CompTweets1.sav");
        MainWindow main = new MainWindow(statusses);
        main.run();
    }
}
