package com.searchEngine.searchService;

import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.dto.SearchHit;
import com.searchEngine.apiClient.APIClientInterface;
import com.searchEngine.apiClient.APIClient;
import java.util.LinkedList;

public class SearchService{

	private APIClientInterface apiClient = new APIClient();

	public SearchService() {
	}

	public LinkedList<SearchResultInterface> getSearchResult(String query){
		LinkedList<SearchResultInterface> Response = new LinkedList<>(); 
		LinkedList<SearchHit> rawResponse = apiClient.fetchResponse(query);
		for(SearchHit sh : rawResponse){
			SearchResultInterface Result = new SearchResult(sh.getLink(), sh.getDescription());
			Response.add(Result);
		}
		return Response;
	}
}
		
		
