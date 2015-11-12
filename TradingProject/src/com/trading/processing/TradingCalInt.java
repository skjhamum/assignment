package com.trading.processing;

/**
 *This is an interface to calculate stock price PE ratio, dividend and Geometric mean. 
 * @author SKJHA
 * @version 1.0
 */
public interface TradingCalInt {

	public double calculateStockPrice(double totalTrade,double totalQnt);
	public double CalcPERatio(double currentPrice, double dividendYield);
	public double CalcDividendYieldForCommon(double dividend,double currentTickPrice);
	public double CalcDividendYieldForPreff(double dividend,double currentTickPrice,double parvalue);
	public double CalGeometricMean(double pricesOfAllStock,double totalNumber);
}
