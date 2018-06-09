import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class InitTwitter {
    public InitTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("3cH0GU7iKaWppFU789HkwbauE")
                .setOAuthConsumerSecret("SacFfPGqtBX6XZvsVTczpDKAcp8Ntk7T3djm6ta0BaiAStaml1")
                .setOAuthAccessToken("1005405587637768193-HECucYVnPfo0jTE2jI9wGUQpt7QcjE")
                .setOAuthAccessTokenSecret("j8wayWvkctCIG9NXPyzBjtjwf1td8K3bayxcDFpGhErQ0");
        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();
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
        }
    }
}
