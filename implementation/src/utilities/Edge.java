package utilities;

public class Edge {
	
	private int v , u ;
	boolean isVisited = false;
	private double weight;
	
	public Edge (int u , int v ){
		this.v = v ;
		this.u = u;
		this.weight = 1;
	}
	
	public Edge (int u , int v , double weight){
		this.v = v ;
		this.u = u;
		this.weight = weight;
	}
	
	public Edge (Edge other) {
		this.v = other.v;
		this.u = other.u;
		this.weight = other.weight;

	}

	public int getV() {return v;}
	public int getU() {return u;}
	public double getWeight () {return weight;}
	
	public boolean equals(Edge other) {
		return(
		(this.v == other.v) && (this.u == other.u)
					||
		(this.v == other.u) && (this.u == other.v)
				);
	}
	
	public String toString () {return "(" + v + ", " + u + ")";}

}
