package com.trading.processing;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
/**
 *This is helper class which will be used to update the ticker price with new random price 
 *and old price is replaced with current price after every 1 minute.
 * @author SKJHA
 * @version 1.0
 */


public class TradingUpdate extends TimerTask{
	
	HashMap<String, ArrayList<Double>>  tickerPriceList;
	
	public TradingUpdate(HashMap<String, ArrayList<Double>>  tickerPriceListFromStockExch)
	{
		tickerPriceList= tickerPriceListFromStockExch;
	}

	@Override
	public void run() {
		for(String stock : tickerPriceList.keySet())
		{
			double rand = 100.0 * Math.random();
			tickerPriceList.get(stock).set(0, tickerPriceList.get(stock).get(tickerPriceList.get(stock).size()-1));
			tickerPriceList.get(stock).set(tickerPriceList.get(stock).size()-1, Double.parseDouble(new DecimalFormat("##.##").format(rand)));
		}	
	}
}
