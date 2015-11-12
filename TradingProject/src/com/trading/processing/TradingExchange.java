package com.trading.processing;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import com.trading.model.TradingDAO;
import com.trading.model.TradingModel;
import com.trading.util.StockName;
import com.trading.util.TradingConstants;

/**
 *This is a trade processing class for trading exchange which initialise predefined values, viewing trading list.
 * @author SKJHA
 * @version 1.0
 */
public class TradingExchange{

	Scanner in = new Scanner(System.in);
	String selectedStock;
	TradingImpl tradeImpl = new TradingImpl();
	TradingCalImpl calcImpl = new TradingCalImpl();
	private HashMap<String, ArrayList<Double>> tickerPriceList = new HashMap<String, ArrayList<Double>>(); 
	private ArrayList<TradingDAO> tradeList = new ArrayList<TradingDAO>();
	private HashMap<String, TradingModel> stockList= new HashMap<String, TradingModel>();
	
	public HashMap<String, ArrayList<Double>> getTickerPriceList() {
		return tickerPriceList;
	}
	
	
	/** initialise the list with predefined value and show List of stocks to User for trading **/
	public void initializeStockExchange()
	{
		populateLists();
		viewStockList();
		
	}
	
	public void populateLists()
	{
		/** Populate the stock List with predefined values **/
		populateStocklist();
		
		/** Populate sample Transactions**/
		populateTrasactionlist();
		
		/**Populate Ticker Prices - Old and New (both are assumed as par values). **/
		populateTickerlist();

	}
	
	public void populateStocklist() {
		stockList.put(StockName.NAME_TEA, new TradingModel(StockName.NAME_TEA, TradingConstants.TYPE_COMMON, 0, 0.0, 100));
		stockList.put(StockName.NAME_POP, new TradingModel(StockName.NAME_POP, TradingConstants.TYPE_COMMON, 8, 0.0, 100));
		stockList.put(StockName.NAME_ALE, new TradingModel(StockName.NAME_ALE, TradingConstants.TYPE_COMMON, 23, 0.0, 60));
		stockList.put(StockName.NAME_GIN, new TradingModel(StockName.NAME_GIN, TradingConstants.TYPE_PREFERRED, 8, 0.02, 100));
		stockList.put(StockName.NAME_JOE, new TradingModel(StockName.NAME_JOE, TradingConstants.TYPE_COMMON, 13, 0.0, 250));
	}
	
	public void populateTickerlist() {
		
		/** Ticker List Has 2 values old and new which are both par values initially.**/
		
		tickerPriceList.put(StockName.NAME_TEA, populateOldNewStockValue(StockName.NAME_TEA));
		tickerPriceList.put(StockName.NAME_POP, populateOldNewStockValue(StockName.NAME_POP));
		tickerPriceList.put(StockName.NAME_ALE, populateOldNewStockValue(StockName.NAME_ALE));
		tickerPriceList.put(StockName.NAME_GIN, populateOldNewStockValue(StockName.NAME_GIN));
		tickerPriceList.put(StockName.NAME_JOE, populateOldNewStockValue(StockName.NAME_JOE));
	}
	
	public ArrayList<Double> populateOldNewStockValue(String stockName)
	{
		ArrayList<Double> arr1 = new ArrayList<Double>();
		arr1.add(stockList.get(stockName).getParValue());
		arr1.add(stockList.get(stockName).getParValue());
		return arr1;
	}
	
	public void populateTrasactionlist() {
		try
		{
			tradeList.add(new TradingDAO(StockName.NAME_TEA,3,TradingConstants.BUY_IND,100,new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a").parse("Friday, Jul 23, 2015 07:10:56 PM")));
			tradeList.add(new TradingDAO(StockName.NAME_TEA,4,TradingConstants.BUY_IND,100,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_TEA,2,TradingConstants.SELL_IND,250,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_POP,4,TradingConstants.BUY_IND,80,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_POP,1,TradingConstants.SELL_IND,100,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_ALE,10,TradingConstants.SELL_IND,100,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_ALE,8,TradingConstants.BUY_IND,150,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_GIN,3,TradingConstants.BUY_IND,350,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_GIN,1,TradingConstants.BUY_IND,250,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_JOE,1,TradingConstants.BUY_IND,20,new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a").parse("Friday, Jul 23, 2015 07:10:56 PM")));
			tradeList.add(new TradingDAO(StockName.NAME_JOE,2,TradingConstants.SELL_IND,50,new Date()));
			tradeList.add(new TradingDAO(StockName.NAME_JOE,1,TradingConstants.SELL_IND,50,new Date()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/** Show predefined stock sample data to user to select for trading **/
	
	public void viewStockList() {
		int i = 0;
		/** Print in a tabular format for user to select the stock **/

		System.out.println("/******* Following is the stock data *******/");
		System.out.format("%n");
		System.out.format("%15s%15s%20s%20s%15s\n", "Stock Symbol", "Type",
				"Last Dividend", "Fixed Dividend", "Par Value");
		System.out.format("%n");
		Object[][] table = new Object[5][];

		for (TradingModel value : stockList.values()) {
			table[i] = new Object[] { value.getStockSymbol(), value.getType(),
					value.getLastDividend(), value.getFixedDividend(),
					value.getParValue() };
			i++;
		}

		for (final Object[] row : table) {
			System.out.format("%15s%15s%15d%20f%18f\n", row);
		}
		System.out.format("%n");

	}
	
	/**Get user's input***/
	public void selectStock()
	{
		String userStockOption="";
		boolean value=true;
		do{
			System.out.println("Please select a stock no from the list");
			System.out.println("A)TEA\nB)POP\nC)ALE\nD)GIN\nE)JOE\n");
			userStockOption = in.next();
			if("A".equalsIgnoreCase(userStockOption) || "B".equalsIgnoreCase(userStockOption) || "C".equalsIgnoreCase(userStockOption)
					|| "D".equalsIgnoreCase(userStockOption) || "E".equalsIgnoreCase(userStockOption))
			{
				selectedStock = userStockOption.toUpperCase();
				value=false;
			}
			else
			{
				System.out.println("Invalid number entered. Please select again");
				
			}
		}while(value==true);
	}
	
	/**Calculate Dividend yield, Calculate the P/E ratio, Record Trade, Calculate Stock Price **/
	public void tradeOrCalc() {
			boolean value=true;
			String stockName=getStockName(selectedStock);
			do
			{
				System.out.println("\n/******* Which operation do you want to perform for this stock*******/\n");
				System.out.println("A)Calculate Dividend yield\nB)Calculate the P/E ratio\nC)Record Trade\nD)Calculate Stock Price based on trades recorded in past 15 minutes\nE)Exit to main menu\n");
				String operation = in.next();
				if("A".equalsIgnoreCase(operation))
				{
					 double dividendYield=dividendYieldDecision(stockName);
					 System.out.println("current ticker price: "+getCurrentTickerPriceOfStock(stockName));
					 System.out.println("Dividend yield for common type= Last Dividend/ Ticker Price");
					 System.out.println("Dividend yield for Preferred type= Fixed Dividend * Par Value/ Ticker Price");
					 System.out.println("The Dividend yield for this stock is: "+Double.parseDouble(new DecimalFormat("##.##").format(dividendYield))+"%");
				}
				else if("B".equalsIgnoreCase(operation))
				{
					double dividendYield=dividendYieldDecision(stockName);
					System.out.println("current ticker price: "+getCurrentTickerPriceOfStock(stockName));
					System.out.println("The Dividend yield for this stock is: "+Double.parseDouble(new DecimalFormat("##.##").format(dividendYield))+"%");
					System.out.println("P/E ratio = Ticker Price/ Dividend");
					if(dividendYield!=0)
					{
						System.out.println("The P/E ratio for this stock is: "+Double.parseDouble(new DecimalFormat("##.##").format(calcImpl.CalcPERatio(getCurrentTickerPriceOfStock(stockName),dividendYield))));
					}
					else
					{
						System.out.println("Since dividend yield is 0, P/E ration cannot be calculated");
					}
				}
				else if("C".equalsIgnoreCase(operation))
				{
					recordTransaction(stockName);
				}
				else if("D".equalsIgnoreCase(operation))
				{
					tradeImpl.viewMyTrasactionsForLast15Min(stockName,tradeList);
					System.out.println("\nthe stock Price based on transactions in last 15 min is: "+Double.parseDouble(new DecimalFormat("##.##").format(getStockPriceOfStock(tradeImpl.getTransactionListForLast15Min(stockName,tradeList)))));
				}
				else if("E".equalsIgnoreCase(operation))
				{
					value=false;
				}
				else
				{
					System.out.println("Invalid number entered. Please select again");
				}
			}while(value==true);
	}
	
	/**dividend Yield Decision  based on stock name**/
	public double dividendYieldDecision(String stockName)
	{
		double dividendYeild=0.0;
		if(stockList.get(stockName).getType() == TradingConstants.TYPE_COMMON)
		{
			dividendYeild=calcImpl.CalcDividendYieldForCommon(stockList.get(stockName).getLastDividend(),getCurrentTickerPriceOfStock(stockName));
		}
		else if(stockList.get(stockName).getType() == TradingConstants.TYPE_PREFERRED)
		{
			dividendYeild=calcImpl.CalcDividendYieldForPreff(stockList.get(stockName).getFixedDividend(),getCurrentTickerPriceOfStock(stockName),stockList.get(stockName).getParValue());
		}
		return dividendYeild;
	}
	
	/** Buy and sell is not been considered as Bid and Ask, Quantity and price should be between 1 and 1000 **/
	public void recordTransaction(String stockName) {
		
		boolean value=true;
		tradeImpl.viewMyTrasactionsForStock(stockName,tradeList);
		do
		{
			boolean correctQuant = true;
			boolean correctPrice = true;
			System.out.format("%n");
			System.out.println("----- Buy or Sell?.-----\n");
			System.out.println("A)Buy\nB)Sell\nC)back to main operations\n");
			String operation = in.next();
			
			/** It is assumed that quantity cannot be 0 or less or more than 1000 and price should not be 0 or negative number**/
			
			if("A".equalsIgnoreCase(operation))
			{
				double price;
				double Qnt;
				do
				{
					System.out.println("How many stocks do you want to buy");
					Qnt = convertUserInput(in.next());
					if(Qnt<=0 || Qnt>1000)
					{
						System.out.println("Invalid number entered. Qnty should be more than 0 and less than 1000");
						correctQuant=true;
					}
					else
					{
						correctQuant=false;
					}
				}while(correctQuant == true);
				
				do
				{
					System.out.println("\nWhat is the trade price to buy");
					price = convertUserInput(in.next());
					if(price<=0 || price>1000)
					{
						System.out.println("Invalid number entered. Price should be more than 0 and less than 1000");
						correctPrice=true;
					}
					else
					{
						correctPrice=false;
					}
				}while(correctPrice == true);
				
				tradeImpl.addTrade(stockName, Qnt,1,price,tradeList);
				tradeImpl.viewMyTrasactionsForStock(stockName,tradeList);
				value=false;
			}
			else if("B".equalsIgnoreCase(operation))
			{
				double price;
				double Qnt;
				do
				{
					System.out.println("How many stocks do you want to sell");
					Qnt = convertUserInput(in.next());
					if(Qnt<=0 || Qnt>1000)
					{
						System.out.println("Invalid number entered. Qnty should be more than 0 and less than 1000");
						correctQuant=true;
					}
					else
					{
						correctQuant=false;
					}
					
				}while(correctQuant == true);
				
				do
				{
					System.out.println("\nWhat is the trade price to sell");
					price = convertUserInput(in.next());
					if(price<=0 || price>1000)
					{
						System.out.println("Invalid number entered. Price should be more than 0 and less than 1000");
						correctPrice=true;
					}
					else
					{
						correctPrice=false;
					}
					
				}while(correctPrice == true);
				tradeImpl.addTrade(stockName, Qnt,2,price,tradeList);
				tradeImpl.viewMyTrasactionsForStock(stockName,tradeList);
				value=false;
			}
			else if("C".equalsIgnoreCase(operation))
			{
				value=false;
			}
			else
			{
				System.out.println("Invalid number entered. Please select again.");
				
			}
		}while(value==true);
	}
	
	/** get the stock Name from the user's input i.e A, B, C, D, E **/
	
	public String getStockName(String stockCode)
	{
		String stockName = "";
		switch (stockCode) {
		case "A":
			stockName = StockName.NAME_TEA;
			break;
		case "B":
			stockName = StockName.NAME_POP;
			break;
		case "C":
			stockName = StockName.NAME_ALE;
			break;
		case "D":
			stockName = StockName.NAME_GIN;
			break;
		case "E":
			stockName = StockName.NAME_JOE;
			break;
		}
		
		return stockName;
	}
	
	/** get the stock price **/
	public double getStockPriceOfStock(ArrayList<TradingDAO> tradeListOfSpecificStock)
	{
		double totalTrade=0.0;
		double totalQnt=0.0;
		for(TradingDAO tr: tradeListOfSpecificStock)
		{
			totalTrade=totalTrade+ (tr.getPrice() * tr.getQuantityOfShares());
			totalQnt= totalQnt + tr.getQuantityOfShares();
		}
		if(totalQnt!=0.0)
		{
			return calcImpl.calculateStockPrice(totalTrade, totalQnt);
		}
		else
		{
			return calcImpl.calculateStockPrice(totalTrade, 1);	
		}
		
	}
	
	/** get the current ticker price for a given stock **/
	public double getCurrentTickerPriceOfStock(String stockName)
	{
		ArrayList<Double> arr1= new ArrayList<Double>();
		arr1=tickerPriceList.get(stockName);
		if(!arr1.isEmpty())
		{
				return arr1.get(arr1.size()-1);
		}
		else
			return 1;
	}
	
	/** It is assumed that the prices considered for geometric mean are the stock prices of the stocks. **/
	public double getGeometricMeanCal()
	{
		double pricesOfAllStock=1;
		int totalNumber=0;
		double numerator=0;
		for(String str: stockList.keySet())
		{
			ArrayList<TradingDAO> arr= new ArrayList<TradingDAO>();
			double price=1;
			for (TradingDAO s : tradeList) 
			{ 
				if(str.equalsIgnoreCase(s.getStockSymbol()))
				{
					arr.add(s);
					
				}
			}
			price= getStockPriceOfStock(arr);	
			if(price!=0)
			{
			pricesOfAllStock= pricesOfAllStock * price;
			}
			totalNumber++;
		}
		System.out.println("\nMultiplication of Prices of all stock is :"+new BigDecimal(pricesOfAllStock).longValue());
		System.out.println("total number of stocks traded :"+totalNumber);
		if(totalNumber>=0)
		{
			numerator=1/(double)(totalNumber);
			return calcImpl.CalGeometricMean(pricesOfAllStock, numerator);
		}
		else
		{
			return 0.0;
		}
	}
	
	/** convert user's input to a number, it will be used to get stock quantities and price for buy or sell. If user's input is invalid this method
	 * will return 5000 and system will ask reenter input **/
	private Double convertUserInput(String userInput)
	{
		double userInputDouble =5000;
		try{
			userInputDouble = Double.parseDouble(userInput);
		}catch(NumberFormatException nfe){}
		return userInputDouble;
	}
}
