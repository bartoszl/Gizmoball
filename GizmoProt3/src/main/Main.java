package main;

import view.RunGUI;
import model.*;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Model model = new Model();

                    model.setBallSpeed(200, 200);

                    SquareBumper s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

                    s1 = new SquareBumper(240, 240, "S1"); s1.setColor(Color.RED);
                    s2 = new SquareBumper(60, 60, "S2"); s2.setColor(Color.RED);
                    s3 = new SquareBumper(340, 340, "S3"); s3.setColor(Color.RED);
                    s4 = new SquareBumper(260, 240, "S4"); s4.setColor(Color.RED);
                    s5 = new SquareBumper(220, 240, "S5"); s5.setColor(Color.RED);
                    s6 = new SquareBumper(180, 180, "S6"); s6.setColor(Color.RED);
                    s7 = new SquareBumper(40, 360, "S7"); s7.setColor(Color.RED);
                    s8 = new SquareBumper(60, 360, "S8"); s8.setColor(Color.RED);
                    s9 = new SquareBumper(80, 360, "S9"); s9.setColor(Color.RED);
                    s10 = new SquareBumper(100, 360, "S10"); s10.setColor(Color.RED);

                    model.addSquareBumper(s1);
                    model.addSquareBumper(s2);
                    model.addSquareBumper(s3);
                    model.addSquareBumper(s4);
                    model.addSquareBumper(s5);
                    model.addSquareBumper(s6);
                    model.addSquareBumper(s7);
                    model.addSquareBumper(s8);
                    model.addSquareBumper(s9);
                    model.addSquareBumper(s10);

                    TriangularBumper t1, t2, t3, t4, t5, t6;

                    t1 = new TriangularBumper(80, 80, "T1"); t1.setColor(Color.BLUE);
                    t2 = new TriangularBumper(20, 20, "T2"); t2. setColor(Color.BLUE);
                    t3 = new TriangularBumper(160, 160, "T3"); t3.setColor(Color.BLUE);
                    t4 = new TriangularBumper(100, 100, "T4"); t4.setColor(Color.BLUE);
                    t5 = new TriangularBumper(60, 20, "T5"); t5.setColor(Color.BLUE);
                    t6 = new TriangularBumper(100, 20, "T6"); t6.setColor(Color.BLUE);

                    model.addTriangularBumper(t1);
                    model.addTriangularBumper(t2);
                    model.addTriangularBumper(t3);
                    model.addTriangularBumper(t4);
                    model.addTriangularBumper(t5);
                    model.addTriangularBumper(t6);

                    CircularBumper c1, c2, c3, c4, c5, c6;

                    c1 = new CircularBumper(300, 300, 10, "C1"); c1.setColor(Color.GREEN);
                    c2 = new CircularBumper(280, 280, 10, "C2"); c2.setColor(Color.GREEN);
                    c3 = new CircularBumper(380, 20, 10, "C3"); c3.setColor(Color.GREEN);
                    c4 = new CircularBumper(240, 220, 10, "C4"); c4.setColor(Color.GREEN);
                    c5 = new CircularBumper(240, 220, 10, "C5"); c5.setColor(Color.GREEN);
                    c6 = new CircularBumper(40, 240, 10, "C6"); c6.setColor(Color.GREEN);

                    model.addCircularBumper(c1);
                    model.addCircularBumper(c2);
                    model.addCircularBumper(c3);
                    model.addCircularBumper(c4);
                    model.addCircularBumper(c5);
                    model.addCircularBumper(c6);
                    model.setMoving(true);

                    RunGUI window = new RunGUI(model);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}