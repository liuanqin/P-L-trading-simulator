package TradingSystem;
/** 
 *  Simple Java program to simulate a trading system of stocks
 *  
 * Arg1 is the fills file, which indicates buying or selling the stocks.
 * Arg2 is the price update file, which indicates the price change of the stock at certain time
 * 
 * Output file is the P&L file that has the P&L messages printed for each price update.
 *  
 *  @author Anqing Liu
 *  
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

public class positionservice {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// needs to have two parameters (two files)
		if(args.length != 2)
        {
            System.out.println("Please enter 2 filenames");
            return;
        }
		else{
		//output file contains all the PNL messages
		File fout = new File("PNL message.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		HashMap<String,double[]> pnl = new HashMap<>();
		// read all the messages from both files sorting by time
		List<String[]> allMessages = readFile.read(args[0],args[1]);
		
		// deal with each message
		for(String[] message : allMessages){
			if(message[0].equals("F")){
				if(message[5].equals("B")){
					// buy stock
					new BuyStock(message[1],message[2],Double.parseDouble(message[3]),Double.parseDouble(message[4]),pnl);
				} else {
					// sell stock
					new SellStock(message[1],message[2],Double.parseDouble(message[3]),Double.parseDouble(message[4]),pnl);
				}				
			} else if(message[0].equals("P")){
				// updte price
				String output = new PriceUpdate(message[1],message[2],Double.parseDouble(message[3]),pnl).pnlMessage();
				// write PNL message to the output file
				bw.write(output);
				bw.newLine();
			}
		}
		bw.close(); // close the file
		}
	}
}
