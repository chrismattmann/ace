package ace.util.test;


import ace.util.HTMLParser;
import java.util.List;



public class HTMLStripperTest{
	
	public static final String usage="java ace.util.test.HTMLStripperTest --url [url to parse]\n";
	
	public static void main(String [] args){
		
		String theURL = null;
		
		for(int i=0; i < args.length; i++){
			if(args[i].equals("--url")){
				theURL = args[++i];
			}
		}
		
		if(theURL==null){
			System.err.println(usage);
			System.exit(1);
		}
		
		HTMLParser htmlParse = new HTMLParser(theURL);
		try{
			htmlParse.doParse();			
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		List theTokens = htmlParse.getTokens();
		System.out.println(theTokens);
		
	}
	
	
}