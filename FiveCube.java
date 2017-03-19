/*
CS560 Group Project
2016
Solves a 3x3x3 cube, ironically
*/
import java.util.LinkedList;

public class FiveCube{
	LinkedList<Vertex>[] adjacent;
	private int time;
	
	//starts the program
	public FiveCube(){
		int[] arr = {0,1,2,3,4,5};
		Graph graph = new Graph(arr);
		AdjList adj = new AdjList(graph.vertices);
		dfs(graph);			//throws nullPointerException until findAdjacent is implemented
		System.out.println((graph.vertices[3]).finish);		//for testing dfs
		System.out.println(((graph.vertices[3]).parent));	//for testing dfs
		printSolution();
	}

	//This is terrible. I made this just to test depth first search
	//we need to improve this class so that it can be used for
	//6 or 7 pieces
	private class AdjList{

		public AdjList(Vertex[] vertArr){
			Vertex a = vertArr[0];
			Vertex b = vertArr[1];
			Vertex c = vertArr[2];
			Vertex d = vertArr[3];
			Vertex e = vertArr[4];
			Vertex f = vertArr[5];

			adjacent = new LinkedList[vertArr.length];

			for(int i=0; i<vertArr.length; i++){
				adjacent[i] = new LinkedList<Vertex>();
			}

			adjacent[a.num].add(a);
			adjacent[a.num].add(b);
			adjacent[a.num].add(c);
			adjacent[a.num].add(d);
			adjacent[a.num].add(e);
			adjacent[a.num].add(f);

			adjacent[b.num].add(b);
			adjacent[b.num].add(a);
			adjacent[b.num].add(c);
			adjacent[b.num].add(d);
			adjacent[b.num].add(e);
			adjacent[b.num].add(f);

			adjacent[c.num].add(c);
			adjacent[c.num].add(a);
			adjacent[c.num].add(b);
			adjacent[c.num].add(d);
			adjacent[c.num].add(e);
			adjacent[c.num].add(f);

			adjacent[d.num].add(d);
			adjacent[d.num].add(a);
			adjacent[d.num].add(b);
			adjacent[d.num].add(c);
			adjacent[d.num].add(e);
			adjacent[d.num].add(f);

			adjacent[e.num].add(e);
			adjacent[e.num].add(a);
			adjacent[e.num].add(b);
			adjacent[e.num].add(c);
			adjacent[e.num].add(d);
			adjacent[e.num].add(f);

			adjacent[f.num].add(f);
			adjacent[f.num].add(a);
			adjacent[f.num].add(b);
			adjacent[f.num].add(c);
			adjacent[f.num].add(d);
			adjacent[f.num].add(e);
		}
	}

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
		public Vertex[] getAdjacent(Vertex v){
			Vertex[] arrVert = new Vertex[adjacent[v.num].size()];
			Vertex[] arr = new Vertex[arrVert.length-1];
			for(int i=0; i<adjacent[v.num].size(); i++){
				arrVert[i] = adjacent[v.num].get(i);
			}

			for (int i=0; i<arr.length; i++)
				arr[i]=arrVert[i+1];

			return arr;
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
		//we can just take the pieces out of each vertex
		public Vertex(int n){/*public Vertex(Piece piece, int n){*/
			num = n;
			color = "";
			parent = null;			
			dist = 0;
			finish = 0;				
			//pc = piece;
		}
	}

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
		for(Vertex v : g.getAdjacent(u)){			
			System.out.println(v.num+"'s color is: "+v.color); //for testing only
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
		int a[] = new int[27];
		for(int i=0; i<27;i++)
			a[i]=i;

		for (int i=0; i<3; i++){
			System.out.println("plane #"+(i+1));
			System.out.println("|--"+a[6+9*i]+"--|--"+a[7+9*i]+"--|--"+a[8+9*i]+"--|");
			System.out.println("|--"+a[3+9*i]+"--|--"+a[4+9*i]+"--|--"+a[5+9*i]+"--|");
			System.out.println("|--"+a[0+9*i]+"--|--"+a[1+9*i]+"--|--"+a[2+9*i]+"--|");
			System.out.println("--------------------------");
		}
	}

	public static void main(String[] args){
		new FiveCube();
	}
}