package com.searchEngine.apiClient;

import java.util.LinkedList;

public interface APIClientInterface{
	
	LinkedList<String> fetchResponse(String searchQuery);


}
