package ace.scorer;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import ace.tf.StopWordSet;
import ace.util.Token;
import ace.util.Concept;
import javax.swing.text.html.*;
import ace.util.HTMLTags;

/*
 * HTMLScorer
 *
 * @author pramirez
 *
*/

public class HTMLScorer implements IScorer {
   private HTMLTags tagLib = new HTMLTags();
   
   public HTMLScorer() {}
	
   public Map score(List tokens, StopWordSet stopWords) {
      Map frequencies = new HashMap();
      Map rankings = new HashMap();
      Map tokenMap = new HashMap();
      Map seenTags = new HashMap();
      
      for (Iterator i = tokens.iterator(); i.hasNext();) {
         Token token = (Token) i.next();
         if (!stopWords.contains(token.getTerm().toUpperCase())) {
            List toks = (List) tokenMap.get(token.getTerm().toUpperCase());
            if (toks == null) {
               toks = new ArrayList();
               tokenMap.put(token.getTerm().toUpperCase(), toks);
            }
            toks.add(token);
            
            Double rank = (Double) rankings.get(token.getTerm().toUpperCase());
            List seenTagList = (List) seenTags.get(token.getTerm().toUpperCase());
            if (rank == null) {
               rankings.put(token.getTerm().toUpperCase(), new Double(tagLib.getWeight(token.getTag())));
               seenTagList = new ArrayList();
               seenTagList.add(token.getTag());
               seenTags.put(token.getTerm().toUpperCase(), seenTagList);
               
            } else {
               if (!seenTagList.contains(token.getTag())) {
                  seenTagList.add(token.getTag());
                  rankings.put(token.getTerm().toUpperCase(), new Double(rank.doubleValue() + tagLib.getWeight(token.getTag())));
               }
            }
         }
      }
      
      double max = 0;
      for (Iterator i = tokenMap.keySet().iterator(); i.hasNext();) {
         String upperToken = (String) i.next();
         Double rank = (Double) rankings.get(upperToken);
         double ranking = rank.doubleValue();
         System.out.println("Token: " + upperToken + "; Rank: " + ranking);
         if (max < ranking)
            max = ranking;
      }
      
      System.out.println("Max: " + max);   
      
      Map concepts = new HashMap();
      for (Iterator i = tokenMap.keySet().iterator(); i.hasNext();) {
         String upperToken = (String) i.next();
         List toks = (List) tokenMap.get(upperToken);
         String concept = ((Token)toks.get(0)).getTerm();
         double rank = ((Double)rankings.get(upperToken)).doubleValue()/max;
         System.out.println("Concept: " + concept + "; Rank: " + rank);
         concepts.put(concept.toUpperCase(), new Concept(concept, rank, toks)); //Changed toUpper
      }
      
      return concepts;
   }   
	
}