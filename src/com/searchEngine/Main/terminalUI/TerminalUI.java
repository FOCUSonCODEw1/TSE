package termalUI;

import java.utils.Scanner;

public class TerminalUI{
	
	private final Scanner scanner = new Scanner(System.in);
	private String recentInput;


	public void run(String[] args){
		System.out.println("Welcome to openTSE");
		recentInput = scanner.nextLine();
		APIClientInterface apiClient = new APIClient();
			apiClient.fetchResponse(recentInput);
	}
		
