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

    Twitter twitter;

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

        String user = "cnn";
        List<Status> statuses = getTweets(user);
        printTweets(statuses);
        sortByReTweets(statuses);
        printTweets(statuses);

        System.out.println("Total: "+statuses.size());
    }

    public List<Status> getTweets(String user){
        int pageno = 1;
        List<Status> statuses = new ArrayList();
        //gets the first 100 tweets
        Paging page = new Paging(pageno, 100);
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
}
