package TradingSystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class readFile {
	// read two .gz files 
	public static List<String[]> read(String file1, String file2) throws FileNotFoundException, IOException{
	
	// store all transaction and price update messages from both of the files
	List<String[]> allMessages = new LinkedList<>();
	GZIPInputStream in = null;
	GZIPInputStream in2 = null;
	
	try{
	// take in the fill file
    in =new GZIPInputStream(new FileInputStream(file1));
    InputStreamReader xover = new InputStreamReader(in);
    BufferedReader fillFile = new BufferedReader(xover);
    
    // take in the price update file
    in2 = new GZIPInputStream(new FileInputStream(file2));
    InputStreamReader xover2 = new InputStreamReader(in2);
    BufferedReader pricesFile = new BufferedReader(xover2);
    
    // get the first line of each file
    String fillInfo = fillFile.readLine();
    String pricesInfo = pricesFile.readLine();
    
    // read each line of files until both of files are read to the end
    while(fillInfo!=null || pricesInfo!=null){
    	if(fillInfo == null){
    		String[] pricesRecord = pricesInfo.split(" ");
    		allMessages.add(pricesRecord);  
    		pricesInfo = pricesFile.readLine();
    	} else if (pricesInfo == null){
    		String[] fillRecord = fillInfo.split(" ");
    		allMessages.add(fillRecord);  
    		fillInfo = fillFile.readLine();
    	} else { // when both files are not reaching end
    		String[] pricesRecord = pricesInfo.split(" ");
    		String[] fillRecord = fillInfo.split(" ");
    		// sort two messages by the time stamp 
    		if(Double.parseDouble(pricesRecord[1])>Double.parseDouble(fillRecord[1])){
    			allMessages.add(fillRecord);
    			fillInfo = fillFile.readLine();
    		} else if(Double.parseDouble(pricesRecord[1])<Double.parseDouble(fillRecord[1])){
    			allMessages.add(pricesRecord);  
        		pricesInfo = pricesFile.readLine();
    		} else { // when two messages are at same time stamp
    			allMessages.add(fillRecord);
    			fillInfo = fillFile.readLine();
    			allMessages.add(pricesRecord);  
        		pricesInfo = pricesFile.readLine();
    		}       
    	}
    }
    in.close();
    in2.close(); //close files   
	}
	catch(FileNotFoundException e){
        e.printStackTrace();
        System.out.println(e);		
    }
	catch(IOException e){
		e.printStackTrace();
        System.out.println(e);	
    }
	finally{
			in.close();
			in2.close(); //close files anyway
			}
    return allMessages;
}
}
