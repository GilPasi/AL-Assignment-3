/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package utilities;
import java.util.ArrayList;

public class ATM {//Equivalent to an edge
	private int priority = 0;
	private boolean isVisited = false;
	final double x , y; 
	public final static ATM  GEOGRAFIC_CENTER = calcGeograficCenter();
	public final int id;
	private static int idGenerator = 1;


	private ArrayList<ATM> neighbors;
	private static ArrayList<ATM> allATMs;
	public final String city;
	
	
	//Constructors
	public ATM ( String city ,double x , double y ) {
		id = ++idGenerator;
		this.x = x;
		this.y = y;
		this.city = city;
		
		
		if(allATMs == null)allATMs = new ArrayList<>();
		allATMs.add(this);
		
	}
	
	public ATM (int priority , String city ,double x , double y) {
		id = idGenerator++;
		this.x = x;
		this.y = y;
		this.city = city;
		this.priority = priority;
		
		if(allATMs == null)allATMs = new ArrayList<>();

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
	
	public double road (ATM other) {
		final double  EXTRA_SPEED = 70 , INTRA_SPEED  = 30 ,//kph
				FILL_TIME = 5,CITIES_TRANSFER_TIME = 6 ;//mins		
		double distance = Math.sqrt(
				Math.pow(x - other.x , 2) 
				+ 
				Math.pow(y - other.y , 2) 	
				);
		double time = 0;
		if(this.city.equals(other.city))
			time = distance / INTRA_SPEED + FILL_TIME;
		else 
			time = distance / EXTRA_SPEED + FILL_TIME + CITIES_TRANSFER_TIME;
		
		
		return time;
	}
	
	
	public double road () { //Overloaded version for a comparison with geographic center
		return road(GEOGRAFIC_CENTER);
	}
	
	public double weight (ATM other) {
		return road(other) + (priority == 0 ? Route.getMaxWeight() : 0);
	}
	
	public double weight () {
		return road(GEOGRAFIC_CENTER) + (priority == 0 ? Route.getMaxWeight() : 0);
	}
	
	public static ArrayList<ATM> getAllATMs() {
		return new ArrayList<>(allATMs);
	}
	
	//Mutator methods
	

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
	
	public static void unvisitAll () {allATMs.forEach(atm->atm.isVisited = false);}
	
	
	//Class Methods
	public static void resetTour () {
		for(ATM atm : allATMs)
			atm.isVisited = false;
	}
	
	private static ATM calcGeograficCenter() {
		//Taken from google-maps
		final double SOUTHEST = 29.490584850191187, NORTHEST = 33.315720555693524,
					 WESTEST  = 34.26738628055353  , EASTEST = 35.89624321597831;
		
		return new ATM ("Geographic center" ,(SOUTHEST + NORTHEST) / 2 , (WESTEST + EASTEST) /2);
	
	}
	

}
