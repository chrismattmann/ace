/*
 * Token.java
 *
 * Created on April 17, 2004, 9:09 PM
 */

package ace.util;

import javax.swing.text.html.*;

/**
 * Represents a parsed token
 *
 * @author  pramirez
 */
public class Token {
   private String term = null;
   private int position;
   private HTML.Tag tag;
   
   public Token(String term, int position, HTML.Tag tag) {
      this.term = term;
      this.position = position;
      this.tag = tag;
   }
   
   public Token(String term, int position) {
      this(term, position, null);
   }
   
   public String getTerm() {return term;}
   public int getPosition() {return position;}
   public HTML.Tag getTag() {return tag;}
   public String toString() {return term;}
   
   public void setTerm(String term) {this.term = term;}
   public void setPosition(int position) {this.position = position;}
   public void setTag(HTML.Tag tag) {this.tag = tag;}
}
