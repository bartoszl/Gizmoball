package controller;

import model.*;
import physics.Circle;
import view.Board;
import view.BuildGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddConnectListener implements MouseListener {
    private BuildGUI bgui;
    private IGBallModel m;
    private boolean firstStep;
    private CircularBumper trigger;
    private IAbsorber triggerAbs;

    public AddConnectListener(BuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
        firstStep = true;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        Point mouseP = MouseInfo.getPointerInfo().getLocation();
        Point gridP = b.getLocationOnScreen();
        int x = mouseP.x - gridP.x;
        int y = mouseP.y - gridP.y;
        x -= x % 20;
        y -= y % 20;
        if(b.getAction() == Board.Action.CONNECT) {
            //find trigger
            if(firstStep) {
                Bumper bumper = m.findBumper(x, y);
                if(bumper instanceof CircularBumper) {
                    //sorry for casting
                    trigger = (CircularBumper) bumper;
                    System.out.println("Trigger found!");
                    firstStep = false;
                } else if(m.getAbsorber() != null &&
                        x < m.getAbsorber().getXBottomRight() && x > m.getAbsorber().getXTopLeft() &&
                        y < m.getAbsorber().getYBottomRight() && y > m.getAbsorber().getYTopLeft()
                        ) {
                    //it is absorber
                    triggerAbs = m.getAbsorber();
                    System.out.println("Trigger found!");
                    firstStep = false;
                }
            } else {
                //find flipper
                Flipper f = m.findFlipper(x, y);
                if(trigger != null) {
                    if (f != null) {
                        m.addConnection(new Connection(trigger, f));
                        System.out.println("Connected!");
                    }
                } else if(triggerAbs != null) {
                    if(m.getAbsorber() != null &&
                            x < m.getAbsorber().getXBottomRight() && x > m.getAbsorber().getXTopLeft() &&
                            y < m.getAbsorber().getYBottomRight() && y > m.getAbsorber().getYTopLeft()
                            ) {
                        m.getAbsorber().setConnectedToItself(true);
                        System.out.println("Connected!");
                    }
                }

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
