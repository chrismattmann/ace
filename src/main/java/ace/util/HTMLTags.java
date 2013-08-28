/*
 * HTMLTags.java
 *
 * Created on April 17, 2004, 9:09 PM
 */

package ace.util;

import javax.swing.text.html.*;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents the tags that will be processed and their relevance weights.
 *
 * @author  pramirez
 */
public class HTMLTags {
   private Map acceptableTags = new HashMap();
   
   public HTMLTags() {
      acceptableTags.put(HTML.Tag.A, new Double(0.5));
      acceptableTags.put(HTML.Tag.ADDRESS, new Double(0.0));
      acceptableTags.put(HTML.Tag.B, new Double(0.8));
      acceptableTags.put(HTML.Tag.BIG, new Double(0.8));
      acceptableTags.put(HTML.Tag.BODY, new Double(0.0));
      acceptableTags.put(HTML.Tag.BR, new Double(0.0));
      acceptableTags.put(HTML.Tag.CAPTION, new Double(0.0));
      acceptableTags.put(HTML.Tag.CENTER, new Double(0.0));
      acceptableTags.put(HTML.Tag.CITE, new Double(0.0));
      acceptableTags.put(HTML.Tag.CODE, new Double(0.0));
      acceptableTags.put(HTML.Tag.DD, new Double(0.0));
      acceptableTags.put(HTML.Tag.DFN, new Double(1.0));
      acceptableTags.put(HTML.Tag.DIR, new Double(0.0));
      acceptableTags.put(HTML.Tag.DIV, new Double(0.0));
      acceptableTags.put(HTML.Tag.DL, new Double(0.0));
      acceptableTags.put(HTML.Tag.DT, new Double(1.0));
      acceptableTags.put(HTML.Tag.EM, new Double(0.0));
      acceptableTags.put(HTML.Tag.FONT, new Double(0.0));
      acceptableTags.put(HTML.Tag.FORM, new Double(0.0));
      acceptableTags.put(HTML.Tag.FRAME, new Double(0.0));
      acceptableTags.put(HTML.Tag.FRAMESET, new Double(0.0));
      acceptableTags.put(HTML.Tag.H1, new Double(1.0));
      acceptableTags.put(HTML.Tag.H2, new Double(0.85));
      acceptableTags.put(HTML.Tag.H3, new Double(0.7));  
      acceptableTags.put(HTML.Tag.H4, new Double(0.5));  
      acceptableTags.put(HTML.Tag.H5, new Double(0.3));  
      acceptableTags.put(HTML.Tag.H6, new Double(0.1));  
      acceptableTags.put(HTML.Tag.HR, new Double(0.0));  
      acceptableTags.put(HTML.Tag.HTML, new Double(0.0));  
      acceptableTags.put(HTML.Tag.I, new Double(0.3));  
      acceptableTags.put(HTML.Tag.KBD, new Double(0.0));  
      acceptableTags.put(HTML.Tag.LI, new Double(0.7));  
      acceptableTags.put(HTML.Tag.MENU, new Double(0.0));  
      acceptableTags.put(HTML.Tag.OL, new Double(0.0));  
      acceptableTags.put(HTML.Tag.P, new Double(0.0));  
      acceptableTags.put(HTML.Tag.PRE, new Double(0.0));  
      acceptableTags.put(HTML.Tag.SAMP, new Double(0.0));  
      acceptableTags.put(HTML.Tag.SMALL, new Double(0.0));  
      acceptableTags.put(HTML.Tag.SPAN, new Double(0.0));  
      acceptableTags.put(HTML.Tag.STRONG, new Double(0.0));  
      acceptableTags.put(HTML.Tag.TABLE, new Double(0.0));  
      acceptableTags.put(HTML.Tag.TD, new Double(0.0));  
      acceptableTags.put(HTML.Tag.TEXTAREA, new Double(0.0));  
      acceptableTags.put(HTML.Tag.TH, new Double(0.5));  
      acceptableTags.put(HTML.Tag.TITLE, new Double(1.0));  
      acceptableTags.put(HTML.Tag.TR, new Double(0.0));      
      acceptableTags.put(HTML.Tag.TT, new Double(0.0));  
      acceptableTags.put(HTML.Tag.U, new Double(0.7));  
      acceptableTags.put(HTML.Tag.UL, new Double(0.0));  
   }
   
   public boolean isAcceptable(HTML.Tag tag) {
      if (acceptableTags.get(tag) != null)
         return true;
      return false;
   }
   
   public double getWeight(HTML.Tag tag) {
      if (acceptableTags.get(tag) != null)
         return ((Double) acceptableTags.get(tag)).doubleValue();
      return 0;
   }
}
