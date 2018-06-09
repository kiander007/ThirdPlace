import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InitTwitter {

    private Twitter twitter;

    public InitTwitter() {
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("3cH0GU7iKaWppFU789HkwbauE")
                .setOAuthConsumerSecret("SacFfPGqtBX6XZvsVTczpDKAcp8Ntk7T3djm6ta0BaiAStaml1")
                .setOAuthAccessToken("1005405587637768193-HECucYVnPfo0jTE2jI9wGUQpt7QcjE")
                .setOAuthAccessTokenSecret("j8wayWvkctCIG9NXPyzBjtjwf1td8K3bayxcDFpGhErQ0");
        TwitterFactory tf = new TwitterFactory(cb.build());

        twitter = tf.getInstance();
        /*
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        }catch (TwitterException e){
            System.out.println(" what happend?"+e);
        }
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }*/

        //String user = "cnn";
        //List<Status> statuses = getTweets(user,5);
       // printTweets(statuses);
        //sortByReTweets(statuses);
        //printTweets(statuses);

        //System.out.println("Total: "+statuses.size());
    }
    
    public List<Status> getTweets(String user,int amount){
        int pageno = 1;
        List<Status> statuses = new ArrayList();
        //gets the first 100 tweets
        Paging page = new Paging(pageno, amount);
        try {
        statuses.addAll(twitter.getUserTimeline(user, page));
        } catch(TwitterException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    public void sortByReTweets(List<Status> ls){
        ls.sort(Comparator.comparing(Status::getRetweetCount).reversed());
    }

    public void printTweets(List<Status> ls){
        for (Status s:ls) {
            System.out.println(s.getUser().getName() + ":" + s.getText() + "retweets: " + s.getRetweetCount());
        }
    }

    public void printTweets(List<Status> ls, int size){
        for (int i = 0; i< size; i++) {
            System.out.println(ls.get(i).getUser().getName() + ":" + ls.get(i).getText() + "retweets: " + ls.get(i).getRetweetCount());
        }
    }

    public void printUserStats(List<Status> ls, String user){
        System.out.println("twitter account of: "+user);
        System.out.println("followers: "+getFollowers(user));
        System.out.println("total tweets: "+getTotalTweets(ls));
        //TODO change this
        System.out.println("popularity: "+getAverageFavorite(ls));
    }

    public int getTotalTweets(List<Status> ls){
        return ls.size();
    }

    public int getFollowers(String name){
        //TODO fix this
        try {
            User user = twitter.showUser(name);
            return user.getFollowersCount();
        }catch (TwitterException e){
            System.out.println("cannot get followers");
        }
        return 0;
    }

    public double getAverageRetweets(List<Status> ls){
        long count = 0;
        if(ls.size() == 0)
            return 0;
        for (Status status: ls) {
            count += status.getRetweetCount();
        }
        return count/ls.size();
    }

    public int getTopRetweets(List<Status> ls){
        int top = 0;
        for (Status status: ls) {
            top = (top < status.getRetweetCount() ? status.getRetweetCount() : top);
        }
        return top;
    }

    public double getAverageFavorite(List<Status> ls){
        long count = 0;
        if(ls.size() == 0)
            return 0;
        for (Status status: ls) {
            count += status.getFavoriteCount();
        }
        return count/ls.size();
    }

    public int getTopFavorite(List<Status> ls){
        int top = 0;
        for (Status status: ls) {
            top = (top < status.getFavoriteCount() ? status.getFavoriteCount() : top);
        }
        return top;
    }

    public int getPopularity(List<Status> ls,String user){
        double averageFav = getAverageFavorite(ls);
        double averageRetweet = getAverageRetweets(ls);
        int followers = getFollowers(user);
        return (int)(followers + averageFav + averageRetweet);
    }
}
