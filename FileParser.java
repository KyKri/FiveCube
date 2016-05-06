import java.io.*;
import java.util.*;

public class FileParser{
	int[] results;
	char[] charArr;
	String[] strArr;

	public FileParser(String input){
		bfRead(input);
	}

	public int[] bfRead(String fileName){
		try{
			BufferedReader bf = new BufferedReader(new FileReader(fileName));
			int[] temp = new int[1000];
			int size = 0;
			String line = "";
			int i = 0;
			try{
				while ((line=bf.readLine())!=null){
					char[] chars = line.toCharArray();
					for(int j=0; j<line.length();j++){
						temp[i++] = Character.getNumericValue(chars[j]);
						size++;
					}
				}
				results = new int[size];
				for(int x=0; x<size; x++)
					results[x] = temp[x];
				return results;
			}catch(IOException E){
				System.out.println("ran into IOException");
				return null;
			}
		}catch(FileNotFoundException E){
			System.out.println("File not found");
			return null;
		}
	}
	/*
	public static void main(String[] args){
		new FileParser(args[0]);
	}*/
}