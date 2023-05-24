/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package utilities;
import java.util.ArrayList;

public class ATM {
	private int priority = 0;
	private boolean isVisited = false;
	final double x , y; 
	public final int id;
	private static int idGenerator = 1;
	private ArrayList<ATM> neighbors;
	public static ArrayList<ATM> allATMs = new ArrayList<>();
	public final String city;
	
	
	//Constructors
	public ATM ( String city ,double x , double y ) {
		id = idGenerator++;
		this.x = x;
		this.y = y;
		this.city = city;
		allATMs.add(this);
	}
	
	public ATM (int priority , String city ,double x , double y) {
		id = idGenerator++;
		this.x = x;
		this.y = y;
		this.city = city;
		this.priority = priority;
		allATMs.add(this);
	}
	
	//Accessor methods
	public int getPriority () {return priority;}
	public boolean getIsVisted () {return isVisited;}
	
	public String toString () {
		return "[" + id + ":"+city+":" + x + "," + y + "]";
	}
	
	public static void printAll() {
		for(ATM atm : allATMs)
			System.out.println(atm);
	}
	
	
	//Mutator methods
	public static void resetTour () {
		for(ATM atm : allATMs)
			atm.isVisited = false;
	}
	
	public double distance (ATM other) { //Equivalent to 'road()' method
		return Math.sqrt(
			   Math.pow(x - other.x , 2) 
			   			+ 
			   	Math.pow(y - other.y , 2) 	
				);
	}

	public void visit () {isVisited = true;}
	
	public int setPriority (int priority) throws Exception {
		if(this.priority > 0) 
			throw new Exception("Priority can be set only once");
		return priority;
	}
	
	public void addNeighbor (ATM neighbor) throws Exception {
		if(neighbors.contains(neighbor))
			throw new Exception("Try to add an existing neighbor");
		
		neighbors.add(neighbor);
	}
	

}
