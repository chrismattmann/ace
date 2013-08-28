/*
 * TempToken.java
 *
 * Created on April 17, 2004, 9:09 PM
 */

package ace.util;


/**
 * Represents a parsed token
 *
 * @author  pramirez
 */
public class TempToken {
   private String tempTerm = null;
   private String term = null;
   private int size;
   
   public TempToken(String tempTerm, String term, int size) {
      this.term = term;
      this.tempTerm = tempTerm;
      this.size = size;
   }
   
   public String getTerm() {return term;}
   public String getTempTerm() {return tempTerm;}
   public String toString() {return tempTerm;}
   public int getSize() {return size;}
   
   public void setTerm(String term) {this.term = term;}
   public void setTempTerm(String tempTerm) {this.tempTerm = tempTerm;}
   public void setSize(int size) {this.size = size;}
}
