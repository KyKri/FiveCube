import java.util.*;
public class cs560project
{
    public static int N;  // hint assumes 3x3x3 solution ciube
    public static int P;  // hint assumes 7 pieces in puzzle

    public static int[][][] mapTable ;
    public static int[] xInvTable ;
    public static int[] yInvTable ;
    public static int[] zInvTable ;
    
    public static piece3D[] pieces ;
    
    public static int[] solution ;
    public static boolean continuing ;
    
    public static char[][][] solutionCharMatrix ;

    public static int[] inputs;
    
    //=====================================================================================================
    
    public static void outputSolution( ) 
    {
      //--- fill 3x3x3 char matrix with piece ID's per solution piece placements decoded from solution bitmaps
      
        for ( int whichPiece = 0 ; whichPiece < P ; whichPiece++ )
          {
            char idChar = pieces[whichPiece].pieceID ;
            int integratedBitmap = solution[whichPiece] ;
            for ( int i = 0 ; i < N*N*N ; i++ ) 
              {
                if ( integratedBitmap % 2 == 1 ) 
                  solutionCharMatrix[xInvTable[i]][yInvTable[i]][zInvTable[i]] = idChar ; 
                integratedBitmap >>= 1 ;                          
              }
          }

      //--- output 3x3x3 char matrix showing pieceID's at each location, layer-by-layer in z- direction    
        for ( int z = 2 ; z >= 0 ; z-- ) 
          {
            //System.out.println( ) ;
            System.out.println( "\nz = " + z + " layer\n" ) ;
            //System.out.println( ) ;
            for ( int x = 2 ; x >= 0 ; x-- ) 
              {
                for ( int y = 2 ; y >= 0 ; y-- ) 
                  System.out.print( solutionCharMatrix[x][y][z] ) ;
                System.out.println( ) ;
              }            
          }      
    }
    
    //=====================================================================================================
    
    //TO-DO: Try to implement user managed stack
    public static void DFS ( int level , int priorPartialSolution ) 
    {
      //--- recursive depth-first search to search for solution among all possible piece placement combinations 
        
        if ( level == P ) 
            {
               outputSolution( ) ;
               continuing = false ;      
            }
          else
            {
              bitmapNode ptr = pieces[level].possiblePositionsBitmaps ;
              while ( (ptr != null) && continuing )
                {
                  int overlap = priorPartialSolution & ptr.bitmap ;
                  if ( overlap == 0 ) // no overlap
                    {
                      solution[level] = ptr.bitmap ;
                      DFS ( level+1 , priorPartialSolution | ptr.bitmap ) ;
                    }
                  ptr = ptr.link ;
                }
            }
    }
    
    //=====================================================================================================
    public cs560project(int[] inputs){
      //--- instantiate and compute table for forward and co-inverse bitmap mapping(s)

        N = inputs[0];
        P = inputs[1];

        char[] chrs = {'A','B','C','D','E','F','G'};

        mapTable  = new int[N][N][N] ;
        xInvTable = new int[N*N*N] ;
        yInvTable = new int[N*N*N] ;
        zInvTable = new int[N*N*N] ;
        int count = 0 ;
        for ( int ix = 0 ; ix < N ; ix++ ) 
          for ( int iy = 0 ; iy < N ; iy++ ) 
            for ( int iz = 0 ; iz < N ; iz++ )
              {
                mapTable[ix][iy][iz] = 1 << count ;
                xInvTable[count] = ix ;
                yInvTable[count] = iy ;
                zInvTable[count] = iz ;
                count++ ;
              }

      //--- instantiate solution bitmap vector and solution character matrix  
      
        solution = new int[P] ;
        solutionCharMatrix = new char[N][N][N] ;  
            
      //--- instantiate the pieces comprising the puzzle, as an array of piece3D objects indexed from 0     
            
        pieces = new piece3D[P] ; // for this hint, the puzzle is assumed to use 7 pieces   

        int pieceNum=0;
        int i = 2;
        while (i < inputs.length){
          i++;
          int numCubes = inputs[i++];
          pieces[pieceNum] = new piece3D (chrs[pieceNum], numCubes);
          for (int j=0; j<numCubes; j++){
            pieces[pieceNum].setCube(j, inputs[i++], inputs[i++], inputs[i++]);
          }
          pieceNum++;
        }
        
      //--- compute lists of possible positions for each piece
      
        for ( int whichPiece = 0 ; whichPiece < P ; whichPiece++ ) 
          pieces[whichPiece].findAllPossiblePositionsAsBitmaps( ) ;
        
      //--- DFS to examine all combinations of piece placement for each of seven pieeces to find solution
      
        continuing = true ;
        DFS ( 0 , 0 ) ;
    }
    
    public static void main(String[] args)
    {
      long time = System.currentTimeMillis();
      FileParser parse = new FileParser(args[0]);
      inputs = parse.results;
      new cs560project(inputs);
      long time2 = System.currentTimeMillis();

      System.out.println("\nRuntime in ms: "+(int)(time2-time));
    }
}
