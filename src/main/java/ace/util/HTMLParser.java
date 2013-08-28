/*
 * HTMLParser.java
 *
 * Created on April 17, 2004, 9:09 PM
 */

package ace.util;

import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.net.URLEncoder;
import ace.tf.StopWordSet;

/**
 *
 * @author  pramirez
 */
public class HTMLParser extends HTMLEditorKit.ParserCallback {
   private StringBuffer text = new StringBuffer();
   private List links = new ArrayList();
   private String baseUrl = null;
   private HTMLTags tags = new HTMLTags();
   private List seenTags = new ArrayList();
   private boolean ignore = false;
   private List tokens = new ArrayList();
   private StopWordSet stopWords = null;
   private int position = 0;
   
   
   public HTMLParser(String baseUrl, StopWordSet stopWords) {
      this.baseUrl = baseUrl;
      this.stopWords = stopWords;
   }
   
   public HTMLParser(String baseUrl) {
      this(baseUrl, new StopWordSet());
   }
   
   public void flush() throws BadLocationException {
   }
   
   public void handleText(char[] data, int pos) {
      StringBuffer sb = new StringBuffer();
      if (!ignore) {
         if (tags.isAcceptable((HTML.Tag) seenTags.get(0))) {
            try {
               for (int i=0; i<data.length; i++) {
                  if ((data[i] >= 65 && data[i] <= 90) || (data[i] >= 97 && data[i] <= 122)) {
                     text.append(data[i]);
                     sb.append(data[i]);
                  }
                  else {
                     text.append(" ");
                     sb.append(" ");   
                  }            
               }
               StringTokenizer st = new StringTokenizer(sb.toString());
               while (st.hasMoreTokens()) {
                  String t = st.nextToken();
                  //if (!stopWords.contains(t.toUpperCase()))
                     tokens.add(new Token(t, position++, (HTML.Tag) seenTags.get(0)));
               }
               
      	   } catch (Exception ex) {
      	      System.err.println("Could not encode " + data + " it was skipped.");
      	   }
         }
      }
   }
   
   public void handleComment(char[] data, int pos) {
   }
   
   public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
      if (t == HTML.Tag.SCRIPT)
         ignore = true;
      else {
         seenTags.add(0, t);
         if (t == HTML.Tag.A) {
            for (Enumeration e = a.getAttributeNames(); e.hasMoreElements();) {
               Object name = e.nextElement();
               if (name == HTML.Attribute.HREF) {
                  String value = (String) a.getAttribute(name);
                  //System.out.println(baseUrl + value);
               }
            }
         }
      }
   }
   
   public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {     
   }
   
   public void handleEndTag(HTML.Tag t, int pos) {
      if (t == HTML.Tag.SCRIPT)
         ignore = false;
      else if (!ignore && t == (HTML.Tag) seenTags.get(0)) {
         seenTags.remove(0);
      }
   }
   
   public void handleError(String errorMsg, int pos) {
   }
   
   public void handleEndOfLineString(String eol) {
   }
   
   public StringBuffer getText() {return text;}
   
   public List getTokens() {return tokens;}
   
   public void doParse() throws Exception{
      HttpURLConnection conn = (HttpURLConnection) (new URL(baseUrl)).openConnection();
      System.out.println("Getting HTML");
      System.out.println("Conent Type: " + conn.getContentType());
      if (conn.getContentType().indexOf("text/html") != -1) {
         Reader reader = new InputStreamReader(conn.getInputStream());
         
         StringBuffer theHTML = new StringBuffer();
         char [] theBuf = new char[512];
         while(reader.read(theBuf) != -1){
         	theHTML.append(theBuf);
         }
         
         /*System.err.println("Got web page, it's ");
         System.err.println(theHTML);
         
         System.err.println("Now stripping..");
         */
         
         
         String strippedHTML = theHTML.toString();
         strippedHTML = StripHTML(strippedHTML,"<style>","<var>");
         strippedHTML = StripHTML(strippedHTML,"</style>","</var>");
         
         //System.out.println(strippedHTML);
         
         StringReader sr = new StringReader(strippedHTML);
         
         new ParserDelegator().parse(sr, this, true);
      } 
      System.out.println("Parsed HTML");
   }
   
   private String StripHTML(String htmlString,String thePatternString,String theReplaceString)
   {
   		//This pattern Matches everything found inside html tags;
   		//(.|\n) - > Look for any character or a new line
   		//*?  -> 0 or more occurences, and make a non-greedy search meaning
   		//That the match will stop at the first available '>' it sees, and not at the last one
   		//(if it stopped at the last one we could have overlooked 
   		//nested HTML tags inside a bigger HTML tag..)
   		//Thanks to Oisin and Hugh Brown for helping on this one...


     Pattern thePattern = Pattern.compile(thePatternString);
     Matcher theMatcher = thePattern.matcher(htmlString);
     
     return theMatcher.replaceAll(theReplaceString);

   }

   
   public static void main(String [] args) throws Exception {
      HTMLParser htmlParser = new HTMLParser(args[0]);
      htmlParser.doParse();
      System.out.println(htmlParser.getTokens());
   }
}
