package com.searchEngine.apiClient;

import java.util.LinkedList;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.dto.SearchHit;

public interface APIClientInterface{
	
	LinkedList<SearchHit> fetchResponse(String searchQuery);


}
