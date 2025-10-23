package com.searchEngine.terminalUI;

import java.util.Scanner;
import java.util.LinkedList;
import com.searchEngine.apiClient.*;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.searchService.SearchService;

public class TerminalUI{
	
	private final Scanner scanner = new Scanner(System.in);
	private final SearchService searchService = new SearchService();
	private String recentInput;

	public void run(String[] args){
		System.out.println("Welcome to openTSE");
		while (true){
			this.handleRequest();
		}
	}

	public void handleRequest(){
		recentInput = scanner.nextLine();
		char hashTag = '#';
		if(recentInput.charAt(0) != hashTag){
			this.displayLinkedList(searchService.getSearchResult(recentInput));
		}
		/*else{
			this.displayHTML(html);
		}
		*/
	}

	public void displayLinkedList(LinkedList<SearchResultInterface> linkList){
		for (int i = 0; i < linkList.size(); i++){
			String link = linkList.get(i).getLink();
			int x = i + 1;
			System.out.println("[#"+ x +"] -->"+ link);
			System.out.println("");
		}
	}
	
	public void displayHTML(String html){
	}

			

	
}	
