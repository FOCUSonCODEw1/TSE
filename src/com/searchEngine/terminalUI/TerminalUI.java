package com.searchEngine.terminalUI;

import java.util.Scanner;
import java.util.LinkedList;
import com.searchEngine.apiClient.*;
import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.searchResult.SearchResultInterface;

public class TerminalUI{
	
	private final Scanner scanner = new Scanner(System.in);
	private APIClientInterface apiClient = new APIClient();
	private String recentInput;
	private boolean isLinkedList;


	public void run(String[] args){
		System.out.println("Welcome to openTSE");
		while (true){
			this.handleRequest();
		}
	}

	public void handleRequest(){
		recentInput = scanner.nextLine();
		this.displayLinkedList(apiClient.fetchResponse(recentInput));
	}

	public void displayLinkedList(LinkedList<SearchResultInterface> linkList){
		for (int i = 0; i < linkList.size(); i++){
			String link = linkList.get(i).getLink();
			int x = i + 1;
			System.out.println("[#"+ x +"] -->"+ link);
			System.out.println("");
		}
	}

			

	
}	
