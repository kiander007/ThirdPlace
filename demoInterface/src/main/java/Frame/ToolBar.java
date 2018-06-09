package Frame;

import Controller.*;

import javax.swing.*;
import java.util.List;

public class ToolBar extends JToolBar {
    public ToolBar (List<CompanyTweet> comp, MainWindow window) {
        JButton likes = new JButton();
        JButton retweets = new JButton();
        JButton followers = new JButton();
        JButton trending = new JButton();
        JButton recent = new JButton();

        likes.setAction(new LikeAction("Most Likes",comp,window));
        retweets.setAction(new RetweetAction("Most Retweets",comp,window));
        followers.setAction(new FollowerAction("Most Followers",comp,window));
        trending.setAction(new TrendingAction("Trending",comp,window));
        recent.setAction(new RecentAction("Newest",comp,window));

        this.setRollover(true);
        this.add(recent);
        this.addSeparator();
        this.add(followers);
        this.addSeparator();
        this.add(likes);
        this.addSeparator();
        this.add(retweets);
        this.addSeparator();
        this.add(trending);
    }
}
