package com.searchEngine.apiClient;

import java.util.LinkedList;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.searchResult.SearchResultInterface;

public interface APIClientInterface{
	
	LinkedList<SearchResultInterface> fetchResponse(String searchQuery);


}
