package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Configurer {
	static final String CONFIGPATHWIN = "./files/configFile.txt";
	
	public ArrayList<String> readConfigFile(ArrayList<String> keys) throws IOException {
		File fileToRead = new File(CONFIGPATHWIN);
		FileReader fileReader = null;
		String line = null;
		ArrayList<String> result = new ArrayList<String>();
		try {
			fileReader = new FileReader(fileToRead);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufferReader = new BufferedReader(fileReader);
		while((line=bufferReader.readLine())!=null){
			for(String key : keys) {
				if(line.startsWith(key)) {
					result.add(line.substring(key.length()-1));
				}
			}
		}	
		return result;
	}	 
	
}
