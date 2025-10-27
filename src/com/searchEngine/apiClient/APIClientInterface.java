package com.searchEngine.apiClient;

import java.util.LinkedList;
import com.searchEngine.dto.SearchHit;

public interface APIClientInterface{
	
	LinkedList<SearchHit> fetchResults(String searchQuery);

	String buildURL(String searchQuery);

}
