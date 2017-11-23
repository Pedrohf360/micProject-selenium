package web;
import java.net.*;
	import java.io.*;


public class UrlTeste {

		//static String url = "http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml";
		static String url = "http://iptuonline.siatu.pbh.gov.br/IptuOnline/index.xhtml;jsessionid=BE176BFAE082C4CC4FE7B10F6D821FC9.iptuonline1";
		static String indiceCadastral = "";
	//	static String 
	    
		public static void main(String[] args) throws Exception {
	        URL oracle = new URL(url);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        
	        System.out.println("Header:"+yc.getHeaderField("Set-Cookie"));
	      //System.out.println("Header:"+yc.getHeaderFields());
	        String inputLine;
	        FileWriter t = new FileWriter("arquivo.txt");
	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
//				t.write(inputLine);
	        in.close();
	        t.close();
	    }
	}

