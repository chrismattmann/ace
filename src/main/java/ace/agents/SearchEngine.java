package ace.agents;

import java.util.List;
import java.util.Vector;
import java.io.StringReader;

/**
 *
 * @author mattmann
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


public abstract class SearchEngine implements ISearchEngine{
   
   protected String sourceName=null;

   public SearchEngine(String s){
      sourceName = s;
   }

     
   public List query(String queryStr){
      return query(queryStr,null);
   }
   
   public abstract List query(String queryStr,String site);
   
   
   
}