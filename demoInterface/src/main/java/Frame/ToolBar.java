package Frame;

import Controller.*;

import javax.swing.*;

public class ToolBar extends JToolBar {
    public ToolBar () {
        JButton likes = new JButton();
        JButton retweets = new JButton();
        JButton followers = new JButton();
        JButton trending = new JButton();
        JButton recent = new JButton();

        likes.setAction(new LikeAction("Most Likes"));
        retweets.setAction(new RetweetAction("Most Retweets"));
        followers.setAction(new FollowerAction("Most Followers"));
        trending.setAction(new TrendingAction("Trending"));
        recent.setAction(new RecentAction("Newest"));

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
