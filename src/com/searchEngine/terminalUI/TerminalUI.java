package com.searchEngine.terminalUI;

import java.util.Scanner;
import com.searchEngine.apiClient.*;

public class TerminalUI{
	
	private final Scanner scanner = new Scanner(System.in);
	private String recentInput;


	public void run(String[] args){
		System.out.println("Welcome to openTSE");
		recentInput = scanner.nextLine();
		APIClientInterface apiClient = new APIClient();
		System.out.println(apiClient.fetchResponse(recentInput).toString());
	}
	
}	
