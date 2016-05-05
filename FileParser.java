import java.io.*;
import java.util.*;

public class FileParser{
	int[] results;
	char[] charArr;
	ArrayList<String> strArrList;
	String[] strArr;

	public FileParser(String input){
		readInputFile(input);
	}

	//takes a file name as a string, returns file as an array of ints
	public int[] readInputFile(String fileName){
		File file = new File(fileName);
		String contents = "";
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				contents = contents + scan.nextLine();
			}

			System.out.println(contents);
			charArr = new char[contents.length()];
			charArr = contents.toCharArray();

			results = new int[charArr.length];
			for(int i=0; i<results.length; i++){
				results[i] = Character.getNumericValue(charArr[i]);
			}

			return results;
		}catch (FileNotFoundException E){
			System.out.println("File Not Found");
			return null;
		}

	}

	/*public static void main(String[] args){
		String inputFile = args[0];
		new FileParser(inputFile);
	}*/
}