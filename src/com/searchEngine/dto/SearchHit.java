package com.searchEngine.dto;

public class SearchHit{

	public String href;
	public String description;
	public String title;
	public String date;

	public SearchHit(String href, String description){
		this.href = href;
		this.description = description;
	}

	public String getLink(){
		return this.href;
	}

	public String getDescription(){
		return this.description;
	}



}
