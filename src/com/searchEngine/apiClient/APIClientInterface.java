package com.searchEngine.apiClient;

import com.searchEngine.dto.SearchHit;
import java.util.LinkedList;


public interface APIClientInterface{
	
	LinkedList<SearchHit> fetchResults(String searchQuery);

	String buildURL(String searchQuery);

}
