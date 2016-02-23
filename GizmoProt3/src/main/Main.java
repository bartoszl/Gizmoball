package main;

import View.RunGUI;
import model.CircularBumper;
import model.Model;
import model.SquareBumper;
import model.TriangularBumper;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Model model = new Model();

                    model.setBallSpeed(200, 200);


                    SquareBumper s1, s2, s3;

                    s1 = new SquareBumper(250, 250, "S1"); s1.setColor(Color.RED);
                    s2 = new SquareBumper(50, 50, "S2"); s2.setColor(Color.RED);
                    s3 = new SquareBumper(340, 340, "S3"); s3.setColor(Color.RED);

                    model.addSquareBumper(s1);
                    model.addSquareBumper(s2);
                    model.addSquareBumper(s3);

                    TriangularBumper t1, t2, t3;

                    t1 = new TriangularBumper(80, 80, "T1"); t1.setColor(Color.BLUE);
                    t2 = new TriangularBumper(30, 30, "T2"); t2. setColor(Color.BLUE);
                    t3 = new TriangularBumper(150, 150, "T3"); t3.setColor(Color.BLUE);

                    model.addTriangularBumper(t1);
                    model.addTriangularBumper(t2);
                    model.addTriangularBumper(t3);

                    CircularBumper c1, c2, c3;
                    c1 = new CircularBumper(300, 300, 10, "C1"); c1.setColor(Color.GREEN);
                    c2 = new CircularBumper(280, 280, 10, "C2"); c2.setColor(Color.GREEN);
                    c3 = new CircularBumper(100, 100, 10, "C3"); c3.setColor(Color.GREEN);

                    model.addCircularBumper(c1);
                    model.addCircularBumper(c2);
                    model.addCircularBumper(c3);
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
