package ace.scorer;

import java.util.Map;
import java.util.List;
import ace.tf.StopWordSet;

/*
 * Scorer interface
 *
 * @author pramirez
 *
*/

public interface IScorer{

   /*
   * Takes in a set of tokens and stop word and returns a Map of concepts.
   */
   public Map score(List tokens, StopWordSet stopWords);
	
}