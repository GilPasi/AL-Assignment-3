package utilities;

public class Path {
	//Equivalent to an edge on a graph
	
	
	private int v , u ;
	boolean isVisited = false;
	private double weight;
	
	
	//----Constructors----
	public Path (int v , int u ){
		this.v = v ;
		this.u = u;
		this.weight = 1;
	}
	
	public Path (int u , int v , double weight){
		this.v = v ;
		this.u = u;
		this.weight = weight;
	}
	
	public Path (Path other) {
		this.v = other.v;
		this.u = other.u;
		this.weight = other.weight;

	}

	//----Accessors----
	public int getV() {return v;}
	public int getU() {return u;}
	public double getWeight () {return weight;}
	
	public boolean equals(Path other) {
		return(
		(this.v == other.v) && (this.u == other.u)
					||
		(this.v == other.u) && (this.u == other.v)
				);
	}
	
	public String toString () {return "(" + v + ", " + u + ")";}


}	
