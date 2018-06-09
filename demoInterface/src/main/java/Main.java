
import  Frame.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * This sample demonstrates how to create Browser instance,
 * embed it into Swing BrowserView container, display it in JFrame and
 * navigate to the "www.google.com" web site.
 */
public class Main {
    public static void main(String[] args) {

        MainWindow main = new MainWindow();
        main.run();

        DBConnection db = new DBConnection();
        InitTwitter twit = new InitTwitter();

        for (String user: db.getUsers()) {
            twit.getTweets(user,2);
        }
    }
}