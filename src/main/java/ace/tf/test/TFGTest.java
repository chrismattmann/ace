package ace.tf.test;

import java.util.List;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import ace.tf.TFGenerator;
import ace.tf.StopWordSet;


public class TFGTest{
	
	public static void main(String [] args){
		List theDocument = new Vector();
		theDocument.add("Paul");
		theDocument.add("Paul");
		theDocument.add("Paul");
		theDocument.add("is");
		theDocument.add("a");
		theDocument.add("raging");
		theDocument.add("whore");
		
		StopWordSet theStopWords = new StopWordSet();
		theStopWords.add("a".toUpperCase());
		theStopWords.add("the".toUpperCase());
		theStopWords.add("is".toUpperCase());
		
		TFGenerator tfg = new TFGenerator(theDocument,theStopWords);
		
		Map termFreqs = tfg.getTF();
		
		for(Iterator i = termFreqs.keySet().iterator(); i.hasNext(); ){
			String theTerm = (String)i.next();
			Double theTF = (Double)termFreqs.get(theTerm);
			
			System.err.println("Term: "+theTerm+" | Frequency: "+theTF.doubleValue());
		}
		
		
		
	}
	
	
	
}