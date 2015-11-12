package com.trading.processing;

import java.math.BigDecimal;

/**
 *This is an implementation class for TradingCalInt which will be used to calculate stock price PE ratio, dividend and Geometric mean.
 * @author SKJHA
 * @version 1.0
 */

public class TradingCalImpl implements TradingCalInt{
	

	@Override
	public double CalcPERatio(double currentPrice, double dividendYield) {
		return currentPrice/dividendYield;
	}

	@Override
	public double CalGeometricMean(double pricesOfAllStock,double totalNumber) {
		return Math.pow(new BigDecimal(pricesOfAllStock).doubleValue(), totalNumber);
	}

	@Override
	public double CalcDividendYieldForCommon(double dividend,
			double currentTickPrice) {
		return dividend/currentTickPrice;
	}

	@Override
	public double CalcDividendYieldForPreff(double dividend,
			double currentTickPrice, double parvalue) {
		return (dividend*parvalue)/currentTickPrice;
		
	}

	@Override
	public double calculateStockPrice(double totalTrade, double totalQnt) {
		return totalTrade/totalQnt;
	}
}
