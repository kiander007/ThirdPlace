package Controller;

import Frame.CompanyTweet;
import Frame.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TrendingAction extends AbstractAction {
    private List<CompanyTweet> comp;
    private MainWindow window;
    public TrendingAction(String s, List<CompanyTweet> comp, MainWindow window){
        super(s);
        this.comp = comp;
        this.window =window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        comp.sort((c1, c2) -> {
            if(trend(c1) == trend(c2)){
                return 0;
            }
            return trend(c1) > trend(c2) ? -1 : 1;
        });
        window.updateButtons(comp);
    }

    private double trend(CompanyTweet x){
        Calendar  now = GregorianCalendar.getInstance();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(x.getStatus().getCreatedAt());

        return (x.getStatus().getFavoriteCount()+x.getStatus().getRetweetCount())/(double)(now.getTimeInMillis()-calendar.getTimeInMillis());
    }
}
