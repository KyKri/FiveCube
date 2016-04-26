/*
CS560 Group Project
2016

Solves a 5x5x5 cube
*/

public class FiveCube{
	
	//starts the program
	public FiveCube(){
		printSolution();
		//makeCube();
		//makePieces();
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
			System.out.println("-------------------------------");
		}
	}

	public static void main(String[] args){
		new FiveCube();
	}
	
}