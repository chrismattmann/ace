/*
 * SiteRanker.java
 *
 * Created on April 17, 2004, 6:24 PM
 */

package ace.util;

import java.util.*;
import ace.agents.data.ResultTuple;
import javax.swing.JTable;
import java.net.URL;
import ace.gui.ResultTable;
import ace.util.Site;

/**
 *
 * @author  pramirez
 */
public class SiteRanker {
   private Map sites = null;
   private List results = null;
   private JTable siteTable = null;
   private ResultTable resultTable = null;
   private int numSites;
   private int numResults;
   
   /** Creates a new instance of SiteRanker */
   public SiteRanker(JTable siteTable, ResultTable resultTable) {
      sites = new HashMap();
      results = new ArrayList();
      this.resultTable = resultTable;
      this.siteTable = siteTable;
      numResults = 0;
      numSites = 0;
   }
   
   public void showResultsFor(String site) {
      int count = 0;
      List rl = new ArrayList();
      for (Iterator i = results.iterator(); i.hasNext();) {
         ResultTuple rt = (ResultTuple) i.next();
         if (rt.getLink().indexOf(site) != -1) {
            rl.add(rt);
         }
      }
      
      resultTable.putResults(rl);
   }
   
   public void showResults() {
      resultTable.putResults(results);
   }
   
   public synchronized void addResults(List results) {
      this.results.addAll(results);
      showResults();
     
      List sites = new ArrayList();
      for (Iterator i = results.iterator(); i.hasNext();) {
         ResultTuple rt = (ResultTuple) i.next();
         try {
            URL url = new URL(rt.getLink());
            Site site = new Site(url.getHost());
            if (sites.contains(site)) {
               Site s = (Site) sites.get(sites.indexOf(site));
               s.hit();
            } else {
               sites.add(site);
               site.hit();
            }
         } catch(Exception ex) {System.out.println(ex.getMessage());}
      }
      
      Collections.sort(sites, new Comparator() { 
                                      public int compare(Object o1, Object o2) {
                                         Site s1 = (Site) o1;
                                         Site s2 = (Site) o2;
                                         if (s1.getHits() == s2.getHits())
                                            return 0;
                                         else if (s1.getHits() > s2.getHits())
                                            return -1;
                                         else
                                            return 1;
                                         }});
      
      for (int i=0; i<sites.size(); i++) {
         Site s = (Site) sites.get(i);          
         siteTable.setValueAt(s.getSiteName(), i, 0);
         siteTable.setValueAt("" + s.getHits(), i, 1);
      }
      /*
      for (Iterator i = results.iterator(); i.hasNext();) {
         ResultTuple rt = (ResultTuple) i.next();
         try {
            URL url = new URL(rt.getLink());
            String site = url.getHost();
            Integer count = (Integer) sites.get(site);
            if (count == null)
               count = new Integer(1);
            else
               count = new Integer(count.intValue()+1);
            sites.put(site, count);
         } catch(Exception ex) {System.out.println(ex.getMessage());}
      }
      
      for (Iterator i = sites.keySet().iterator(); i.hasNext() && numSites<50;) {
         String site = (String) i.next();
         Integer count = (Integer) sites.get(site);
         siteTable.setValueAt(site, numSites, 0);
         siteTable.setValueAt(count, numSites, 1);
         numSites++;
      }*/
   }
   
   public void reset() {
      clearTables();
      numResults = 0;
      numSites = 0;
      sites = new HashMap();
      results = new ArrayList();
   }
   
   private void clearTables() {
      resultTable.reset();
      for (int i=0; i<numSites; i++) {
         siteTable.setValueAt(null, i, 0);
         siteTable.setValueAt(null, i, 1);
      }
   }
}
