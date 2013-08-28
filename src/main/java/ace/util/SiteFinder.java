/*
 * SiteFinder.java
 *
 * Created on April 17, 2004, 5:01 PM
 */

package ace.util;


import ace.agents.*;
import ace.agents.data.*;
import java.util.*;

/**
 *
 * @author  pramirez
 */
public class SiteFinder {
   private SiteRanker ranker;
   private String query;
   
   /** Creates a new instance of SiteFinder */
   public SiteFinder(SiteRanker ranker, String query) {
      this.query = query;
      this.ranker = ranker;
   }
   
   public void run() {
      
   	  String wrapperBaseURL = System.getProperty("wrapper.baseURL");
   	  
   	  if(wrapperBaseURL == null){
   	  	wrapperBaseURL = "http://localhost:8080";
   	  }
   	  
      SearchEngine se = new GoogleSearchEngine();
      List results = se.query(query);
      System.out.println("Num results: " + results.size());
      /*
      List results = new ArrayList();
      results.add(new ResultTuple(null, "http://sweet.jpl.nasa.gov", null, "msn"));
      results.add(new ResultTuple(null, "http://www.warped.nu", null, "msn"));
      results.add(new ResultTuple(null, "http://sunset.usc.edu", null, "msn"));
      results.add(new ResultTuple(null, "http://baron.pagemewhen.com", null, "msn"));
      results.add(new ResultTuple(null, "http://sunset.usc.edu", null, "msn"));
      results.add(new ResultTuple(null, "http://www.pagemewhen.com", null, "msn"));
      results.add(new ResultTuple(null, "http://sourceforge.net", null, "msn"));
      results.add(new ResultTuple(null, "http://www.intrafoundation.com", null, "msn"));
      results.add(new ResultTuple(null, "http://sweet.jpl.nasa.gov", null, "msn"));
      results.add(new ResultTuple(null, "http://dmoz.org", null, "msn"));
      results.add(new ResultTuple(null, "http://csweb1.usc.edu", null, "msn"));
      results.add(new ResultTuple(null, "http://www.caere.com", null, "msn"));
      results.add(new ResultTuple(null, "http://baron.pagemewhen.com", null, "msn"));
      results.add(new ResultTuple(null, "http://www-scf.usc.edu", null, "msn"));
      results.add(new ResultTuple(null, "http://www-scf.usc.edu", null, "msn"));
       */
      ranker.addResults(results);
   }
   
}
