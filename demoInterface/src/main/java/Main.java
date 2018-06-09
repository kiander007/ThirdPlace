
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

        //MainWindow main = new MainWindow();
        //main.run();

        DBConnection db = new DBConnection();
        InitTwitter twit = new InitTwitter();

            List<CompanyTweet> statuses = twit.getAllTweets(db.getUsers(),20);
            twit.sortByReTweets(statuses);
            twit.printTweets(statuses,200);
    }
}
