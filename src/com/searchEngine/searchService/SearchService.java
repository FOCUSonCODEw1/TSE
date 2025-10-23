package com.searchEngine.searchService;

import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.apiClient.APIClientInterface;
import com.searchEngine.apiClient.APIClient;
import java.util.LinkedList;

public class SearchService{

	private APIClientInterface apiClient = new APIClient();

	public SearchService() {
	}

	public LinkedList<SearchResultInterface> getSearchResult(String query){
		return apiClient.fetchResponse(query);
	}
}
		
		
