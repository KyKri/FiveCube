public class cs560project {

	public static final int N = 3;
	public static int[][][] mapTable;
	public static int[] xInvTable;
	public static int[] yInvTable;
	public static int[] zInvTable;

	public cs560project(){
		mapTable = new int[N][N][N];
		xInvTable = new int[N*N*N];
		yInvTable = new int[N*N*N];
		zInvTable = new int[N*N*N];
		int count = 0;
		for (int ix=0; ix<N; ix++)	
			for (int iy=0; iy<N; iy++)
				for (int iz=0; iz<N; iz++)	{
					mapTable[ix][iy][iz] = 1 << count;
					xInvTable[count] = ix;
					yInvTable[count] = iy;
					zInvTable[count] = iz;
					count++;
					}

		piece3D a = new piece3D('A',4);
		p.setCube(0,0,0,0);
		p.setCube(1,0,1,0);
		p.setCube(2,0,1,1);
		p.setCube(3,0,0,1);
		p.findAllPossiblePositionsAsBitmaps();

		piece3D b = new piece3D('B',4);
		p.setCube(0,0,0,0);
		p.setCube(1,1,0,0);
		p.setCube(2,1,0,1);
		p.setCube(3,2,0,1);
		p.findAllPossiblePositionsAsBitmaps();

		piece3D c = new piece3D('C',3);
		p.setCube(0,0,0,0);
		p.setCube(1,0,0,1);
		p.setCube(2,1,0,0);
		p.findAllPossiblePositionsAsBitmaps();

		piece3D d = new piece3D('D',5);
		p.setCube(0,0,0,0);
		p.setCube(1,0,0,1);
		p.setCube(2,1,0,1);
		p.setCube(3,0,1,1);
		p.setCube(4,1,1,1);
		p.findAllPossiblePositionsAsBitmaps();

		piece3D e = new piece3D('E',5);
		p.setCube(0,0,0,0);
		p.setCube(1,0,1,0);
		p.setCube(2,1,0,0);
		p.setCube(3,0,1,1);
		p.setCube(4,1,0,1);
		p.findAllPossiblePositionsAsBitmaps();

		piece3D f = new piece3D('F',3);
		p.setCube(0,0,0,0);
		p.setCube(1,1,0,0);
		p.setCube(2,2,0,0);
		p.findAllPossiblePositionsAsBitmaps();
	
		piece3D g = new piece3D('G',3);
		p.setCube(0,0,0,0);
		p.setCube(1,0,1,0);
		p.setCube(2,0,2,0);
		p.findAllPossiblePositionsAsBitmaps();
	}
	
	public static void main(String[] args){
		new cs560project();
	}

	private class bitmapNode {
	int bitmap;
	bitmapNode link;
	
	public bitmapNode()	{
		bitmap = 0;
		link = null;
	}

	public bitmapNode(int map)	{
		bitmap = map;
		link = null;
		}
	}

	private class piece3D {

	point3D[] cubes;
	int nCubes;
	char pieceID;
	bitmapNode possiblePositionsBitmaps;
	int xMin, xMax, xPan;
	int yMin, yMax, yPan;
	int zMin, zMax, zPan;
	
	public piece3D(char ID, int size)	{
		nCubes = size;
		pieceID = ID;
		cubes = new point3D[size];
		for (int i=0; i<size; i++)
			cubes[i] = new point3D();
		possiblePositionsBitmaps = null;
		}
	
	public void setCube(int whichCube, int ix, int iy, int iz)	{
		cubes[whichCube].x = ix;
		cubes[whichCube].y = iy;
		cubes[whichCube].z = iz;
		}
	
	public piece3D applyIsometry(int isometryNumber)	{
		piece3D p = new piece3D(pieceID, nCubes);
		
		for (int whichCube=0; whichCube<p.nCubes; whichCube++)	{
			int x = cubes[whichCube].x;
			int y = cubes[whichCube].y;
			int z = cubes[whichCube].z;
			switch (isometryNumber)	{
				case 1: p.cubes[whichCube].x = x;
						p.cubes[whichCube].y = y;
						p.cubes[whichCube].z = z;
						break;
				case 2: p.cubes[whichCube].x = y;
						p.cubes[whichCube].y = x;
						p.cubes[whichCube].z = z;
						break;
				case 3: p.cubes[whichCube].x = z;
						p.cubes[whichCube].y = x;
						p.cubes[whichCube].z = y;
						break;
				case 4: p.cubes[whichCube].x = -y;
						p.cubes[whichCube].y = x;
						p.cubes[whichCube].z = z;
						break;
				case 5: p.cubes[whichCube].x = -z;
						p.cubes[whichCube].y = y;
						p.cubes[whichCube].z = x;
						break;
				case 6: p.cubes[whichCube].x = -x;
						p.cubes[whichCube].y = z;
						p.cubes[whichCube].z = y;
						break;
				case 7: p.cubes[whichCube].x = -x;
						p.cubes[whichCube].y = -y;
						p.cubes[whichCube].z = z;
						break;
				case 8: p.cubes[whichCube].x = -y;
						p.cubes[whichCube].y = -z;
						p.cubes[whichCube].z = x;
						break;
				case 9: p.cubes[whichCube].x = -z;
						p.cubes[whichCube].y = -x;
						p.cubes[whichCube].z = y;
						break;
				case 10:p.cubes[whichCube].x = y;
						p.cubes[whichCube].y = -x;
						p.cubes[whichCube].z = z;
						break;
				case 11:p.cubes[whichCube].x = z;
						p.cubes[whichCube].y = -y;
						p.cubes[whichCube].z = x;
						break;
				case 12:p.cubes[whichCube].x = x;
						p.cubes[whichCube].y = -z;
						p.cubes[whichCube].z = y;
						break;
				case 13:p.cubes[whichCube].x = x;
						p.cubes[whichCube].y = z;
						p.cubes[whichCube].z = -y;
						break;
				case 14:p.cubes[whichCube].x = y;
						p.cubes[whichCube].y = x;
						p.cubes[whichCube].z = -z;
						break;
				case 15:p.cubes[whichCube].x = z;
						p.cubes[whichCube].y = y;
						p.cubes[whichCube].z = -x;
						break;
				case 16:p.cubes[whichCube].x = -z;
						p.cubes[whichCube].y = x;
						p.cubes[whichCube].z = -y;
						break;
				case 17:p.cubes[whichCube].x = -x;
						p.cubes[whichCube].y = y;
						p.cubes[whichCube].z = -z;
						break;
				case 18:p.cubes[whichCube].x = -y;
						p.cubes[whichCube].y = z;
						p.cubes[whichCube].z = -x;
						break;
				case 19:p.cubes[whichCube].x = -x;
						p.cubes[whichCube].y = -z;
						p.cubes[whichCube].z = -y;
						break;
				case 20:p.cubes[whichCube].x = -z;
						p.cubes[whichCube].y = -x;
						p.cubes[whichCube].z = -y;
						break;
				case 21:p.cubes[whichCube].x = -y;
						p.cubes[whichCube].y = -x;
						p.cubes[whichCube].z = -z;
						break;
				case 22:p.cubes[whichCube].x = z;
						p.cubes[whichCube].y = -x;
						p.cubes[whichCube].z = -y;
						break;
				case 23:p.cubes[whichCube].x = y;
						p.cubes[whichCube].y = -z;
						p.cubes[whichCube].z = -x;
						break;
				case 24:p.cubes[whichCube].x = x;
						p.cubes[whichCube].y = -y;
						p.cubes[whichCube].z = -z;
						break;
				}
			}
		return p;
		}
	
	public void normalizePiece()	{
		xMin = 999; xMax = -999;
		yMin = 999; yMax = -999;
		zMin = 999; zMax = -999;
		
		for (int whichCube=0; whichCube<nCubes; whichCube++)	{
			if (cubes[whichCube].x < xMin) xMin = cubes[whichCube].x;
			if (cubes[whichCube].y < yMin) yMin = cubes[whichCube].y;
			if (cubes[whichCube].z < zMin) zMin = cubes[whichCube].z;
			if (cubes[whichCube].x > xMax) xMax = cubes[whichCube].x;
			if (cubes[whichCube].y > yMax) yMax = cubes[whichCube].y;
			if (cubes[whichCube].z > zMax) zMax = cubes[whichCube].z;
			}
		
		for (int whichCube=0; whichCube<nCubes; whichCube++)	{
			cubes[whichCube].x -= xMin;
			cubes[whichCube].y -= yMin;
			cubes[whichCube].z -= zMin;
			}
		
		xPan = cs560project.N - (xMax - xMin +1);
		yPan = cs560project.N - (yMax - yMin +1);
		zPan = cs560project.N - (zMax - zMin +1);
		}
	
	public void findAllPossiblePositionsAsBitmaps()	{
		int positionIndex = 0;
		for (int whichIsometry=0; whichIsometry<=24; whichIsometry++)	{
			piece3D q = applyIsometry(whichIsometry);
			q.normalizePiece();
			for (int dx=0; dx <= q.xPan; dx++)
				for (int dy=0; dy <= q.yPan; dy++)
					for (int dz=0; dz <= q.zPan; dz++)	{
						bitmapNode b = new bitmapNode();
						for (int whichCube=0; whichCube<q.nCubes; whichCube++)	{
							int x = q.cubes[whichCube].x + dx;
							int y = q.cubes[whichCube].y + dy;
							int z = q.cubes[whichCube].z + dz;
							b.bitmap |= cs560project.mapTable[x][y][z];
							}
						if (possiblePositionsBitmaps == null)
							possiblePositionsBitmaps = b;
						else	{
							bitmapNode p;
							p = possiblePositionsBitmaps;
							while ((p.link != null) && (p.bitmap != b.bitmap))
								p = p.link;
							if (p.bitmap != b.bitmap)	{
								positionIndex++;
								System.out.println("======= " + positionIndex);
								
								p.link = b;
								System.out.println("integrated bitmap " + b.bitmap);
								}
							}
						}
			}
		}

	}


	public class point3D {
	
	int x;
	int y;
	int z;
	
	public point3D()	{
		}
	
	public point3D(int ix, int iy, int iz)	{
		x=ix;
		y=iy;
		z=iz;
		}

	}	
}
