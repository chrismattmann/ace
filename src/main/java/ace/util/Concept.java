/*
 * Concept.java
 *
 * Created on April 17, 2004, 2:14 PM
 */

package ace.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author  pramirez
 */
public class Concept {
   private String concept;
   private String url;
   private double ranking;
   private List tokens;
   private List derived;
   private String tempTerm;
   private int size = 1;
   
   public Concept(String concept, String url, double ranking, List tokens) {
      this.concept = concept;
      this.url = url;
      this.ranking = ranking;
      this.tokens = tokens;
      this.derived = new ArrayList();
      this.tempTerm = "";
   }
   
   public Concept(String concept, double ranking, List tokens) {
      this(concept, null, ranking, tokens);
   }
   
   public Concept(String concept, Double ranking, List tokens) {
      this(concept, null, ranking.doubleValue(), tokens);
   }
   
   public Concept(String concept, String url, Double ranking, List tokens) {
      this(concept, url, ranking.doubleValue(), tokens);
   }
   
   public Concept(String concept, Double ranking) {
      this(concept, null, ranking.doubleValue(), new ArrayList());
   }
   
   public Concept(String concept, double ranking) {
      this(concept, null, ranking, new ArrayList());
   }
   
   public Concept(String concept, String url) {
      this(concept, url, 0, new ArrayList());
   }
   
   public Concept(String concept) {
      this(concept, null, 0, new ArrayList());
   }
   
   public void setTempTerm(String tempTerm) {this.tempTerm = tempTerm;}
   public String getTempTerm() {return tempTerm;}
   public void addDerivedConcept(Concept c) {derived.add(c);}
   public void setConcept(String concept) {this.concept = concept;}
   public void setUrl(String url) {this.url = url;}
   public void setRanking(double ranking) {this.ranking = ranking;}
   public void setTokens(List tokens) {this.tokens = tokens;}
   public String getConcept() {return concept;}
   public String getUrl() {return url;}
   public double getRanking() {return ranking;}
   public List getTokens() {return tokens;}
   public List getDerivedConcepts() {return derived;}
   public void setSize(int size) {this.size = size;}
   public int getSize() {return size;}
   public int getMaxDerivedSize() {
      int size = 0;
      for (Iterator i = derived.iterator(); i.hasNext();) {
         Concept c = (Concept) i.next();
         if (size < c.getSize())
            size = c.getSize();
      }
      return size;
   }
   public String toString() {
      return concept + " [" + ranking + "]";
      //String rs = "{Concept: " + concept + "; URL: " + url + "; Ranking: " + ranking + "; Tokens: " + tokens.toString() + "; Derived: " + derived.toString() + "}";
      //return rs;
      //return "<html><font color=\"" + ((url == null) ? "blue" : "red") + "\">" + concept + "</font> [" + ranking + "]</html>";
   }
  
}
