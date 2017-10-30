package TradingSystem;

import java.util.HashMap;

public class PriceUpdate extends TradeStock {
	// constructor for price update, does not have parameter fillSize
	public PriceUpdate(String milliSeconds,String symbolName, double fillPrice, HashMap<String,double[]> pnl) {
		super(milliSeconds,symbolName, fillPrice, pnl);
		updatePNL();
	}
	
	@Override
	public void updatePNL() {
		if(pnl.containsKey(symbolName)){
			double[] temp = pnl.get(symbolName);
			temp[1] = fillPrice; // update current stock price;
			temp[3] = temp[0]*temp[1] +temp[2]; //update pnl 

			pnl.put(symbolName, temp);
			}
		
	}
	// append several components to construct a PNL message 
	public String pnlMessage(){
		if(pnl.containsKey(symbolName)){
		StringBuilder sb = new StringBuilder();
		sb.append("PNL ");  // message type
		sb.append(milliSeconds+ " "); // time
		sb.append(symbolName + " ");  // symbol name
		sb.append((int)(pnl.get(symbolName)[0]) + " "); // size of current stock owned
		sb.append(String.format("%.2f",pnl.get(symbolName)[3]));  // total P&L value of the stock
		return sb.toString();
		}
		return null;
	}
	}

