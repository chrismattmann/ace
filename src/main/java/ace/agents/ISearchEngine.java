package ace.agents;

import java.util.List;
/*
 * 
 *
 * @author mattmann
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


public interface ISearchEngine{
	
  public List query(String queryStr);
  public List query(String queryStr,String site);
	
}