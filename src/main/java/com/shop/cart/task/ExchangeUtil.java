package com.shop.cart.task;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.web.client.RestTemplate;


public class ExchangeUtil {

	private static final String GBP_CURRENCY = "GBP";
	private static final String EXCHANGE_GET_URL = "https://api.exchangeratesapi.io/latest?symbols=";

	public static void main(String[] args) {
		String gbpRate = getCurrentRate(GBP_CURRENCY);
		
		System.out.println(gbpRate);
		
		BigDecimal GBPRate = new BigDecimal(gbpRate);
		BigDecimal gbp = new BigDecimal(1000.00);
		BigDecimal eurototal = convertGBPtoEuro(GBPRate, gbp);
		
		System.out.println(eurototal.toString());
	}

	/**
	 *  convert GBP to EURO
	 * @param GBPRate
	 * @param gbp
	 * @return
	 */
	public static BigDecimal convertGBPtoEuro(BigDecimal GBPRate, BigDecimal gbp) {
		MathContext mc = new MathContext(5);
		BigDecimal eurototal = gbp.divide(GBPRate,mc);
		return eurototal;
	}
	
	

	/**
	 * Call exchange url and get GBP latest rate
	 */
	public static String getCurrentRate(String currency) {
		
		try {
			String exchangeUrl = EXCHANGE_GET_URL+currency; 
	        RestTemplate restTemplate = new RestTemplate(); 	
	        Exchange exchange = restTemplate
	        	  .getForObject(exchangeUrl, Exchange.class);
	        
	        String  a = exchange.getRates().toString();
			String[] arr = a.replace("{", "").replace("}", "").split("=");
			return arr[1].trim();
		
		}catch(Exception e) { // Error while getting latest price
			e.printStackTrace();
		}
        
		return null;
	}
    
    
	
}
