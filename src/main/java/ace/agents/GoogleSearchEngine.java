package ace.agents;

import java.util.List;
import java.util.Vector;
import ace.agents.data.ResultTuple;
import com.google.soap.search.*;



public class GoogleSearchEngine extends SearchEngine {

	private String clientKey = null;

	public GoogleSearchEngine() {
		super("google");
		clientKey = System.getProperty("google.clientKey","bogus");
	}

	public static void main(String[] args) {

		String theQuery = null;
		String usage = "java project.agents.GoogleSearchEngine --query [queryString]\n";

		//parse command line arguments
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--query")) {
				theQuery = args[++i];
			}

		}

		if (theQuery == null) {
			System.err.println(usage);
			System.exit(1);
		}

		List theList = new GoogleSearchEngine().query(theQuery);

		for (int i = 0; i < theList.size(); i++) {
			ResultTuple rt = (ResultTuple) theList.get(i);
			System.err.println(rt.toString());
		}
	}

	public List query(String queryStr, String site) {

		//create results
		List resList = new Vector();

		// Create a Google Search object, set our authorization key
		GoogleSearch s = new GoogleSearch();
		s.setKey(clientKey);

		// Depending on user input, do search or cache query, then print out
		// result
		try {
			s.setQueryString(queryStr);
			GoogleSearchResult r = s.doSearch();

			GoogleSearchResultElement[] resultList = r.getResultElements();

			for (int i = 0; i < resultList.length; i++) {
				GoogleSearchResultElement elem = (GoogleSearchResultElement) resultList[i];
				ResultTuple rt = new ResultTuple(elem.getSummary(), elem
						.getURL(), elem.getHostName(), sourceName);
				resList.add(rt);
			}

			return resList;
		} catch (GoogleSearchFault f) {
			System.out.println("The call to the Google Web APIs failed:");
			System.out.println(f.toString());
			return null;
		}
	}

}
