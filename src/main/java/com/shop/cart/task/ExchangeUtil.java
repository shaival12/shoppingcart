package com.shop.cart.task;

import org.springframework.web.client.RestTemplate;


public class ExchangeUtil {

	public static void main(String[] args) {
		getCurrentRate();
	}

	/**
	 * Call exchange url and get GBP latest rate
	 */
	public static void getCurrentRate() {
		String exchangeUrl = "https://api.exchangeratesapi.io/latest?symbols=GBP"; 
        RestTemplate restTemplate = new RestTemplate(); 	
        Exchange exchange = restTemplate
        	  .getForObject(exchangeUrl, Exchange.class);
        System.out.println(exchange.toString());
        System.out.println(exchange.getRates());
        
	}
    
    
	
}
