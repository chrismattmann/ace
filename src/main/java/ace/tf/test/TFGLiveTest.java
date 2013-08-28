package ace.tf.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

import ace.util.HTMLParser;
import ace.tf.TFGenerator;
import ace.tf.StopWordSet;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
      
public class TFGLiveTest{
	public final static String usage = "java ace.tf.test.TFGLiveTest --url [url to parse] [--swf Stop Word File Loc]\n";
	
	public static void main(String [] args){
		String theURL = null;
		String stopWordFileLoc = null;
		
		for(int i=0; i < args.length; i++){
			if(args[i].equals("--url")){
				theURL = args[++i];
			}
			else if(args[i].equals("--swf")){
				stopWordFileLoc=args[++i];
			}
		}
		
		if(theURL == null){
			System.err.println(usage);
			System.exit(1);
		}
		
		StopWordSet theStopWords = new StopWordSet();
		
		if(stopWordFileLoc == null){
			theStopWords.add("a".toUpperCase());
			theStopWords.add("the".toUpperCase());
			theStopWords.add("is".toUpperCase());
		}
		else{
			try{
				theStopWords.loadStopWords(stopWordFileLoc);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}
		
		HTMLParser htmlParse = new HTMLParser(theURL, theStopWords);
		
		try{
			htmlParse.doParse();
		}
		catch(Exception theException){
			System.err.println("Exception parsing HTML!! ");
			System.err.println(theException.getMessage());
			theException.printStackTrace();
		}
		String retText = htmlParse.getText().toString();
		
		StringTokenizer st = new StringTokenizer(retText," \r\n\t\f|=.1234567890$,\\?/-:;{}()\"+%#@^&*~`[]");
		
		List theDocument = new Vector();

		 System.out.println("Performing TFG test with stop words..");
		 System.out.println(theStopWords.toString());

		
		
		
		while(st.hasMoreTokens()){
			String theTok = (String)st.nextToken();
			System.err.println("Got Token "+theTok);
			theDocument.add(theTok);
		}

		TFGenerator tfg = new TFGenerator(theDocument,theStopWords);
		
		Map termFreqs = tfg.getTF();
		
		for(Iterator i = termFreqs.keySet().iterator(); i.hasNext(); ){
			String theTerm = (String)i.next();
			Double theTF = (Double)termFreqs.get(theTerm);
			
			System.out.println("Term: "+theTerm+" | Frequency: "+theTF.doubleValue());
		}
		
		
		
		
	}
	
	
	
}