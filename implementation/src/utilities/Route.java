/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package utilities;

import java.util.ArrayList;

public class Route { //Equivalent to an edge
	private ATM u , v;
	private double weight , time ;
	private static double maxWeight = Double.MIN_VALUE;
	private static ArrayList<Route> allRoutes;
	
	public Route (ATM s , ATM d) {
		u = s;
		v = d;

		
		//Order is important!
		time = u.road(v);
		maxWeight = Math.max(time, maxWeight);//Update if necessary
		time = u.weight(v);
		
		if(allRoutes == null)
			allRoutes = new ArrayList<Route>();
		allRoutes.add(this);
	}

	public ATM getU() {
		return u;
	}

	public ATM getV() {
		return v;
	}

	public static ArrayList<Route> getAllRoutes() {
		return new ArrayList<>(allRoutes);
	}


	public double getWeight() {
		return weight;
	}


	public double getTime() {
		return time;
	}

	public static double getMaxWeight() {
		return maxWeight;
	}
	
	public String toString() {
		return "(" + u.id +"," + v.id + ")";
	}



}
