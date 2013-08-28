package ace.tf;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import java.util.Comparator;
import ace.util.Token;


/**
 * 
 * @author mattmann
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class TFGenerator{
	
	protected StopWordSet STOP_WORDS=null;
	private List Document=null;  //List of java.util.String tokens
	
	public TFGenerator(List d){
		Document = d;
	}
	
	public TFGenerator(List d,StopWordSet sw){
		Document = d;
		STOP_WORDS = sw;
	}
	
    public Map getTF(){
    	//step 1, count all the occurences of each term in the list
        Map theOccurences = getTermOccurences();
        int md = getMaxFreq(theOccurences);
        
    	Map theTermFreqs = new HashMap();
    	
    	for(Iterator i = theOccurences.keySet().iterator(); i.hasNext(); ){
    		String theTerm = (String)i.next();
    		Integer theTermOccurences = (Integer)theOccurences.get(theTerm);
    		
    		double TF = (double)((double)theTermOccurences.intValue())/md;
    		theTermFreqs.put(theTerm,new Double(TF));
    	}

    	return theTermFreqs;
    }

    public double getTermFrequency(String term){
    	Map tfs = getTF();
        Double theFreq = (Double)tfs.get(term);
        
        if(theFreq != null){
        	return theFreq.doubleValue();
        }else return -1.0;
    }
    
    private int getMaxFreq(Map theTFs){
         int highValue = 0;
         
         for(Iterator i = theTFs.keySet().iterator(); i.hasNext();){
         	String theTerm = (String)i.next();
         	Integer theTermVal = (Integer)theTFs.get(theTerm);
         	int theVal = theTermVal.intValue();
         	
         	if(theVal > highValue){
         		highValue = theVal;
         	}
         }
         
         return highValue;
    }
    
    
    private Map getTermOccurences(){
    	Map occurences = new HashMap();
    	
    	for(Iterator i = Document.iterator(); i.hasNext(); ){
    		String theTerm = i.next().toString();
    		//first check if we have any STOP_WORDS
    		if(STOP_WORDS != null && STOP_WORDS.contains(theTerm.toUpperCase())){
    			//ignore this term
    		}
    		else{
    			//if we have counted this already, just increment the count
    			Integer theCount = (Integer)occurences.get(theTerm);
    			if(theCount == null){
    				theCount = new Integer(1);
    				occurences.put(theTerm,theCount);
    			}
    			else{
    				theCount = new Integer(theCount.intValue()+1);
    				occurences.put(theTerm,theCount);
    			}
    			
    		}
    	}
    	
    	return occurences;
    }
	
}