package ace.tf;

import java.util.HashSet;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
 * 
 * @author mattmann
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class StopWordSet{
	
	private HashSet swSet=null;
	
	
	public StopWordSet(){
	  swSet = new HashSet();	
	}
	
	public void add(String sw){
		swSet.add(sw);
	}
	
	public boolean contains(String sw){
		return swSet.contains(sw);
	}
	
	public Iterator iterator(){return swSet.iterator();}
	
	public boolean remove(String sw){
		return swSet.remove(sw);
	}
	
	public int size(){return swSet.size();}
	
	public String toString(){
		String rStr="";
		
		
		for(Iterator i = swSet.iterator(); i.hasNext();){
			String sw = (String)i.next();
			rStr+=sw+"\n";
		}
		
		return rStr;
	}
	public void loadStopWords(String fileLoc) throws IOException,FileNotFoundException,Exception{
		try {
		   File f = new File(fileLoc);
		   BufferedReader in
		      = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		
		   //each line is a stopword
		   String theWord = null;
		
		   //clear out existing stop word list
		   swSet.clear();
		
		   while((theWord = in.readLine()) != null){
			   swSet.add(theWord.toUpperCase());
			   //System.out.println("Adding "+theWord.toUpperCase());
		   }
		
		   in.close();
		} catch(FileNotFoundException fnfe) {
		   System.out.println(fnfe.getMessage());
		   throw fnfe;
		} catch(IOException ioe) {
		   System.out.println(ioe.getMessage());
		   throw ioe;
		} catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
	
	
	
	
}