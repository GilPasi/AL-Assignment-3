/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package utilities;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	public String graphName = "";
	private int idGenerator = 0;
	private ArrayList <ATM> atms = new ArrayList<ATM>();
	private ArrayList <Route> routes = new ArrayList<>();
	private List <Edge> edges = new ArrayList<>();
	
	

	
	
	//----Constructors-----
	public Graph() {}
	
	public Graph(Graph other) {
		this();
		for(int i = 0 ; i <  other.atms.size() ; i++)
			addATM(other.atms.get(i).createIsolatedATM());
		
		
		for(int i = 0 ; i <  other.atms.size() ; i++) {
			ATM currentATMOriginal = other.atms.get(i);
			ATM currentATMNew = atms.get(i);

			ArrayList<Route> currentRoutesOriginal = new ArrayList<>(currentATMOriginal.routes);
			ArrayList<Route> currentRoutesNew = new ArrayList<>();

			do {//Check all the routes:
				Route testedRoute = currentRoutesOriginal.remove(0);
				
				//If the route already exists, do not add it a second time
				for(int j = 0 ; j < currentRoutesNew.size() ; j++) {
					Route r = currentRoutesNew.get(j);

					if(r.equals(testedRoute))
						continue;
				}
				
				//Otherwise add the route
				int indexOfParalelU = testedRoute.getU().getId() - 1;
				int indexOfParalelV = testedRoute.getV().getId() - 1;

				new Route(atms.get(indexOfParalelU),atms.get(indexOfParalelV));
				
			}while(!currentRoutesOriginal.isEmpty());
			
			
			
		}
		
	}
	
	public Graph createSpanningGraph(Graph originalGraph) {
		Graph spanningGraph = new Graph();	
		for(ATM atm : originalGraph.atms)spanningGraph.addATM(atm.createIsolatedATM());
		return spanningGraph;
	}

	
	
	//----Mutators---
	public void addATM (ATM atm) {
		atms.add(new ATM(atm));
		atm.setId(generatId());
	}
	
	private void resetVisits() {
		for(ATM atm : atms ) {
			atm.visitsCount = 0;
			for (Route route : atm.routes)
				route.isVisited = false;
		}
	}
	
	public void addRoute (int uId , int vId) {
		routes.add(new Route(atms.get(vId - 1) , atms.get(uId - 1)));
		}
	
	public void addEdge (Edge e) {edges.add(e);}
	
	
	//----Accessors----
	public int generatId () {return ++idGenerator;}
	
	public String toString () {
		String ret = "Graph " + graphName + ":\n";
		for(int i = 0 ; i < atms.size() ; i++)
			ret += "(" + atms.get(i).getId() + ")-" + atms.get(i).getRoutes() + "\n";
		return ret;
	}
	
	public boolean isDegenerated () {
		//First condition : no more than two routes for each atm
		final int MAX_NEIGHBORS = 2;
		
		for(ATM atm : atms)
			if(atm.neighborsCount()  > MAX_NEIGHBORS)return false;
		
		resetVisits();
		
		ATM currentAtm = atms.get(0);
		
		for(int i = 0 ;i < atms.size()  ; i ++) { //There are |V|-1 edges in a degenerated graph
			ArrayList<Route> currentRoutes = new ArrayList<>(currentAtm.routes);
			
			currentAtm.visit();
			//Examine each route
			do {
				Route testedRoute = currentRoutes.remove(0);
				
				if (!testedRoute.isVisited) {
					//Visit and move for the next atm
					testedRoute.isVisited = true;
					currentAtm = inferNext(testedRoute, currentAtm);
					break;
				}
				
			}while(!currentRoutes.isEmpty());
			
		}
		
		//Make sure all routes have been visited
		for(int i = 0 ; i < atms.size(); i++) {
			ATM a = atms.get(i);
			if(a.visitsCount == 0)return false;
			
			ArrayList<Route> rts = new ArrayList<>(a.routes);
			while (!rts.isEmpty()) 
				if (!rts.remove(0).isVisited)return false;
			}
		
		return true;
	}
	
	
    
    //==================All-Journeys Procedure====================

//	public Graph allJourneys (){
//		/**A journey is a Graph with precisely |V|-1 edges. Meaning it has
//		 * a potential to be a path (simple spanning graph).
//		 * This method finds all of them */
//
//    	final int N = routes.size();
//    	final int R = atms.size() - 1;
//    	ArrayList <Graph>spanninGraphs = new ArrayList<Graph>();
//    	allJourneys(N, R, new Graph() ,spanninGraphs );
//    	
//    	journeys = filterNulls(journeys);
//    	return journeys;
//    }

    
//    public void allJourneys (int n , int r ,Graph currentGraph , ArrayList<Graph> validGraphs ) {
//    	
//    	//In that case , the requirements are fulfilled , r edges are selected
//    	ArrayList<Route> curRoutes = currentGraph.routes; 
//    	
//    	if(curRoutes.size() == n && countNulls(curRoutes) == n - r )
//    		validGraphs.add(currentGraph);
//    	
//    	//The end is yet to reach , make another iteration
//    	if (curRoutes.size() < n) {
//    		//The size indicates how many edges have been examined
//    		int index = curRoutes.size();
//    		
//    		//Split for 2 cases:
//    		   Journey newRouteAdded = new Journey(curRoutes);
//    		   Journey newRouteIgnored = new Journey(curRoutes);
//
//    		
//    		newRouteAdded.addRoute(this.routes.get(index));
//    		newRouteIgnored.addRoute(null); //Vital in order to keep the stop condition
//    		
//    		allPossibleJourneys(n, r, newRouteAdded, journeys);
//    		allPossibleJourneys(n, r, newRouteIgnored, journeys);
//    	}
//    }
    
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
	
	


	private ATM inferNext (Route  r , ATM a ) {
		if(r.getU() == a)
			return r.getV();
		if(r.getV() == a)
			return r.getU();
		else 
			throw new IllegalArgumentException("Route must be connected to the atm");
	}
	

	
	


}
