package com.shop.cart.task;

public class Rates
{
    private Object GBP;

    public void setGBP(Object GBP){
        this.GBP = GBP;
    }
    public Object getGBP(){
        return this.GBP;
    }
	@Override
	public String toString() {
		return "Rates [GBP=" + GBP + "]";
	}
    
    
}
