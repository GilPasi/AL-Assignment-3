package utilities;

import java.util.ArrayList;


public class Journey {
	private ArrayList <Route> routes;
	
	
	//Constructors
	public Journey () {
		routes = new ArrayList<Route>();
	}
	public Journey (ArrayList<Route> routes) {
		this.routes = routes;
	}
	
	public Journey (Journey other) {
		routes = new ArrayList<Route>(other.routes);
	}
	
	//Accessors 
	public String toString () {
		return routes.toString();
	}
	
	public Route getRoute (int index) {return routes.get(index);}
	
	public int size () {return routes.size();}

    private ArrayList<Route> getRoutes() {return new ArrayList<Route>(routes);}
    
    
	//Mutators
	public boolean addRoute(Route newRoute) {
		boolean alreadyExists = routes.contains(newRoute);
		
		//Add the new route any way but report for duplications
		routes.add(newRoute);
		return alreadyExists;
	}
	
    public boolean isDegenerated () { 
    	final int MAX_NEIGBORS_DEGENERATED_TREE = 2;
    	ATM.resetVisitsCount();
    	for(Route r : routes) {
    		r.getU().visit();
    		r.getV().visit();
    	}
    	for(Route r : routes) {
    		if(
    			r.getU().getVisitsCout() > MAX_NEIGBORS_DEGENERATED_TREE
    										||
    			r.getV().getVisitsCout() > MAX_NEIGBORS_DEGENERATED_TREE
    				)
    			return false;

    	}
    return true;
    }
    
    
    //==================All-Journeys Procedure=====================

    

	public static ArrayList<Journey> allPossibleJourneys (){

    	final int N = Route.getAllRoutes().size();
    	final int R = ATM.getAllATMs().size() - 1;
    	
    		
    	ArrayList<Journey> journeys  = new ArrayList<>();
    	allPossibleJourneys(N, R, new Journey(), journeys);
    	journeys = filterNulls(journeys);
    	return journeys;
    }

    
    public static void allPossibleJourneys (int n , int r ,
    		Journey currentJourney , ArrayList<Journey> journeys) {
    	
    	//In that case , the requirements are fulfilled , r edges are selected
    	if(currentJourney.size() == n && countNulls(currentJourney.routes) == n - r )
    		journeys.add(currentJourney);
    	
    	//The end is yet to reach , make another iteration
    	if (currentJourney.size() < n) {
    		//The size indicates how many edges have been examined
    		int index = currentJourney.size();
    		
    		//Split for 2 cases:
    		   Journey newRouteAdded = new Journey(currentJourney);
    		   Journey newRouteIgnored = new Journey(currentJourney);

    		
    		newRouteAdded.addRoute(Route.getAllRoutes().get(index));
    		newRouteIgnored.addRoute(null); //Vital in order to keep the stop condition
    		
    		allPossibleJourneys(n, r, newRouteAdded, journeys);
    		allPossibleJourneys(n, r, newRouteIgnored, journeys);
    	}
    }
    
    public static int countNulls(ArrayList<?> objects) {
    	int counter = 0 ; 
    	
    	for (Object obj : objects)
    		if (obj == null)
    			counter++;
    	return counter;
    }
    
    public static ArrayList<Journey> filterNulls (ArrayList<Journey> list){
    	ArrayList<Journey> filtered = new ArrayList<>();
    	for(int i = 0 ; i < list.size() ; i++) {    		
    		Journey currentList = new Journey();
    		for (int j = 0 ; j < list.get(0).size(); j++)
    			if(list.get(i).getRoute(j) != null) 
    				currentList.addRoute(list.get(i).getRoute(j));
    		
    		filtered.add(currentList);
    	}

        return filtered;
    }
    //=========================================================
	
	

}
