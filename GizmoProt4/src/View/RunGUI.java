package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * @author Stephen Dundas
 * 
 * Demonstrate loading files in the standard format. 
 * Given a test file, your implementation should display 
 * the gizmos specified in that file at the specified 
 * locations on the screen. You should be able to load 
 * and display all the standard gizmos.
 */

public class RunGUI {

	private JFrame frame;
	private GridView gridView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunGUI window = new RunGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RunGUI() {
		initialize();
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
		mnNewMenu.add(load);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem quit = new JMenuItem("Quit");
		mnNewMenu.add(quit);
		
		//drawing
		gridView = new GridView();
		gridView.setBounds(0, 0, 405, 405);

		//square bumper
		gridView.addSquareBumper(4*20,3*20,new Color(255,0,0));

		//add circle
		gridView.addCircularBumper(5*20,3*20,new Color(0,255,0));

		//add triangle
		gridView.addTriangularBumper(9*20,2*20,new Color(0,0,255),0);
		gridView.addTriangularBumper(8*20,2*20,new Color(0,0,255),1);
		gridView.addTriangularBumper(8*20,3*20,new Color(0,0,255),2);
		gridView.addTriangularBumper(9*20,3*20,new Color(0,0,255),3);
		frame.getContentPane().add(gridView);
		
		//add left flipper
		gridView.addLeftFlipper(0*20,4*20,new Color(200,100,100),0);
		gridView.addLeftFlipper(2*20,4*20,new Color(200,100,100),1);
		gridView.addLeftFlipper(4*20,4*20,new Color(200,100,100),2);
		gridView.addLeftFlipper(6*20,4*20,new Color(200,100,100),3);
		
		//add right flipper
		gridView.addRightFlipper(0*20,7*20,new Color(235,197,93),0);
		gridView.addRightFlipper(2*20,7*20,new Color(235,197,93),1);
		gridView.addRightFlipper(4*20,7*20,new Color(235,197,93),2);
		gridView.addRightFlipper(6*20,7*20,new Color(235,197,93),3);
		
		//add absorber
		gridView.addAbsorber(1*20,12*20,11*20,18*20,new Color(235,93,154));
	}
	
	private class GridView extends JPanel{
		List<Attributes> squareBumpers = new ArrayList<Attributes>();
		List<Attributes> circularBumpers = new ArrayList<Attributes>();
		List<Triangle> triangularBumpers = new ArrayList<Triangle>();
		List<Attributes> leftFlippers = new ArrayList<Attributes>();
		List<Attributes> rightFlippers = new ArrayList<Attributes>();
		List<Absorber> absorbers = new ArrayList<Absorber>();
		
		public void paintComponent(Graphics g){
			drawGrid(g);
			drawSquareBumpers(g);
			drawCircularBumpers(g);
			drawTriangularBumpers(g);
			drawLeftFlippers(g);
			drawRightFlippers(g);
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
		
		public boolean addTriangularBumper(int x, int y, Color color, int rotation){
			return triangularBumpers.add(new Triangle(x, y, color, rotation));
		}
		
		private void drawLeftFlippers(Graphics g) {
			for(Attributes lFlip: leftFlippers){
				g.setColor(lFlip.getColor());
				switch(lFlip.getRotation()){
					case 0: g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate(), 10, 10);
							g.fillRect(lFlip.getXcoordinate(), lFlip.getYcoordinate()+5, 10, 30);
							g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate()+30, 10, 10);
							break;
					case 1:	g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate()+30, 10, 10);
							g.fillRect(lFlip.getXcoordinate()+5, lFlip.getYcoordinate()+30, 30, 10);
							g.fillOval(lFlip.getXcoordinate()+30, lFlip.getYcoordinate()+30, 10, 10);
							break;
					case 2: g.fillOval(lFlip.getXcoordinate()+30, lFlip.getYcoordinate(), 10, 10);
							g.fillRect(lFlip.getXcoordinate()+30, lFlip.getYcoordinate()+5, 10, 30);
							g.fillOval(lFlip.getXcoordinate()+30, lFlip.getYcoordinate()+30, 10, 10);
							break;
					case 3:	g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate(), 10, 10);
							g.fillRect(lFlip.getXcoordinate()+5, lFlip.getYcoordinate(), 30, 10);
							g.fillOval(lFlip.getXcoordinate()+30, lFlip.getYcoordinate(), 10, 10);
							break;
				}
				
			}	
		}
		
		public boolean addLeftFlipper(int x, int y, Color color, int rotation){
			return leftFlippers.add(new Attributes(x, y, color, rotation));
		}
		
		private void drawRightFlippers(Graphics g) {
			for(Attributes rFlip: rightFlippers){
				g.setColor(rFlip.getColor());
				switch(rFlip.getRotation()){
				case 0: g.fillOval(rFlip.getXcoordinate()+30, rFlip.getYcoordinate(), 10, 10);
						g.fillRect(rFlip.getXcoordinate()+30, rFlip.getYcoordinate()+5, 10, 30);
						g.fillOval(rFlip.getXcoordinate()+30, rFlip.getYcoordinate()+30, 10, 10);
						break;
				case 1:	g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate(), 10, 10);
						g.fillRect(rFlip.getXcoordinate()+5, rFlip.getYcoordinate(), 30, 10);
						g.fillOval(rFlip.getXcoordinate()+30, rFlip.getYcoordinate(), 10, 10);
						break;
				case 2: g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate(), 10, 10);
						g.fillRect(rFlip.getXcoordinate(), rFlip.getYcoordinate()+5, 10, 30);
						g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate()+30, 10, 10);
						break;
				case 3:	g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate()+30, 10, 10);
						g.fillRect(rFlip.getXcoordinate()+5, rFlip.getYcoordinate()+30, 30, 10);
						g.fillOval(rFlip.getXcoordinate()+30, rFlip.getYcoordinate()+30, 10, 10);
						break;
				}
			}	
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
		
		public Triangle(int x, int y, Color color, int rotate){
			this.color=color;
			xCoordinate=x;
			yCoordinate=y;
			this.rotate=rotate%4;
			calculate();
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
		
		private void calculate(){
			switch(rotate){
				case 0:	this.x[0]=xCoordinate;
						this.y[0]=yCoordinate;
						this.x[1]=xCoordinate+20;
						this.y[1]=yCoordinate;
						this.x[2]=xCoordinate+20;
						this.y[2]=yCoordinate+20;
						break;	
				case 1:	this.x[0]=xCoordinate;
						this.y[0]=yCoordinate;
						this.x[1]=xCoordinate+20;
						this.y[1]=yCoordinate;
						this.x[2]=xCoordinate;
						this.y[2]=yCoordinate+20;
						break;
				case 2:	this.x[0]=xCoordinate;
						this.y[0]=yCoordinate;
						this.x[1]=xCoordinate+20;
						this.y[1]=yCoordinate+20;
						this.x[2]=xCoordinate;
						this.y[2]=yCoordinate+20;
						break;
				case 3:	this.x[0]=xCoordinate+20;
						this.y[0]=yCoordinate;
						this.x[1]=xCoordinate+20;
						this.y[1]=yCoordinate+20;
						this.x[2]=xCoordinate;
						this.y[2]=yCoordinate+20;
						break;
			}
		}
		
		public void rotate(){
			rotate=(rotate+1)%4;
			calculate();
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
