import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InitTwitter {
    public InitTwitter() {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("", "");

        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Authorization URL: \n"
                + requestToken.getAuthorizationURL());

        AccessToken accessToken = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Hit above Authorization URL and Input PIN here: ");
            String pin = br.readLine();

            accessToken = twitter.getOAuthAccessToken(requestToken, pin);

        } catch (TwitterException te) {

            System.out.println("Failed to get access token, caused by: "
                    + te.getMessage());
        }

        System.out.println("Access Token: " + accessToken.getToken());
        System.out.println("Access Token Secret: "
                + accessToken.getTokenSecret());

        // updating twitter status
        twitter.updateStatus("hi.. im updating this using Namex Tweet for Demo");

        System.out.println("\nReading Twitter Timeline:");

        // I'm reading your timeline
        ResponseList<Status> list = twitter.getHomeTimeline();
        for (Status each : list) {

            System.out.println("Sent by: @" + each.getUser().getScreenName()
                    + " - " + each.getUser().getName() + "\n" + each.getText()
                    + "\n");
        }
    }
}
