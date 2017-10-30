package TradingSystem;

import java.util.HashMap;

public class SellStock extends TradeStock {
	// constructor for selling stock
	public SellStock(String milliSeconds,String symbolName, double fillPrice, double fillSize, HashMap<String,double[]> pnl) {
		super( milliSeconds,symbolName, fillPrice, pnl);
		this.fillSize = fillSize;
		updatePNL();
	}

	@Override
	public void updatePNL() {
		if(pnl.containsKey(symbolName)){
			double[] temp = pnl.get(symbolName);
			temp[0] -= fillSize; // buy more shares
			temp[1] = fillPrice; // update current stock price
			temp[2] +=fillPrice*fillSize; // update total money spent
			temp[3] = temp[0]*temp[1] +temp[2]; //update pnl 
			pnl.put(symbolName, temp);
		} else {
			double[] stockInfo = new double[4];
			stockInfo[0] = -fillSize;  //size of stock (negative)
			stockInfo[1] = fillPrice; // current prize
			stockInfo[2] = fillPrice*fillSize; // total money earned
			stockInfo[3] = 0;  // total pnl
			pnl.put(symbolName, stockInfo);
		}			
	}
}
