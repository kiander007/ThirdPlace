package Controller;

import Frame.CompanyTweet;
import Frame.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RecentAction extends AbstractAction {
    private List<CompanyTweet> comp;
    private MainWindow window;
    public RecentAction(String s, List<CompanyTweet> comp, MainWindow window){
        super(s);
        this.comp = comp;
        this.window = window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        comp.sort((c1, c2) -> {
            if(c1.getStatus().getCreatedAt().equals(c2.getStatus().getCreatedAt())){
                return 0;
            }
            return c1.getStatus().getCreatedAt().after(c2.getStatus().getCreatedAt())  ? -1 : 1;
        });
        window.updateButtons(comp);
    }
}
