/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package utilities;

import java.util.ArrayList;

public class Graph {
	
	public String graphName = "";
	private int idGenerator = 0;
	private ArrayList <ATM> atms = new ArrayList<ATM>();
	
	
	//----Constructors-----
	public Graph() {}
	
	public Graph(Graph other) {atms = other.getAdjencyList();}
	
	public Graph createSpanningGraph(Graph originalGraph) {
		Graph spanningGraph = new Graph();	
		for(ATM atm : originalGraph.atms)spanningGraph.addATM(atm);
		return spanningGraph;
	}

	
	
	//----Mutators---
	public void addATM (ATM atm) {
		atms.add(atm);
		atm.setId(generatId());
	}
	
	private void resetVisits() {
		for(ATM atm : atms ) {
			atm.visitsCount = 0;
			for (Route route : atm.routes)
				route.isVisited = false;
		}
	}
	
	public void addRoute (int uId , int vId) {new Route(atms.get(vId - 1) , atms.get(uId - 1));}
	
	
	//----Accessors----
	public ArrayList<ATM> getAdjencyList() {
		return new ArrayList<>(atms);
	}
	
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
	


	private ATM inferNext (Route  r , ATM a ) {
		if(r.getU() == a)
			return r.getV();
		if(r.getV() == a)
			return r.getU();
		else 
			throw new IllegalArgumentException("Route must be connected to the atm");
	}
	
	
	
	


}
