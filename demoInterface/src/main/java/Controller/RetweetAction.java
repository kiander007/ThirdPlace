package Controller;

import Frame.CompanyTweet;
import Frame.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RetweetAction extends AbstractAction {
    private List<CompanyTweet> comp;
    private MainWindow window;
    public RetweetAction(String s, List<CompanyTweet> comp, MainWindow window){
        super(s);
        this.comp = comp;
        this.window =window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        comp.sort((c1, c2) -> {
            if(c1.getStatus().getRetweetCount() == c2.getStatus().getRetweetCount()){
                return 0;
            }
            return c1.getStatus().getRetweetCount() > c2.getStatus().getRetweetCount() ? -1 : 1;
        });
        window.updateButtons(comp);
    }
}
