package TradingSystem;

import java.util.HashMap;

public abstract class TradeStock {
	//common properties for buying and selling stocks
	 String symbolName;
	 double fillPrice;
	 double fillSize;
	 String milliSeconds;
	
	// stores pnl info for each stock
	//double[] contains size of stock, current price, total money spent(negative means buy) and total pnl in order.
	 HashMap<String,double[]> pnl;
	
	//constructor to set base properties 
	public TradeStock(String milliSeconds,String symbolName, double fillPrice,HashMap<String,double[]> pnl) {
		this.milliSeconds = milliSeconds;
		this.symbolName = symbolName;
		this.fillPrice = fillPrice;
		this.pnl = pnl;
		
	}
	// for each line of message, we need to update the size, current prize, total money spent and PNL
	public abstract void updatePNL();	
}
