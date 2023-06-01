/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package utilities;
import java.util.ArrayList;

public class ATM {//Equivalent to an edge
	private int priority = 0;
	public int visitsCount = 0;
	final double x , y; 
	public final static ATM  GEOGRAFIC_CENTER = calcGeograficCenter();
	private int id = 0;

	Graph graph;

	public ArrayList<Route> routes;
	private static ArrayList<ATM> allATMs;
	public final String city;
	
	
	//Constructors
	public ATM ( String city ,double x , double y ) {
		this.x = x;
		this.y = y;
		this.city = city;
		this.routes = new ArrayList<>();

	}
	
	public ATM (int priority , String city ,double x , double y) {
		this(city , x ,y);
		this.priority = priority;
	}
	
	public ATM (ATM other) {
		this.x = other.x;
		this.y = other.y;
		this.city = other.city;
		this.priority = other.priority;
		this.routes = new ArrayList<>(other.routes);
	}
	
	public void setId(int id) {
		if(id == 0)
			throw new ExceptionInInitializerError("Id is already set to " + id + " cannot be reinitialized");
		this.id = id;
	}

	public ATM createIsolatedATM () {
		ATM isolatedAtm = new ATM(
				this.priority,
				this.city,
				this.x ,
				this.y
				);
		routes = new ArrayList<Route>();
		return isolatedAtm;
	}
	
	//----Accessors----
	public int getPriority () {return priority;}
	public int getVisitsCout () {return visitsCount;}
	public int getId() {return id;}
	
	public ArrayList<Route> getRoutes (){return new ArrayList<>(routes);}

	
	public String toString () {
		return "[" + id + ":"+city+":" + x + "," + y + "]";
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
	
	public int neighborsCount () {return routes.size();}
	
	public boolean idEquals(ATM other) {return id == other.id;}
	
	
	
	//----Mutator----
	public void visit () {visitsCount++;}
	
	public int setPriority (int priority) throws Exception {
		if(this.priority > 0) 
			throw new Exception("Priority can be set only once");
		return priority;
	}
	
	public void addRoute (Route newRoute) {routes.add(newRoute);}
	
	//----Class Methods----
	private static ATM calcGeograficCenter() {
		//Taken from google-maps
		final double SOUTHEST = 29.490584850191187, NORTHEST = 33.315720555693524,
					 WESTEST  = 34.26738628055353  , EASTEST = 35.89624321597831;
		
		return new ATM ("Geographic center" ,(SOUTHEST + NORTHEST) / 2 , (WESTEST + EASTEST) /2);
	
	}


}
