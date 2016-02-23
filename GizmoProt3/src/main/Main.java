package main;

import View.RunGUI;
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

                    s1 = new SquareBumper(250, 250, "S1");
                    s2 = new SquareBumper(50, 50, "S2");
                    s3 = new SquareBumper(380, 380, "S3");

                    model.addSquareBumper(s1);
                    model.addSquareBumper(s2);
                    model.addSquareBumper(s3);

                    TriangularBumper t1, t2, t3;

                    t1 = new TriangularBumper(80, 80, "T1");
                    t2 = new TriangularBumper(30, 30, "T2");
                    t3 = new TriangularBumper(70, 70, "T3");

                    model.addTriangularBumper(t1);
                    model.addTriangularBumper(t2);
                    model.addTriangularBumper(t3);

                    RunGUI window = new RunGUI(model);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
