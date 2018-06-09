package Controller;

import Frame.CompanyTweet;
import Frame.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TrendingAction extends AbstractAction {

    public TrendingAction(String s, List<CompanyTweet> comp, MainWindow window){
        super(s);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
