package com.searchEngine.searchService;

import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.apiClient.APIClientInterface;
import com.searchEngine.apiClient.SearXNGClient;
import com.searchEngine.websiteClient.WebsiteClient;
import com.searchEngine.dto.SearchHit;
import java.util.LinkedList;
import java.util.HashMap;

public class SearchService{
	private HashMap<String, LinkedList<SearchResultInterface>> searchResultCache = new HashMap<>();

	private final APIClientInterface searXNGClient;
	private final WebsiteClient websiteClient;
	private final int DISPLAYED_RESULTS = 10;

	public SearchService() {
		this.searXNGClient = new SearXNGClient();
		this.websiteClient = new WebsiteClient();
	}

	public LinkedList<SearchResultInterface> getSearchResult(String searchQuery){
		LinkedList<SearchResultInterface> resultsForUI = new LinkedList<>(); 
		LinkedList<SearchResultInterface> resultsForCache = new LinkedList<>();
		
		LinkedList<SearchHit> clientResponse = searXNGClient.fetchResults(searchQuery);

		
		for(int i = 0; i < clientResponse.size(); i++){
			SearchHit singleSearchHit = clientResponse.get(i);
			SearchResultInterface singleResult = new SearchResult(singleSearchHit.getLink(), singleSearchHit.getDescription());
			resultsForCache.add(singleResult);
			if (i < DISPLAYED_RESULTS){
				SearchResultInterface singleResultForUI = new SearchResult(singleSearchHit.getLink(), singleSearchHit.getDescription());
				resultsForUI.add(singleResultForUI);
			}
		}

		this.searchResultCache.put(searchQuery, resultsForCache);

		return resultsForUI;
	}

	public String getWebsitesHTML(String searchQuery, int resultCounter){
		if(resultCounter < 0){
			return "Error: Invalid index!";
		}
		int resultIndex = resultCounter - 1;

		LinkedList<SearchResultInterface> cachedResults = searchResultCache.get(searchQuery);
		if(cachedResults == null | resultIndex >= cachedResults.size()){
		       	return "Error: No cached results found for this index!";
		}

		String link = searchResultCache.get(searchQuery).get(resultCounter).getLink();
				
		return websiteClient.getReadyHTML(link);
	}
}
		
		
