package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Martin Peev
 *
 */
public class TeleporterConnection {
	private Bumper tp1;
	private Bumper tp2;
	
	/**
	 * Constructor for the TeleporterConnection class.
	 * @param tp12 - one end of the connection
	 * @param tp22 - the other end of the connection
	 */
	public TeleporterConnection(Bumper tp12, Bumper tp22){
		this.tp1=tp12;
		this.tp2=tp22;
	}
	
	/**
	 * Getter method for the connection.
	 * @return - list containing the two ends of the teleporter connection
	 */
	public List<Bumper> getConnection(){
		List<Bumper> list = new ArrayList<Bumper>();
		list.add(tp1);
		list.add(tp2);
		return list;
	}
	
	/**
	 * Overridden equals method for the class.
	 */
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof TeleporterConnection)) {
            return false;
        }
        TeleporterConnection tpConnect = (TeleporterConnection) object;
        if((tpConnect.getConnection().get(0).getCircles().equals(tp1.getCircles())
        		&& tpConnect.getConnection().get(1).getCircles().equals(tp2.getCircles()))||
	                (tpConnect.getConnection().get(0).getCircles().equals(tp2.getCircles())
	                		&& tpConnect.getConnection().get(1).getCircles().equals(tp1.getCircles()))){
            return true;
        }
        else {
            return false;
        }
    }
}
