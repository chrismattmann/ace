/*
 * Site.java
 *
 * Created on April 17, 2004, 6:24 PM
 */

package ace.util;

/**
 *
 * @author  pramirez
 */
public class Site {
   private String siteName = null;
   private int hits = 0;
   
   /** Creates a new instance of SiteRanker */
   public Site(String siteName) {
      this.siteName = siteName;
   }
   
   public String getSiteName() {return siteName;}
   public int getHits() {return hits;}
   public boolean equals(Object o) {
      if (siteName.equalsIgnoreCase(o.toString()))
         return true;
      else
         return false;
   }
   public String toString() {return siteName;}
   public void hit() {hits++;}
}
