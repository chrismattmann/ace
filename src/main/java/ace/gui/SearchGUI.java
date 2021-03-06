/*
 * SearchGUI.java
 *
 * Created on April 16, 2004, 4:15 PM
 */

package ace.gui;

import ace.util.SiteFinder;
import ace.util.SiteRanker;
import ace.util.Search;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import ace.tf.StopWordSet;
import ace.util.ConceptMiner;
import ace.util.Concept;
import java.util.ArrayList;
import ace.util.HTMLParser;
import ace.scorer.TFScorer;
import ace.scorer.HTMLScorer;
import java.util.Comparator;


/**
 *
 * @author  pramirez
 */
public class SearchGUI extends javax.swing.JFrame {
   
   /** Creates new form SearchGUI */
   public SearchGUI() {
      initComponents();
      results = new ResultTable(resultTable);
      ranker = new SiteRanker(suggestedSites, results);
      search = new Search(results);
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   private void initComponents() {//GEN-BEGIN:initComponents
      java.awt.GridBagConstraints gridBagConstraints;

      jSplitPane1 = new javax.swing.JSplitPane();
      jPanel9 = new javax.swing.JPanel();
      jPanel7 = new javax.swing.JPanel();
      jScrollPane4 = new javax.swing.JScrollPane();
      conceptTree = new javax.swing.JTree(new DefaultMutableTreeNode("Concept Search"));
         jSplitPane2 = new javax.swing.JSplitPane();
         jPanel1 = new javax.swing.JPanel();
         jPanel5 = new javax.swing.JPanel();
         jLabel1 = new javax.swing.JLabel();
         siteField = new javax.swing.JTextField();
         setButton = new javax.swing.JButton();
         jPanel6 = new javax.swing.JPanel();
         jLabel3 = new javax.swing.JLabel();
         queryField = new javax.swing.JTextField();
         searchButton = new javax.swing.JButton();
         jPanel4 = new javax.swing.JPanel();
         jScrollPane1 = new javax.swing.JScrollPane();
         resultTable = new javax.swing.JTable();
         mineButton = new javax.swing.JButton();
         jSplitPane3 = new javax.swing.JSplitPane();
         jPanel2 = new javax.swing.JPanel();
         jLabel2 = new javax.swing.JLabel();
         searchField = new javax.swing.JTextField();
         findSiteButton = new javax.swing.JButton();
         jScrollPane3 = new javax.swing.JScrollPane();
         suggestedSites = new javax.swing.JTable();
         showResultButton = new javax.swing.JButton();
         showAllButton = new javax.swing.JButton();
         setSourceButton = new javax.swing.JButton();
         jPanel8 = new javax.swing.JPanel();
         jPanel10 = new javax.swing.JPanel();
         jLabel4 = new javax.swing.JLabel();
         tfThresholdField = new javax.swing.JTextField();
         jPanel11 = new javax.swing.JPanel();
         jLabel5 = new javax.swing.JLabel();
         htmlThresholdField = new javax.swing.JTextField();
         jPanel12 = new javax.swing.JPanel();
         jLabel6 = new javax.swing.JLabel();
         tfWeightField = new javax.swing.JTextField();
         jLabel7 = new javax.swing.JLabel();
         htmlWeightField = new javax.swing.JTextField();
         jLabel8 = new javax.swing.JLabel();
         breadthField = new javax.swing.JTextField();
         jLabel9 = new javax.swing.JLabel();
         combinedThresholdField = new javax.swing.JTextField();
         jLabel10 = new javax.swing.JLabel();
         tokenCountField = new javax.swing.JTextField();
         jLabel11 = new javax.swing.JLabel();
         maxConceptField = new javax.swing.JTextField();
         jPanel3 = new javax.swing.JPanel();
         jMenuBar1 = new javax.swing.JMenuBar();
         jMenu1 = new javax.swing.JMenu();
         exitMenuItem = new javax.swing.JMenuItem();

         setTitle("Automatic Concept Extraction (ACE)");
         addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
               exitForm(evt);
            }
         });

         jSplitPane1.setDividerLocation(250);
         jSplitPane1.setMinimumSize(new java.awt.Dimension(1400, 780));
         jSplitPane1.setPreferredSize(new java.awt.Dimension(1400, 780));
         jPanel9.setLayout(new java.awt.BorderLayout());

         jPanel7.setLayout(new java.awt.BorderLayout());

         conceptTree.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);

         conceptTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
               conceptTreeValueChanged(evt);
            }
         });

         jScrollPane4.setViewportView(conceptTree);

         jPanel7.add(jScrollPane4, java.awt.BorderLayout.CENTER);

         jPanel9.add(jPanel7, java.awt.BorderLayout.CENTER);

         jSplitPane1.setLeftComponent(jPanel9);

         jSplitPane2.setMinimumSize(new java.awt.Dimension(612, 602));
         jPanel1.setLayout(new java.awt.GridBagLayout());

         jPanel1.setMinimumSize(new java.awt.Dimension(800, 780));
         jPanel1.setPreferredSize(new java.awt.Dimension(1200, 900));
         jPanel5.setLayout(new java.awt.GridBagLayout());

         jPanel5.setMaximumSize(new java.awt.Dimension(32767, 25));
         jPanel5.setMinimumSize(new java.awt.Dimension(400, 25));
         jPanel5.setPreferredSize(new java.awt.Dimension(800, 25));
         jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel1.setText("Gold Source:  ");
         jLabel1.setMaximumSize(new java.awt.Dimension(41, 16));
         jLabel1.setMinimumSize(new java.awt.Dimension(100, 16));
         jLabel1.setPreferredSize(new java.awt.Dimension(41, 16));
         jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
         jPanel5.add(jLabel1, new java.awt.GridBagConstraints());

         siteField.setMinimumSize(new java.awt.Dimension(500, 20));
         siteField.setPreferredSize(new java.awt.Dimension(800, 20));
         jPanel5.add(siteField, new java.awt.GridBagConstraints());

         setButton.setText("Set");
         setButton.setMaximumSize(new java.awt.Dimension(60, 26));
         setButton.setMinimumSize(new java.awt.Dimension(60, 26));
         setButton.setPreferredSize(new java.awt.Dimension(60, 26));
         jPanel5.add(setButton, new java.awt.GridBagConstraints());

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
         jPanel1.add(jPanel5, gridBagConstraints);

         jPanel6.setMinimumSize(new java.awt.Dimension(341, 36));
         jLabel3.setText("Query: ");
         jPanel6.add(jLabel3);

         queryField.setMinimumSize(new java.awt.Dimension(200, 20));
         queryField.setPreferredSize(new java.awt.Dimension(450, 20));
         jPanel6.add(queryField);

         searchButton.setText("Search");
         searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               searchButtonActionPerformed(evt);
            }
         });

         jPanel6.add(searchButton);

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 1;
         gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
         jPanel1.add(jPanel6, gridBagConstraints);

         jPanel4.setLayout(new java.awt.GridLayout(1, 0));

         jPanel4.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder("Result List"), new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED)));
         jPanel4.setMinimumSize(new java.awt.Dimension(750, 650));
         jPanel4.setPreferredSize(new java.awt.Dimension(750, 1075));
         jScrollPane1.setPreferredSize(new java.awt.Dimension(468, 403));
         resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null},
               {null, null, null, null}
            },
            new String [] {
               "Sitename", "Link", "Description", "Source"
            }
         ) {
            Class[] types = new Class [] {
               java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
               return types [columnIndex];
            }
         });
         jScrollPane1.setViewportView(resultTable);

         jPanel4.add(jScrollPane1);

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 2;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         jPanel1.add(jPanel4, gridBagConstraints);

         mineButton.setText("Mine Concepts from Selected Result");
         mineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               mineButtonActionPerformed(evt);
            }
         });

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 3;
         gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
         jPanel1.add(mineButton, gridBagConstraints);

         jSplitPane2.setLeftComponent(jPanel1);

         jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
         jSplitPane3.setMinimumSize(new java.awt.Dimension(200, 600));
         jSplitPane3.setPreferredSize(new java.awt.Dimension(200, 600));
         jPanel2.setMinimumSize(new java.awt.Dimension(200, 400));
         jPanel2.setPreferredSize(new java.awt.Dimension(200, 400));
         jLabel2.setText("Concept:");
         jPanel2.add(jLabel2);

         searchField.setMinimumSize(new java.awt.Dimension(150, 20));
         searchField.setPreferredSize(new java.awt.Dimension(150, 20));
         jPanel2.add(searchField);

         findSiteButton.setText("Find Site");
         findSiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               findSiteButtonActionPerformed(evt);
            }
         });

         jPanel2.add(findSiteButton);

         jScrollPane3.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder("Suggested Sites"), new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED)));
         jScrollPane3.setPreferredSize(new java.awt.Dimension(300, 250));
         suggestedSites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null},
               {null, null}
            },
            new String [] {
               "Site", "Hits"
            }
         ) {
            Class[] types = new Class [] {
               java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
               false, false
            };

            public Class getColumnClass(int columnIndex) {
               return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
               return canEdit [columnIndex];
            }
         });
         jScrollPane3.setViewportView(suggestedSites);

         jPanel2.add(jScrollPane3);

         showResultButton.setText("Show Results");
         showResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               showResultButtonActionPerformed(evt);
            }
         });

         jPanel2.add(showResultButton);

         showAllButton.setText("Show All");
         showAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               showAllButtonActionPerformed(evt);
            }
         });

         jPanel2.add(showAllButton);

         setSourceButton.setText("Set as Gold Source");
         setSourceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               setSourceButtonActionPerformed(evt);
            }
         });

         jPanel2.add(setSourceButton);

         jSplitPane3.setLeftComponent(jPanel2);

         jPanel8.setLayout(new java.awt.GridBagLayout());

         jPanel8.setMinimumSize(new java.awt.Dimension(10, 250));
         jPanel8.setPreferredSize(new java.awt.Dimension(10, 250));
         jPanel10.setLayout(new java.awt.GridBagLayout());

         jPanel10.setBorder(new javax.swing.border.TitledBorder("Term Frequency Scorer"));
         jPanel10.setMinimumSize(new java.awt.Dimension(225, 50));
         jPanel10.setPreferredSize(new java.awt.Dimension(225, 50));
         jLabel4.setText("Threshold: ");
         jPanel10.add(jLabel4, new java.awt.GridBagConstraints());

         tfThresholdField.setText("0.5");
         tfThresholdField.setMinimumSize(new java.awt.Dimension(100, 20));
         tfThresholdField.setPreferredSize(new java.awt.Dimension(100, 20));
         jPanel10.add(tfThresholdField, new java.awt.GridBagConstraints());

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridheight = 2;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         jPanel8.add(jPanel10, gridBagConstraints);

         jPanel11.setLayout(new java.awt.GridBagLayout());

         jPanel11.setBorder(new javax.swing.border.TitledBorder("HTML Scorer"));
         jPanel11.setMinimumSize(new java.awt.Dimension(225, 50));
         jPanel11.setPreferredSize(new java.awt.Dimension(225, 50));
         jLabel5.setText("Threshold: ");
         jPanel11.add(jLabel5, new java.awt.GridBagConstraints());

         htmlThresholdField.setText("0.5");
         htmlThresholdField.setMinimumSize(new java.awt.Dimension(100, 20));
         htmlThresholdField.setPreferredSize(new java.awt.Dimension(100, 20));
         jPanel11.add(htmlThresholdField, new java.awt.GridBagConstraints());

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 2;
         gridBagConstraints.gridheight = 2;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         jPanel8.add(jPanel11, gridBagConstraints);

         jPanel12.setLayout(new java.awt.GridBagLayout());

         jPanel12.setBorder(new javax.swing.border.TitledBorder("Concept Miner"));
         jPanel12.setMinimumSize(new java.awt.Dimension(225, 225));
         jPanel12.setPreferredSize(new java.awt.Dimension(225, 225));
         jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel6.setText("TF Weight: ");
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(jLabel6, gridBagConstraints);

         tfWeightField.setText("0.5");
         tfWeightField.setMinimumSize(new java.awt.Dimension(100, 20));
         tfWeightField.setPreferredSize(new java.awt.Dimension(100, 20));
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(tfWeightField, gridBagConstraints);

         jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel7.setText("HTML Weight: ");
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 1;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(jLabel7, gridBagConstraints);

         htmlWeightField.setText("0.5");
         htmlWeightField.setMinimumSize(new java.awt.Dimension(100, 20));
         htmlWeightField.setPreferredSize(new java.awt.Dimension(100, 20));
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 1;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(htmlWeightField, gridBagConstraints);

         jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel8.setText("Breadth: ");
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 2;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(jLabel8, gridBagConstraints);

         breadthField.setText("5");
         breadthField.setMinimumSize(new java.awt.Dimension(100, 20));
         breadthField.setPreferredSize(new java.awt.Dimension(100, 20));
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 2;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(breadthField, gridBagConstraints);

         jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel9.setText("Threshold: ");
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 3;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(jLabel9, gridBagConstraints);

         combinedThresholdField.setText("0.5");
         combinedThresholdField.setMinimumSize(new java.awt.Dimension(100, 20));
         combinedThresholdField.setPreferredSize(new java.awt.Dimension(100, 20));
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 3;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(combinedThresholdField, gridBagConstraints);

         jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel10.setText("Token Count: ");
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 4;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(jLabel10, gridBagConstraints);

         tokenCountField.setEditable(false);
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 4;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
         jPanel12.add(tokenCountField, gridBagConstraints);

         jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         jLabel11.setText("Max Concept: ");
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 5;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         jPanel12.add(jLabel11, gridBagConstraints);

         maxConceptField.setEditable(false);
         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 1;
         gridBagConstraints.gridy = 5;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         jPanel12.add(maxConceptField, gridBagConstraints);

         gridBagConstraints = new java.awt.GridBagConstraints();
         gridBagConstraints.gridx = 0;
         gridBagConstraints.gridy = 4;
         gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
         jPanel8.add(jPanel12, gridBagConstraints);

         jSplitPane3.setRightComponent(jPanel8);

         jSplitPane2.setRightComponent(jSplitPane3);

         jSplitPane1.setRightComponent(jSplitPane2);

         getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

         getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

         jMenu1.setMnemonic('F');
         jMenu1.setText("File");
         exitMenuItem.setMnemonic('x');
         exitMenuItem.setText("Exit");
         exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               exitMenuItemActionPerformed(evt);
            }
         });

         jMenu1.add(exitMenuItem);
         jMenuBar1.add(jMenu1);
         setJMenuBar(jMenuBar1);

         pack();
      }//GEN-END:initComponents

   private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
      // Add your handling code here:
      search.query(queryField.getText(), siteField.getText());
   }//GEN-LAST:event_searchButtonActionPerformed

   private void mineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mineButtonActionPerformed
      try {
         StopWordSet stopWords = new StopWordSet();
         stopWords.loadStopWords(System.getProperty("stop.file").toString());
         HTMLParser htmlParser = new HTMLParser((String) resultTable.getValueAt(resultTable.getSelectedRow(), 1), stopWords);
         htmlParser.doParse();
         System.out.println(htmlParser.getTokens());
         tokenCountField.setText("" + htmlParser.getTokens().size());
         
         ((DefaultMutableTreeNode) conceptTree.getModel().getRoot()).removeAllChildren();
         DefaultMutableTreeNode root = (DefaultMutableTreeNode) conceptTree.getModel().getRoot();
         root.setUserObject((String) resultTable.getValueAt(resultTable.getSelectedRow(), 0));
         
         //Term Frequency         
         ConceptMiner tfcm = new ConceptMiner(Double.parseDouble(tfThresholdField.getText()), Integer.parseInt(breadthField.getText()));
         List tfScorer = new ArrayList();
         tfScorer.add(new TFScorer());
         
         List tfConcepts = tfcm.mineConcepts(htmlParser.getTokens(), stopWords, tfScorer, new double[] {1.0});
         
         DefaultMutableTreeNode tfNode = new DefaultMutableTreeNode("Term Frequency Scorer");
         root.add(tfNode);
         showConcepts(tfNode, tfConcepts, true);
         //END Term Frequency
         
         //HTML        
         ConceptMiner htmlcm = new ConceptMiner(Double.parseDouble(htmlThresholdField.getText()), Integer.parseInt(breadthField.getText()));
         List htmlScorer = new ArrayList();
         htmlScorer.add(new HTMLScorer());
         
         List htmlConcepts = htmlcm.mineConcepts(htmlParser.getTokens(), stopWords, htmlScorer, new double[] {1.0});
         
         DefaultMutableTreeNode htmlNode = new DefaultMutableTreeNode("HTML Scorer");
         root.add(htmlNode);
         showConcepts(htmlNode, htmlConcepts, true);
         //END HTML
         
         /**/
         //Combined                 
         ConceptMiner combinedcm = new ConceptMiner(Double.parseDouble(combinedThresholdField.getText()), Integer.parseInt(breadthField.getText()));
         List combinedScorers = new ArrayList();
         combinedScorers.add(new TFScorer());
         combinedScorers.add(new HTMLScorer());
         
         List concepts = combinedcm.mineConcepts(htmlParser.getTokens(), stopWords, combinedScorers, new double[] {Double.parseDouble(tfWeightField.getText()), Double.parseDouble(htmlWeightField.getText())});
         
         DefaultMutableTreeNode combinedNode = new DefaultMutableTreeNode("Combined Results");
         root.add(combinedNode);
         showConcepts(combinedNode, concepts, true);
         //END Combined
         /**/
         
         maxConceptField.setText("" + combinedcm.getMaxConceptSize());
         conceptTree.setModel(new DefaultTreeModel(root));
         conceptTree.expandPath(new TreePath(new Object[] {root, tfNode}));
         conceptTree.expandPath(new TreePath(new Object[] {root, htmlNode}));
         conceptTree.expandPath(new TreePath(new Object[] {root, combinedNode}));
      } catch(Exception ex) {
         System.out.println(ex.getMessage());
      }
      conceptTree.repaint();
   }//GEN-LAST:event_mineButtonActionPerformed
   
   private void showConcepts(DefaultMutableTreeNode root, List concepts, boolean expand) {
      Collections.sort(concepts, new Comparator() { 
                                      public int compare(Object o1, Object o2) {
                                         Concept c1 = (Concept) o1;
                                         Concept c2 = (Concept) o2;
                                         if (c1.getRanking() == c2.getRanking())
                                            return 0;
                                         else if (c1.getRanking() > c2.getRanking())
                                            return -1;
                                         else
                                            return 1;
                                         }});
      for (Iterator i = concepts.iterator(); i.hasNext();) {
         Concept c = (Concept) i.next();
         DefaultMutableTreeNode node = new DefaultMutableTreeNode(c);
         root.add(node);
         if (expand)
            showConcepts(node, c.getDerivedConcepts(), false);
      }    
   }

   private void showAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllButtonActionPerformed
      // Add your handling code here:
      ranker.showResults();
   }//GEN-LAST:event_showAllButtonActionPerformed

   private void showResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showResultButtonActionPerformed
      // Add your handling code here:
      ranker.showResultsFor((String) suggestedSites.getValueAt(suggestedSites.getSelectedRow(), 0));
   }//GEN-LAST:event_showResultButtonActionPerformed

   private void setSourceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setSourceButtonActionPerformed
      // Add your handling code here:
      if (suggestedSites.getSelectedRow() != -1)
         siteField.setText((String) suggestedSites.getValueAt(suggestedSites.getSelectedRow(),0));
   }//GEN-LAST:event_setSourceButtonActionPerformed
   
   private void findSiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findSiteButtonActionPerformed
      // Add your handling code here:
      queryField.setText(searchField.getText());
      ranker.reset();
      SiteFinder sf = new SiteFinder(ranker, searchField.getText().trim());
      sf.run();
   }//GEN-LAST:event_findSiteButtonActionPerformed
   
   private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
      // Add your handling code here:
      System.exit(0);
   }//GEN-LAST:event_exitMenuItemActionPerformed
   
   private void conceptTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_conceptTreeValueChanged
      // Add your handling code here:
      javax.swing.tree.DefaultMutableTreeNode node = (javax.swing.tree.DefaultMutableTreeNode) conceptTree.getLastSelectedPathComponent();
      
      if (node == null) return;
      Object nodeInfo = node.getUserObject();
      System.out.println(conceptTree.getSelectionPath());
   }//GEN-LAST:event_conceptTreeValueChanged
      
   /** Exit the Application */
   private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
      System.exit(0);
   }//GEN-LAST:event_exitForm
   
   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      new SearchGUI().show();
   }
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton searchButton;
   private javax.swing.JTextField siteField;
   private javax.swing.JPanel jPanel10;
   private javax.swing.JTree conceptTree;
   private javax.swing.JMenuItem exitMenuItem;
   private javax.swing.JTable resultTable;
   private javax.swing.JTable suggestedSites;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JButton mineButton;
   private javax.swing.JPanel jPanel12;
   private javax.swing.JTextField htmlThresholdField;
   private javax.swing.JButton showResultButton;
   private javax.swing.JTextField htmlWeightField;
   private javax.swing.JSplitPane jSplitPane3;
   private javax.swing.JTextField tfThresholdField;
   private javax.swing.JButton setButton;
   private javax.swing.JPanel jPanel7;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JTextField combinedThresholdField;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JTextField searchField;
   private javax.swing.JMenu jMenu1;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JTextField queryField;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JTextField tfWeightField;
   private javax.swing.JSplitPane jSplitPane2;
   private javax.swing.JButton findSiteButton;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JButton showAllButton;
   private javax.swing.JSplitPane jSplitPane1;
   private javax.swing.JScrollPane jScrollPane4;
   private javax.swing.JTextField tokenCountField;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel5;
   private javax.swing.JPanel jPanel11;
   private javax.swing.JPanel jPanel8;
   private javax.swing.JTextField breadthField;
   private javax.swing.JLabel jLabel11;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JButton setSourceButton;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JPanel jPanel9;
   private javax.swing.JTextField maxConceptField;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JMenuBar jMenuBar1;
   // End of variables declaration//GEN-END:variables
   //private javax.swing.tree.DefaultMutableTreeNode root = new javax.swing.tree.DefaultMutableTreeNode("Concept Tree");
   private SiteRanker ranker;
   private ResultTable results;
   private Search search;
}
