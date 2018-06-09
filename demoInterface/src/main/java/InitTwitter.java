import Frame.CompanyTweet;
import twitter4j.*;

import twitter4j.conf.ConfigurationBuilder;

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

    }

    public List<CompanyTweet> getAllTweets(List<Company> compList, int beginComp,int endComp, int amountTweet){
        List<CompanyTweet> resultList = new ArrayList();
        System.out.println("initializing");
        for (int i = beginComp; i < endComp; i++) {
            resultList.addAll(getTweets(compList.get(i),amountTweet));
        }
        return resultList;
    }

    public List<CompanyTweet> getTweets(Company comp,int amount){
        int pageno = 1;
        List<Status> statuses = new ArrayList();
        //gets the first 100 tweets
        Paging page = new Paging(pageno, amount);
        try {
            System.out.println(comp.getTwitterName());
        statuses.addAll(twitter.getUserTimeline(comp.getTwitterName(), page));
        } catch(TwitterException e) {
            e.printStackTrace();
        }
        List<CompanyTweet> result = new ArrayList();
        for (Status s:statuses) {
            result.add(new CompanyTweet(s,comp.getCompanyName()));
        }
        return result;
    }

    public void sortByReTweets(List<CompanyTweet> ls){
        ls.sort((c1, c2) -> {
            if(c1.getStatus().getRetweetCount() == c1.getStatus().getRetweetCount()){
                return 0;
            }
            return c1.getStatus().getRetweetCount() < c1.getStatus().getRetweetCount() ? -1 : 1;
        });
    }

    public void printTweets(List<Status> ls){
        for (Status s:ls) {
            System.out.println(s.getUser().getName() + ":" + s.getText() + "retweets: " + s.getRetweetCount());
        }
    }

    public void printTweets(List<CompanyTweet> ls, int size){
        for (int i = 0; i< size; i++) {
            System.out.println(ls.get(i).getStatus().getUser().getName() + ":" + ls.get(i).getStatus().getText() + "retweets: " + ls.get(i).getStatus().getRetweetCount());
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
