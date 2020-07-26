package com.shop.cart.task;

import java.io.Serializable;

/*
 * "rates": { "GBP": 0.90985 }, "base": "EUR", "date": "2020-07-24"
 */
/**
 * 
 * @author shaival
 *
 */
public class Exchange implements Serializable
{
   // private Rates rates;
	
	private Object rates;

    private String base;

    private String date;

    public void setRates(Object rates){
        this.rates = rates;
    }
    public Object getRates(){
        return this.rates;
    }
    public void setBase(String base){
        this.base = base;
    }
    public String getBase(){
        return this.base;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
	@Override
	public String toString() {
		return "Exchange [rates=" + rates + ", base=" + base + ", date=" + date + "]";
	}
    
    
}