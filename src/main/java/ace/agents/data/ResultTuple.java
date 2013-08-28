package ace.agents.data;

/*
 *
 * @author mattmann
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


public class ResultTuple{
   protected String description=null;
   protected String link=null;
   protected String siteName=null;
   protected String source=null;   
   
   public ResultTuple(){
      description="";
      link="";
      siteName="";
      source="";
   }
   
   public ResultTuple(String ds,String lnk,String sn, String s){
      description = ds;
      link = lnk;
      siteName = sn;
      source = s;
   }
   
   public String toString(){
      String rStr="";
      
      rStr+="Description: "+description+"\n";
      rStr+="Link: "+link+"\n";
      rStr+="Site: "+siteName+"\n";
      
      return rStr;
   }
   
   public String getSiteName(){return siteName;}
   public String getLink(){return link;}
   public String getDesc(){return description;}
   public String getSource() {return source;}
   
   public void setSiteName(String sn){siteName = sn;}
   public void setLink(String lnk){link = lnk;}
   public void setDesc(String ds){description = ds;}
   public void setSource(String s) {source = s;}
   
   
}