package fsCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//Followed the documentation on https://developers.veliainn.com/ and modified it for Java

public class ApiCall {
	public static String ApiRequest(int item) throws IOException {
	    final String POST_PARAMS = "{\"keyType\": 0, \"mainKey\": "+item+"}";	    
	    
	    //This API specifically just work with POSTs instead of GETs
	    //Established the header for the connection
	    System.out.println(POST_PARAMS);
	    URL obj = new URL("https://eu-trade.naeu.playblackdesert.com/Trademarket/GetWorldMarketSubList");
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type", "application/json");
	    postConnection.setRequestProperty("User-Agent", "BlackDesert");

	    //Did the POST and got the JSON
	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader((postConnection.getInputStream())));
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = br.readLine()) != null) {
	        sb.append(line+"\n");
	    }
	    br.close();
	    return sb.toString();
	}
	
	//This method extracts the item price out of the JSON, it could probably be done in a more optimal way
	//But couldn't think of any at short term
	public static int itemPrice(int item) throws IOException{
		String itemJson = ApiRequest(item);
		for(int i = 0; i < 8; i++) {
			itemJson = itemJson.substring(itemJson.indexOf("-")+1);
		}
		return Integer.valueOf(itemJson.substring(0, itemJson.indexOf("-")));
	}
}