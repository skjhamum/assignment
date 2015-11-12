package com.trading.test;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Timer;

import com.trading.processing.TradingExchange;
import com.trading.processing.TradingUpdate;

/**
 * This is test class to verify implementation. It will ask user to chose options.
 * @author SKJHA
 * @version 1.0
 */

public class ApplicationTest {

	public static void main(String[] args) {
		
		TradingExchange stck= new TradingExchange();
		Scanner in = new Scanner(System.in);
		boolean value=true;
		
		/** Initialize all the predefined values **/
		stck.initializeStockExchange();
		
		/** It Is assumed that ticker price for every stock has 2 values old and current. Initially both the values are Par Values **/
		/**After every 1 minutes, the current ticker price is updated with new random price and old price is replaced with current price**/
		try{
			Timer timer = new Timer();
			timer.schedule(new TradingUpdate(stck.getTickerPriceList()), 60000, 60000);
			
		}
		catch(IllegalArgumentException iae)
		{
			iae.printStackTrace();
		}
		catch(IllegalStateException ise)
		{
			ise.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		/** Operations **/
		do
		{
			System.out.println("\n/******* Which operation do you want to perform *******/");
			System.out.format("%n");
			System.out.println("A)Perform trading or calculation for a particular stock\nB)Calculate the GBCE All Share Index using the geometric mean of prices for all stocks\nC)Exit\n");
			String operation = in.next();
			
			if("A".equalsIgnoreCase(operation)) 
			{
				stck.selectStock();
				stck.tradeOrCalc();
			}
			else if("B".equalsIgnoreCase(operation))
			{
				System.out.println("The geometric mean of prices for all stock is :"+Double.parseDouble(new DecimalFormat("##.##").format(stck.getGeometricMeanCal())));
			}
			else if("C".equalsIgnoreCase(operation))
			{
				in.close();
				System.exit(1);
			}
			
		}while(value==true);
		
	}
}
