package com.searchEngine.searchService;

import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.dto.SearchHit;
import com.searchEngine.apiClient.APIClientInterface;
import com.searchEngine.apiClient.APIClient;
import java.util.LinkedList;
import com.searchEngine.websiteClient.WebsiteClient;
import java.util.HashMap;

public class SearchService{

	private APIClientInterface apiClient = new APIClient();

	private WebsiteClient websiteClient = new WebsiteClient();

	private HashMap<String, LinkedList<SearchResultInterface>> searchResultCache = new HashMap<>();

	public SearchService() {
	}

	public LinkedList<SearchResultInterface> getSearchResult(String query){
		LinkedList<SearchResultInterface> Response = new LinkedList<>(); 
		LinkedList<SearchHit> rawResponse = apiClient.fetchResponse(query);
		for(SearchHit sh : rawResponse){
			SearchResultInterface Result = new SearchResult(sh.getLink(), sh.getDescription());
			Response.add(Result);
		}
		this.searchResultCache.put(query, Response);
		return Response;
	}

	public String getWebsitesHTML(String searchQuery, int resultCounter){
		int ResultCounter = resultCounter - 1;
		String Link = "http://localhost";

		try{
			Link = searchResultCache.get(searchQuery).get(ResultCounter).getLink();
		}catch(IndexOutOfBoundsException ex){
			ex.printStackTrace();
		}

		return websiteClient.getParsedHTML(Link);
	}

		



}
		
		
