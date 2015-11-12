package com.trading.processing;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.trading.model.TradingDAO;
import com.trading.util.TradingConstants;

/**
 *This is an implementation class for TradingInt which will be used to add trade, 
 *print and view transactions for a given stock.
 * @author SKJHA
 * @version 1.0
 */

public class TradingImpl implements TradingInt{
	
	/** It is assumed that at a time only 1 stock can be purchased or sold **/
	
	@Override
	public void addTrade(String stockName, double qnty,int ind,double tradePrice,ArrayList<TradingDAO> tradeList) {
		if(ind==1)
		{
			tradeList.add(new TradingDAO(stockName, qnty, TradingConstants.BUY_IND, tradePrice,new Date() ));
		}
		else
		{
			tradeList.add(new TradingDAO(stockName, qnty, TradingConstants.SELL_IND, tradePrice,new Date() ));
		}
	}

	/** This method shows the complete list of all types of stocks purchased or sold till now **/
	@Override
	public void printTradeList(ArrayList<TradingDAO> tradeList) {
		
		/** Print in a tabular format for user to view his stock trade**/
		
		System.out.println("\n/******* Following is the Transaction data till now *******/\n");
		for (TradingDAO s : tradeList) 
		{ 
			System.out.println(s);
		}
		
		
	}

	/** This method shows the complete list of given 1 stock purchased or sold till now **/
	@Override
	public void viewMyTrasactionsForStock(String stockName,ArrayList<TradingDAO> tradeList)
	{
		System.out.println("\n/******* Following is the Transaction data till now for this particular Stock*******/\n");
		for (TradingDAO s : tradeList) 
		{ 
			if(stockName.equalsIgnoreCase(s.getStockSymbol()))
				System.out.println(s);
		}
	}
	
	/** This method shows the complete list of given 1 stock purchased or sold within 15 minutes till now **/
	
	public void viewMyTrasactionsForLast15Min(String stockName,ArrayList<TradingDAO> tradeList)
	{
		System.out.println("\n/******* Following is the Transaction data for the last 15 min for this particular Stock*******/\n");
		ArrayList<TradingDAO> tradeListFor15Min= getTransactionListForLast15Min(stockName,tradeList);
		if(tradeListFor15Min.isEmpty())
		{
			System.out.println("Sorry No transactions in Last 15 min");
		}
		else
		{
			for (TradingDAO s : tradeListFor15Min) 
			{ 
				System.out.println(s);
			}
		}
	}
	
	/** Get transactions list for last 15 mints**/
	public ArrayList<TradingDAO> getTransactionListForLast15Min(String stockName,ArrayList<TradingDAO> tradeList)
	{
		ArrayList<TradingDAO> tradeListFor15Min=new ArrayList<TradingDAO>();
		long timestamp1 = new Date().getTime();
		long timestamp2 ;
		
		for (TradingDAO s : tradeList) 
		{ 
			if(stockName.equalsIgnoreCase(s.getStockSymbol()))
			{
				timestamp2=s.getTimestamp().getTime();
				if (Math.abs(timestamp1 - timestamp2) < TimeUnit.MINUTES.toMillis(15)) {
					tradeListFor15Min.add(s);
				}
				
			}
		}
		return tradeListFor15Min;
	}
}
	
