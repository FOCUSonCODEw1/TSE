package com.searchEngine.apiClient;

import com.searchEngine.dto.SearchHit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.LinkedList;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import java.io.IOException;
import java.lang.InterruptedException;


public class SearXNGClient implements APIClientInterface{

		
	
		@Override
		public LinkedList<SearchHit> fetchResults(String searchQuery){
			String url = "http://localhost/search?q="+ searchQuery +"&category_general=1&language=auto&time_range=&safesearch=0&theme=simple";
			LinkedList<SearchHit> Results = new LinkedList<>();
	
			try{
				HttpClient client = HttpClient.newBuilder()
				.build();

				HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(this.buildURL(searchQuery)))
				.GET()
				.build();

				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

				Document apiResponse = Jsoup.parse(response.body());

				Elements rawArticles = document.select("article.result");
				for(Element rawArticle : rawArticles){
					Element anchor  = rawArticle.selectFirst("article.result > h3 > a");
					String href = anchor.attr("href");
					Results.add(new SearchHit(href, ""));
				}
			}catch(IOException | InterruptedException ex){
				ex.printStackTrace();
			}
			return Results;
			
		}

		@Override
		public String buildURL(String searchTerm){
			return  "http://localhost/search?q="+ searchTerm +"&category_general=1&language=auto&time_range=&safesearch=0&theme=simple";
		}

}
