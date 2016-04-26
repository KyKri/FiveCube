/*
CS560 Group Project
2016

Solves a 5x5x5 cube
*/

public class FiveCube{
	private int time;
	
	//starts the program
	public FiveCube(){
		int[] arr = {1,2,3,4,5};
		Graph graph = new Graph(arr);
		for(Vertex v : graph.vertices)
			System.out.println(v.num);
		dfs(graph);			//throws nullPointerException until findAdjacent is implemented
		//System.out.println((graph.vertices[0]).finish);		//for testing dfs
		//System.out.println(((graph.vertices[0]).parent).num);	//for testing dfs
		//printSolution();
	}

	/*
	//makes the cube 5x5x5 that will be filled
	public makeCube(){

	}

	//makes the 25 pieces to play with
	public makePieces(){

	}

	//this is the algorithm that will assemble the pieces
	public fillCube(){

	}

	//finds all possible moves
	public makeMove(){

	}

	//helps makeMove find all orientations of each piece
	public findOrientations(){

	}
	*/

	//!!!Note: Graph and Vertex class may change depending on how
	//cube and pieces are represented
	//Graph and Vertex may be dependent on them
	private class Graph{
		Vertex[] vertices;
		int numVerts;

		public Graph(int[] nums){
			vertices = new Vertex[nums.length];
			for (int i=0; i<nums.length; i++)
				vertices[i] = new Vertex(nums[i]);
			numVerts = nums.length;
		}

		//TO-DO Implement adjacency list and accessor method
		public Vertex[] findAdjacent(Vertex v){
			return null;
		}
	}

	//!!!Note: Graph and Vertex class may change depending on how
	//cube and pieces are represented
	//Graph and Vertex may be dependent on them
	private class Vertex{
		int num;
		String color;
		Vertex parent;
		int dist;
		int finish;
		//Piece pc;					//The piece represented by this Vertex

		//Vertex will encapsulate each piece, this is so that each piece can be
		//neatly fit into the graph
		//once depth first search finds the correct order of vertices,
		//we can just take the pieces our of each vertex
		public Vertex(int n){/*public Vertex(Piece piece, int n){*/
			num = n;
			color = "";
			parent = null;			
			dist = 0;
			finish = 0;				
			//pc = piece;
		}
	}

	/* !!!NOTE: Not complete yet!!! Not even tested yet*/
	//How to use results: (Disclaimer: this is in theory) 
	//find Vertex with least finish time and follow the parents
	//backwards to assemble cube
	//least finish time means it was finished first
	public void dfs(Graph g){
		for (Vertex u : g.vertices){
			u.color = "white";
			u.parent = null;
		}

		time = 0;
		for (Vertex u : g.vertices)
			if(u.color.equals("white")){
				System.out.println("Now Searching "+u.num);		//for testing only
				dfsVisit(g,u);
			}
	}

	public void dfsVisit(Graph g, Vertex u){
		time += 1;
		u.dist = time;
		u.color = "gray";
		for(Vertex v : g.findAdjacent(u)){			//!!!Note: findAdjacent not yet implemented
			if (v.color == "white"){
				v.parent = u;
				dfsVisit(g,v);
			}
			u.color = "black";
			time+=1;
			u.finish = time;
		}
	}

	//prints out a matrix representation of the pieces in the cube
	public void printSolution(){
		int a[] = new int[125];
		for(int i=0; i<125;i++)
			a[i]=i;

		for (int i=0; i<5; i++){
			System.out.println("plane #"+(i+1));
			System.out.println("|--"+a[20+25*i]+"--|--"+a[21+25*i]+"--|--"+a[22+25*i]+"--|--"+a[23+25*i]+"--|--"+a[24+25*i]+"--|");
			System.out.println("|--"+a[15+25*i]+"--|--"+a[16+25*i]+"--|--"+a[17+25*i]+"--|--"+a[18+25*i]+"--|--"+a[19+25*i]+"--|");
			System.out.println("|--"+a[10+25*i]+"--|--"+a[11+25*i]+"--|--"+a[12+25*i]+"--|--"+a[13+25*i]+"--|--"+a[14+25*i]+"--|");
			System.out.println("|--"+a[5+25*i]+"--|--"+a[6+25*i]+"--|--"+a[7+25*i]+"--|--"+a[8+25*i]+"--|--"+a[9+25*i]+"--|");
			System.out.println("|--"+a[0+25*i]+"--|--"+a[1+25*i]+"--|--"+a[2+25*i]+"--|--"+a[3+25*i]+"--|--"+a[4+25*i]+"--|");
			System.out.println("------------------------------------");
		}
	}

	public static void main(String[] args){
		new FiveCube();
	}
	
}