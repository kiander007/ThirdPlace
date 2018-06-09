package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RetweetAction extends AbstractAction {

    public RetweetAction(String s){
        super(s);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("dank memes");
    }
}
