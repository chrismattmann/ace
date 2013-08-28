package ace.scorer;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import ace.tf.StopWordSet;
import ace.tf.TFGenerator;
import ace.util.Token;
import ace.util.Concept;

/*
 * TFScorer
 *
 * @author pramirez
 *
*/

public class TFScorer implements IScorer {
   public TFScorer() {}
	
   public Map score(List tokens, StopWordSet stopWords) {
      TFGenerator tfg = new TFGenerator(tokens, stopWords);
      Map frequencies = tfg.getTF();
      Map terms = new HashMap();
      Map concepts = new HashMap();
      
      for (Iterator i = tokens.iterator(); i.hasNext();) {
         Token t = (Token) i.next();
         List toks = (List) terms.get(t.getTerm().toUpperCase());
         if (toks == null) {
            toks = new ArrayList();
            terms.put(t.getTerm().toUpperCase(), toks);
         }
         toks.add(t);
      }
      //System.out.println("Frequencies: " + frequencies);
      for (Iterator i = frequencies.keySet().iterator(); i.hasNext();){
         String term = (String) i.next();
         Double ranking = (Double) frequencies.get(term); //Should be upper case Change in algorithm too
         List toks = (List) terms.get(term.toUpperCase());
         concepts.put(term.toUpperCase(), new Concept(term, ranking, toks)); //Changed toUpper
      }
      
      return concepts;
   }   
	
}