package com.searchEngine.terminalUI;

import com.searchEngine.apiClient.SearXNGClient;
import com.searchEngine.searchService.SearchService;
import com.searchEngine.searchResult.SearchResultInterface;
import java.util.Scanner;
import java.util.LinkedList;


public class TerminalUI{
	
	private final Scanner scanner = new Scanner(System.in);
	private final SearchService searchService = new SearchService();
	private String recentInput;
	private String lastSearchQuery = null;
	private final char HTML_VIEW_PREFIX = '#';
	private final int MAX_LINE_LENGTH = 70;


	public void run(String[] args){
		System.out.println("Welcome to openTSE");
		while (true){
			this.handleInput();
		}
	}

	public void handleInput(){
		recentInput = scanner.nextLine();

		switch (recentInput.charAt(0)) {
			case HTML_VIEW_PREFIX:
				int resultCounter;
			       	try{	
					resultCounter = Integer.parseInt(recentInput.substring(1));
				}catch(NumberFormatException ex){
					System.out.println("Error: Invalid number format!");
					break;
				}

				if(lastSearchQuery == null | lastSearchQuery.isEmpty()){
					System.out.println("Error: Can't show website's HTML before getting search query!");
					break;
				}
				
				this.displayHTML(searchService.getWebsitesHTML(lastSearchQuery, resultCounter));
				break;
			default: 
				this.displayResultList(searchService.getSearchResult(recentInput));
				lastSearchQuery = recentInput;
				break;
		}		
	}

	public void displayResultList(LinkedList<SearchResultInterface> resultList){

		int resultCounter = 1;
		for(SearchResultInterface searchResult : resultList){
			String link = searchResult.getLink();
			String description = searchResult.getDescription();
			System.out.println("========================================");
			System.out.println("[#"+ resultCounter +"] -->"+ link);
			System.out.println();
			System.out.println(this.formatDescription(description, MAX_LINE_LENGTH));
			System.out.println();
			resultCounter++;
		}
	}
	
	public void displayHTML(String html){
		System.out.println(html);
	}

	public String formatDescription(String unformatedText, int maxLineLength){
		int currentLineLength = 0;
		StringBuilder formatedText = new StringBuilder();
		String[] words = unformatedText.split("\\s+");
		for(String word : words){
			if(currentLineLength > maxLineLength && currentLineLength > 0){
				formatedText.append("\n");
				currentLineLength = 0;
			}

			if(currentLineLength > 0){
				formatedText.append(" ");
				currentLineLength++;
			}

			formatedText.append(word);
			currentLineLength = currentLineLength + word.length();
		}

		return formatedText.toString();
		
	}


}	
