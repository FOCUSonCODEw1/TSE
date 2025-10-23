package com.searchEngine.dto;

public class SearchHit{

	public String link;
	public String description;
	public String title;
	public String date;

	public SearchHit(String link, String description){
		this.link = link;
		this.description = description;
	}

	public String getLink(){
		return this.link;
	}

	public String getDescription(){
		return this.description;
	}



}
