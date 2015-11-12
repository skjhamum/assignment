package com.trading.model;

/**
 * This is a model class for stocks.
 * @author SKJHA
 * @version 1.0
 */

public class TradingModel {
	private String stockSymbol;
	private String type;
	private int lastDividend;
	private double fixedDividend;
	private double parValue;
	
	/** constructor **/
	public TradingModel(String stockName, String stockType,int dividend, double fDiv,double value)
	{
		this.stockSymbol=stockName;
		this.type=stockType;
		this.lastDividend= dividend;
		this.fixedDividend= fDiv;
		this.parValue= value;
	}
	
	/** getter Setters method **/
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}
	public double getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public double getParValue() {
		return parValue;
	}
	public void setParValue(int parvAlue) {
		this.parValue = parvAlue;
	}
	
	/** eaquals method **/
	public boolean equals(Object obj) {
        if ((obj instanceof TradingModel) && (((TradingModel)obj).getStockSymbol()== this.stockSymbol))
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
	
	/** Hash code method **/
	public int hashCode(){
	    return (int) stockSymbol.hashCode();
	  }
	

}
