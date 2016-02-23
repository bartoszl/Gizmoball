package View;

import model.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/*
 * Demonstrate loading files in the standard format. 
 * Given a test file, your implementation should display 
 * the gizmos specified in that file at the specified 
 * locations on the screen. You should be able to load 
 * and display all the standard gizmos.
 */

public class RunGUI implements Observer {

	private JFrame frame;
	private GridView gridView;

    private GBallModel model;
    private List<iGizmo> gizmos;
    private List<iBall> balls;
    private Controller controller;

	/**
	 * Create the application.
     * @param m
     */
	public RunGUI(GBallModel m) {
        m.addObserver(this);
        this.model = m;
        controller = new Controller(this, model);
		initialize();
        frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gizmo Proto 4");
		frame.setBounds(100, 100, 417, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Model");
		menuBar.add(mnNewMenu);
		
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(controller);
		mnNewMenu.add(load);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem quit = new JMenuItem("Quit");
		mnNewMenu.add(quit);
		
		//drawing
		gridView = new GridView();
		gridView.setBounds(0, 0, 405, 405);
		frame.getContentPane().add(gridView);

        reload();
	}

    public void reload() {
        for(iBall ball : model.getBalls()) {
            int x = (int) ball.getXCoordinate();
            int y = (int) ball.getYCoordinate();
            int radius = (int) ball.getRadius();
            addBallToGrid(x,y,radius, Color.blue);
        }

        for(iAbsorber absorber : model.getAbsorbers()) {
            int leftX = (int) absorber.getLeftXCoordinate();
            int rightX = (int) absorber.getRightXCoordinate();
            int topY = (int) absorber.getTopYCoordinate();
            int bottomY = (int) absorber.getBottomYCoordinate();
            addAbsorberToGrid(leftX, topY, rightX, bottomY, Color.pink);
        }

        for(iGizmo gizmo : model.getGizmos()) {
            if(gizmo instanceof SquareBumper) {
                SquareBumper square = (SquareBumper) gizmo;
                int x = (int) square.getX();
                int y = (int) square.getY();
                addSquareBumperToGrid(x, y, Color.red);
            } else if(gizmo instanceof TriangularBumper) {
                TriangularBumper triangle = (TriangularBumper) gizmo;
                addTriangleBumperToGrid(triangle);
            } else if(gizmo instanceof CircularBumper) {
                CircularBumper circle = (CircularBumper) gizmo;
                int x = (int) circle.getCentreX();
                int y = (int) circle.getCentreY();
                addCircularBumperToGrid(x, y, Color.GREEN);
            } else if(gizmo instanceof Flipper) {
                Flipper flipper = (Flipper) gizmo;
                int x = (int) flipper.getX();
                int y = (int) flipper.getY();
                if(flipper.getOrientation() == FlipperOrientation.LEFT) {
                    addLeftFlipperToGrid(x,y,Color.RED,0);
                } else {
                    addRightFlipperToGrid(x,y,Color.RED,0);
                }
            }
        }
    }

    public void addBallToGrid(int x, int y, int radius, Color color) {
        gridView.addBall(x,y, radius, color);
    }
	
	public void addSquareBumperToGrid(int x, int y, Color color){
		gridView.addSquareBumper(x, y, color);
	}
	
	public void addCircularBumperToGrid(int x, int y, Color color){
		gridView.addCircularBumper(x, y, color);
	}
	
	public void addTriangleBumperToGrid(TriangularBumper triangle){
        gridView.addTriangularBumper(triangle);
	}
	
	public void addLeftFlipperToGrid(int x, int y, Color color, int rotation){
		gridView.addLeftFlipper(x, y, color, rotation);
	}
	
	public void addRightFlipperToGrid(int x, int y, Color color, int rotation){
		gridView.addRightFlipper(x, y, color, rotation);
	}
	
	public void addAbsorberToGrid(int x1, int y1, int x2, int y2, Color color){
		gridView.addAbsorber(x1, y1, x2, y2, color);
	}

	public void update(Observable o, Object arg) {
        gridView.repaint();
	}

	private class GridView extends JPanel{
		List<Attributes> squareBumpers = new ArrayList<Attributes>();
		List<Attributes> circularBumpers = new ArrayList<Attributes>();
		List<Triangle> triangularBumpers = new ArrayList<Triangle>();
		List<Attributes> leftFlippers = new ArrayList<Attributes>();
		List<Attributes> rightFlippers = new ArrayList<Attributes>();
		List<Absorber> absorbers = new ArrayList<Absorber>();
        List<Ball> balls = new ArrayList<>();
		
		public void paintComponent(Graphics g){
			drawGrid(g);
            drawBalls(g);
			drawSquareBumpers(g);
			drawCircularBumpers(g);
			drawTriangularBumpers(g);
            drawFlippers(g);
			drawAbsorber(g);
		}

		private void drawGrid(Graphics g){
			for(int i = 0; i <= 20; i++){
				g.drawLine(0, i*20, 400, i*20);
				g.drawLine(i*20, 0, i*20, 400);
			}
		}
		
		private void drawSquareBumpers(Graphics g) {
			for(Attributes sqBump: squareBumpers){
				g.setColor(sqBump.getColor());
				g.fillRect(sqBump.getXcoordinate(), sqBump.getYcoordinate(), 20, 20);
			}	
		}

        private void drawBalls(Graphics g) {
            for(Ball b : balls) {
                g.setColor(b.getColor());
                g.fillOval(b.getX() * 20 , b.getY() * 20, b.getRadius() * 5, b.getRadius() * 5);
            }
        }

        public boolean addBall(int x, int y, int radius, Color color) {
            return balls.add(new Ball(x,y,radius,color));
        }
		
		public boolean addSquareBumper(int x, int y, Color color){
			return squareBumpers.add(new Attributes(x, y, color, 0));
		}
		
		private void drawCircularBumpers(Graphics g) {
			for(Attributes cBump: circularBumpers){
				g.setColor(cBump.getColor());
				g.fillOval(cBump.getXcoordinate(), cBump.getYcoordinate(), 20, 20);
			}	
		}
		
		public boolean addCircularBumper(int x, int y, Color color){
			return circularBumpers.add(new Attributes(x, y, color, 0));
		}
		
		private void drawTriangularBumpers(Graphics g) {
			for(Triangle tBump: triangularBumpers){
				g.setColor(tBump.getColor());
                g.fillPolygon(tBump.getXcoordinates(), tBump.getYcoordinates(), 3);
			}
		}
		
		public boolean addTriangularBumper(TriangularBumper triangle){
            Triangle t = new Triangle(triangle);
            return triangularBumpers.add(t);
		}

        private void drawFlippers(Graphics g) {
            g.setColor(Color.yellow);
            for(iGizmo gizmo: model.getGizmos()) {
                if(gizmo instanceof Flipper) {
                    Flipper f = (Flipper) gizmo;
                        g.fillOval((int) f.getC1().getCentreX() - 5, (int) f.getC1().getCentreY() - 5, 10, 10);

                        int[] xCoords = new int[] {(int)f.getLeftSide().getP1().getX(),
                                (int) f.getLeftSide().getP2().getX(),
                                (int) f.getRightSide().getP1().getX(),
                                (int) f.getRightSide().getP2().getX()};

                        int[] yCoords = new int[] {(int)f.getLeftSide().getP1().getY(),
                                (int) f.getLeftSide().getP2().getY(),
                                (int) f.getRightSide().getP1().getY(),
                                (int) f.getRightSide().getP2().getY()};

                        g.fillPolygon(xCoords, yCoords, 3);

                        xCoords = new int[] {
                                (int) f.getLeftSide().getP2().getX(),
                                (int) f.getRightSide().getP1().getX(),
                                (int) f.getRightSide().getP2().getX()};

                        yCoords = new int[] {
                                (int) f.getLeftSide().getP2().getY(),
                                (int) f.getRightSide().getP1().getY(),
                                (int) f.getRightSide().getP2().getY()};
                        g.fillPolygon(xCoords, yCoords, 3);
                        g.fillOval((int) f.getC2().getCentreX() - 5, (int) f.getC2().getCentreY() - 5, 10, 10);
                }
            }
        }
		public boolean addLeftFlipper(int x, int y, Color color, int rotation){
			return leftFlippers.add(new Attributes(x, y, color, rotation));
		}
		
		public boolean addRightFlipper(int x, int y, Color color, int rotation){
			return rightFlippers.add(new Attributes(x, y, color, rotation));
		}
		
		private void drawAbsorber(Graphics g) {
			for(Absorber absorb: absorbers){
				g.setColor(absorb.getColor());
				g.fillRect(absorb.getX1coordinate(), absorb.getY1coordinate(), absorb.getWidth(), absorb.getHeight());
			}	
		}
		
		public boolean addAbsorber(int x1, int y1, int x2, int y2, Color color){
			return absorbers.add(new Absorber(x1, y1, x2, y2, color));
		}
	}
	
	private class Attributes{
		int x, y;
		Color color;
		int rotation;
		
		public Attributes(int x,int y, Color color, int rotation){
			this.x=x;
			this.y=y;
			this.color=color;
			this.rotation=rotation%4;
		}
		
		public int getXcoordinate(){
			return x;
		}
		
		public int getYcoordinate(){
			return y;
		}
		
		public Color getColor(){
			return color;
		}
		
		public int getRotation(){
			return rotation;
		}
		
		public void rotate(){
			rotation=(rotation+1)%4;
		}
	}
	
	private class Triangle{
		int xCoordinate, yCoordinate, rotate;
		int[] x = new int[3];
		int[] y = new int[3];
		Color color;
		
		public Triangle(TriangularBumper triangle){
            xCoordinate = (int) triangle.getX();
            yCoordinate = (int) triangle.getY();
            x[0] = (int) triangle.getSideOne().getP1().getX();
            y[0] = (int) triangle.getSideOne().getP1().getY();
            x[1] = (int) triangle.getSideTwo().getP2().getX();
            y[1] = (int) triangle.getSideTwo().getP2().getY();
            x[2] = (int) triangle.getHypotenuse().getP2().getX();
            y[2] = (int) triangle.getHypotenuse().getP2().getY();
		}
		
		public int[] getXcoordinates(){
			return x;
		}
		
		public int[] getYcoordinates(){
			return y;
		}
		
		public Color getColor(){
			return color;
		}
	}

    private class Ball {
        private int x, y, radius;
        Color color;
        public Ball(int x, int y, int radius, Color color) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getRadius() {
            return radius;
        }

        public Color getColor() {
            return color;
        }
    }
	
	private class Absorber{
		int x1, x2, y1, y2;
		Color color;
		
		public Absorber(int x1, int y1, int x2, int y2, Color color){
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.color=color;
		}
		
		public int getX1coordinate(){
			return x1;
		}
		
		public int getY1coordinate(){
			return y1;
		}
		
		public int getWidth(){
			return x2-x1;
		}
		
		public int getHeight(){
			return y2-y1;
		}
		
		public Color getColor(){
			return color;
		}
	}
}
