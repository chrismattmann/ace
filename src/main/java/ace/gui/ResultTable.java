/*
 * ResultTable.java
 *
 * Created on April 18, 2004, 2:04 PM
 */

package ace.gui;

import java.util.*;
import javax.swing.JTable;
import ace.agents.data.ResultTuple;

/**
 *
 * @author  pramirez
 */
public class ResultTable {
   private JTable resultTable;
   private List results;
   
   /** Creates a new instance of ResultTable */
   public ResultTable(JTable resultTable) {
      this.resultTable = resultTable;
      this.results = new ArrayList();
   }
   
   public void addResults(List results) {
      this.results.addAll(results);
      display();
   }
   
   public void putResults(List results) {
      this.results = new ArrayList();
      this.results.addAll(results);
      display();
   }
   
   private void display() {
      clear();
      int n=0;
      for (Iterator i = results.iterator(); i.hasNext();) {
         ResultTuple rt = (ResultTuple) i.next();
         resultTable.setValueAt(rt.getSiteName(), n, 0);
         resultTable.setValueAt(rt.getLink(), n, 1);
         resultTable.setValueAt(rt.getDesc(), n, 2);
         resultTable.setValueAt(rt.getSource(), n, 3);
         n++;
      }
   }
   
   private void clear() {
      for (int i=0; i<resultTable.getRowCount(); i++) {
         resultTable.setValueAt(null, i, 0);
         resultTable.setValueAt(null, i, 1);
         resultTable.setValueAt(null, i, 2);
         resultTable.setValueAt(null, i, 3);
      }
   }
   
   public void reset() {
      results = new ArrayList();
   }
}
