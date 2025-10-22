package com.searchEngine.searchResult;

public class SearchResult implements SearchResultInterface{

	private final String link;
	private final String description;

	public SearchResult(String link, String description){
		this.link = link;
		this.description = description;
	}

	@Override
	public String getLink(){
		return this.link;
	}

	@Override
	public String getDescription(){
		return this.description;
	}
}
