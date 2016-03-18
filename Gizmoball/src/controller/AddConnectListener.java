package controller;

import model.*;
import view.Board;
import view.IGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddConnectListener implements MouseListener {
    private IGUI bgui;
    private IGBallModel m;
    private boolean firstStep;
    private Bumper trigger;
    private IAbsorber triggerAbs;

    public AddConnectListener(IGUI bgui, IGBallModel m) {
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
            if(firstStep) {
                Bumper bumper = m.findBumper(x, y);
                if(bumper instanceof CircularBumper) {
                    trigger = bumper;
                    bgui.setMessageColor(Color.GREEN);
                    bgui.setMessage("Trigger found! Now press on Flipper or Absorber to connect it to trigger");
                    firstStep = false;
                } else if(m.getAbsorber() != null &&
                        x < m.getAbsorber().getXBottomRight() && x > m.getAbsorber().getXTopLeft() &&
                        y < m.getAbsorber().getYBottomRight() && y > m.getAbsorber().getYTopLeft()
                        ) {
                    triggerAbs = m.getAbsorber();
                    bgui.setMessageColor(Color.GREEN);
                    bgui.setMessage("Trigger found! Now press on Flipper or Absorber to connect it to trigger");
                    firstStep = false;
                }
            } else {
                Flipper f = m.findFlipper(x, y);
                if(trigger != null) {
                    if (f != null) {
                        m.addConnection(trigger.getName(), f.getName());
                        bgui.setMessageColor(Color.GREEN);
                        bgui.setMessage("Flipper connected!");
                    }
                } else if(triggerAbs != null) {
                    if(m.getAbsorber() != null &&
                            x < m.getAbsorber().getXBottomRight() && x > m.getAbsorber().getXTopLeft() &&
                            y < m.getAbsorber().getYBottomRight() && y > m.getAbsorber().getYTopLeft()
                            ) {
                        m.getAbsorber().setConnectedToItself(true);
                        bgui.setMessageColor(Color.GREEN);
                        bgui.setMessage("Absorber connected!");
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
