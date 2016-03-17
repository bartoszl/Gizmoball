package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddAbsorberListener implements MouseListener {
    private IGUI bgui;
    private IGBallModel m;
    private int[] clicks;
    private boolean first;

    public AddAbsorberListener(IGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
        first = true;
        clicks = new int[4];
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedComponent().equals("Absorber")) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            x=(x/20)*20;
            y=(y/20)*20;
            if(first){
                bgui.setMessage("Top left corner set! Now click elsewhere to set bottom right corner of absorber");
                clicks[0] = x;
                clicks[1] = y;
                first = false;
            }
            else {
                clicks[2] = x ;
                clicks[3] = y ;
                if((clicks[0]<clicks[2])||(clicks[1]<clicks[3])){
                	clicks[2]+=20;
                	clicks[3]+=20;
                }
                else{
                	clicks[0]+=20;
                    clicks[1]+=20;
                }
                for(int i = 0; i < clicks.length; i++) {
                    if (clicks[i] > 400) return;
                }
                m.addAbsorber("absorber",clicks[0],clicks[1],clicks[2],clicks[3]);
                bgui.setMessage("Absorber added!");
                bgui.setMessageColor(Color.GREEN);
                first = true;
                b.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
