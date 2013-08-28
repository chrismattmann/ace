/*
 * SearchEngine.java
 *
 * Created on April 18, 2004, 2:50 PM
 */

package ace.util;

import ace.gui.ResultTable;
import ace.agents.SearchEngine;
import ace.agents.GoogleSearchEngine;
import java.util.*;

/**
 *
 * @author  pramirez
 */
public class Search {
   private ResultTable resultTable;
   
   /** Creates a new instance of SearchEngine */
   public Search(ResultTable resultTable) {
      this.resultTable = resultTable;
   }
   
   public void query(String query, String site) {
      SearchEngine se = new GoogleSearchEngine();
      List results = se.query(query, site);
      resultTable.putResults(results);
   }
}
