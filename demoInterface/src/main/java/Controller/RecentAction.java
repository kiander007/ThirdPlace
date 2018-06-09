package Controller;

import Frame.CompanyTweet;
import Frame.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RecentAction extends AbstractAction {

    public RecentAction(String s, List<CompanyTweet> comp, MainWindow window){
        super(s);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
