package com.trading.processing;
import java.util.ArrayList;
import com.trading.model.TradingDAO;

/**
 *This is an interface to add trade, print and view transaction for a given stock.
 * @author SKJHA
 * @version 1.0
 */
public interface TradingInt {

	public void addTrade(String stockName, double qnty, int ind,double tradePrice,ArrayList<TradingDAO> tradeList);
	public void printTradeList(ArrayList<TradingDAO> tradeList);
	public void viewMyTrasactionsForStock(String stockName,ArrayList<TradingDAO> tradeList);
}
