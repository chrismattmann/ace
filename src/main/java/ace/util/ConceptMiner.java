/*
 * ConceptMiner.java
 *
 * Created on April 17, 2004, 6:24 PM
 */

package ace.util;

import ace.tf.StopWordSet;
import ace.scorer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

/**
 *
 * @author  pramirez
 */
public class ConceptMiner {
   private double threshold;
   private int breadth;
   private int maxConceptSize = 1;
   
   public ConceptMiner(double threshold, int breadth) {
      this.threshold = threshold;
      this.breadth = breadth;
   }
   
   public int getMaxConceptSize() {return maxConceptSize;}
   
   /*
   * Mines concepts above a given threshold
   */
   public List mineConcepts(List tokens, StopWordSet stopWords, List scorers, double [] weights) {
      List concepts = new ArrayList();
      
      //First do all the scoring. In the future this can be done in parallel.
      List scoredConcepts = new ArrayList();
      for (Iterator i = scorers.iterator(); i.hasNext();) {
         IScorer scorer = (IScorer) i.next();
         scoredConcepts.add(scorer.score(tokens, stopWords));         
      }
      
      //Next apply weights and form single set of concepts
      List weighted = new ArrayList();
      Map cm = (Map) scoredConcepts.remove(0);
      double max = 0;
      for (Iterator i = cm.keySet().iterator(); i.hasNext();) {
         Concept c = (Concept) cm.get((String) i.next());
         double ranking = (c.getRanking() * weights[0])/weights.length;
         int n = 1;
         for (Iterator i2 = scoredConcepts.iterator(); i2.hasNext();) {
            Map scoreMap = (Map) i2.next();
            Concept c2 = (Concept) scoreMap.get(c.getConcept().toUpperCase());  //changed toUpper
            ranking += (c2.getRanking() * weights[n]); // /weights.length;
            n++;
         }
         c.setRanking(ranking);
         if (max < ranking)
            max = ranking;
         weighted.add(c);
      }
      
      //If is more than 1 scorer we need to normalize weights
      if (scorers.size() > 1) {
         for(Iterator i = weighted.iterator(); i.hasNext();) {
            Concept c = (Concept) i.next();
            c.setRanking(c.getRanking()/max);
         }
      }        
      //End normalization
      
      List qualified = new ArrayList();
      //Dump out all concepts that don't meet the threshold
      for (Iterator i = weighted.iterator(); i.hasNext();) {
         Concept c = (Concept) i.next();
         if (c.getRanking() >= threshold)
            qualified.add(c);
      }
      
      Map conceptMap = new HashMap();
      Map frequencyMap = new HashMap();
      for (Iterator i = qualified.iterator(); i.hasNext();) {
         Concept c = (Concept) i.next();
         List maximizedTokens = maximizeTokens(tokens, new ArrayList(c.getTokens()));
         Map conceptFreqMap = new HashMap();
         conceptMap.put(c.getConcept().toUpperCase(), c);
         for (Iterator i2 = maximizedTokens.iterator(); i2.hasNext();) {
            TempToken tt = (TempToken) i2.next();
            if (!tt.getTempTerm().equals(c.getConcept().toUpperCase())) {
               if (conceptFreqMap.get(tt.getTempTerm()) == null) {
                  Concept temp = new Concept(tt.getTerm());
                  temp.setTempTerm(tt.getTempTerm());
                  temp.setSize(tt.getSize());
                  c.addDerivedConcept(temp);
               }
               //Tracks overall frequencies
               Integer allCount = (Integer) frequencyMap.get(tt.getTempTerm());
               if (allCount == null) {
                  frequencyMap.put(tt.getTempTerm(), new Integer(1));
               } else {
                  frequencyMap.put(tt.getTempTerm(), new Integer(allCount.intValue() + 1));
               }
               //Tracks local frequencies
               Integer count = (Integer) conceptFreqMap.get(tt.getTempTerm());
               if (count == null) {
                  conceptFreqMap.put(tt.getTempTerm(), new Integer(1));
               } else {
                  conceptFreqMap.put(tt.getTempTerm(), new Integer(count.intValue() + 1));
               }
            }            
         }
         setDerivedRankings(c, conceptFreqMap); 
         System.out.println("Frequencies for " + c.getConcept() + ": " + conceptFreqMap);
      }
      System.out.println("Overall Frequencies: " + frequencyMap);
      
      //Find max concept size
      for (Iterator i = conceptMap.values().iterator(); i.hasNext();) {
         Concept c = (Concept) i.next();
         int temp = c.getMaxDerivedSize();
         if (maxConceptSize < temp)
            maxConceptSize = temp;
      }          
      
      return new ArrayList(conceptMap.values());
   }
   
   private void setDerivedRankings(Concept c, Map conceptFreqMap) {
      //First find max occurences
      double max = 0;
      for (Iterator i = conceptFreqMap.values().iterator(); i.hasNext();) {
         double temp = ((Integer) i.next()).intValue();
         if (max < temp)
            max = temp;
      }
      
      for (Iterator i = c.getDerivedConcepts().iterator(); i.hasNext();) {
         Concept concept = (Concept) i.next();
         int frequency = ((Integer) conceptFreqMap.get(concept.getTempTerm())).intValue();
         double ranking = ((double) frequency)/max;
         concept.setRanking(ranking);
      }
   }
   
   public List maximizeTokens(List tokenSet, List tokens) {
      List tokenMaps = new ArrayList();      
      for (Iterator i = tokens.iterator(); i.hasNext();) {
         tokenMaps.add(generateTokenMap(tokenSet, (Token) i.next()));
      }
      
      List maxTokens = new ArrayList();
      for (int i=0; i<tokenMaps.size(); i++) {
         Map tokenMap = (Map) tokenMaps.get(i);
         maxTokens.addAll(findMaxTokens(tokenMaps, tokenMap, i));
         //System.out.println("Max Tokens: " + maxTokens); 
      }
      /* Weed out duplicated concepts at this level OLD_WAY
      Map maximizedTokens = new HashMap();
      for (Iterator i = maxTokens.iterator(); i.hasNext();) {
         TempToken tt = (TempToken) i.next();
         maximizedTokens.put(tt.getTempTerm(), tt);
      }
         
      return new ArrayList(maximizedTokens.values());
      */
      return maxTokens;
   }
   
   public List findMaxTokens(List tokenMaps, Map tokenMap, int ignoreIndex) {
      List maxTokens = new ArrayList();
      boolean stop = false;
      //System.out.println("Base Set: " + tokenMap);
      for (int n=breadth; n>=1 && !stop; n--) {
         Map tempTokens = (Map) tokenMap.get(new Integer(n));
         //System.out.println("Compare Set Size: " + tokenMaps.size());
         for (int i = 0; i<tokenMaps.size(); i++) {
            if (i != ignoreIndex) {
               Map tMap = (Map) tokenMaps.get(i);
               Map tSet = (Map) tMap.get(new Integer(n));
               //System.out.println("Compare Set: " + tSet);
               List max = findOverlap(tempTokens, tSet);
               if (max.size() > 0) {
                  maxTokens.addAll(max);
                  //stop = true;                              //Testing to see if THIS REALLY HELPS OR JUST HINDERS
               }
            }
         }
      }
      return maxTokens;
   }
   
   public List findOverlap(Map source, Map dest) {
      List overlap = new ArrayList();
      for (Iterator i = source.keySet().iterator(); i.hasNext();) {
         String term = (String) i.next();
         if (dest.get(term) != null)
            overlap.add((TempToken) dest.get(term));
      }
      return overlap;
   }
   
   public Map generateTokenMap(List tokenSet, Token token) {
      Map tokenMap = new HashMap();
      for (int i=1; i<=breadth; i++) {
         tokenMap.put(new Integer(i), generateTokenSet(tokenSet, token, i));
      }
      return tokenMap;
   }
   
   public Map generateTokenSet(List tokenSet, Token token, int size) {
      Map tokens = new HashMap();
      for (int i = token.getPosition()-(size-1); i<=token.getPosition(); i++) {
         if (i >= 0) {
            TempToken t = generateToken(tokenSet, i, size);
            if (t != null)
               tokens.put(t.getTempTerm(), t);
         }
      }
      return tokens;
   }
   
   public TempToken generateToken(List tokenSet, int position, int size) {
      if (tokenSet.size() >= position+size) {
         StringBuffer term = new StringBuffer();
         StringBuffer tempTerm = new StringBuffer();
         for (int i = position; i < position+size; i++) {
            if (i < position+size-1) {
               term.append(tokenSet.get(i).toString() + " ");
               tempTerm.append(tokenSet.get(i).toString().toUpperCase() + "_");
            }
            else {
               term.append(tokenSet.get(i).toString());
               tempTerm.append(tokenSet.get(i).toString().toUpperCase());
            }
         }
         return new TempToken(tempTerm.toString(), term.toString(), size);
      }
      else
         return null;
   } 
   
   public static void main(String [] args) throws Exception {
      ConceptMiner cm = new ConceptMiner(0.5, 4);
      List scorers = new ArrayList();
      scorers.add(new HTMLScorer());
      StopWordSet stopWords = new StopWordSet();
      stopWords.loadStopWords(args[1]);
      HTMLParser htmlParser = new HTMLParser(args[0], stopWords);
      htmlParser.doParse();
      System.out.println("Terms: " + htmlParser.getTokens());
      System.out.println("Concepts: " + cm.mineConcepts(htmlParser.getTokens(), stopWords, scorers, new double[] {1.0}));
   }
}
