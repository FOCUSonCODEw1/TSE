package com.searchEngine.apiClient;

import com.searchEngine.dto.SearchHit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.LinkedList;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;


public class SearXNGClient implements APIClientInterface{

		private final String ARTICLE_SELECTOR = "article.result";
		private final String ANCHOR_ELEMENT_SELECTOR = "article.result > h3 > a";
		private final String PARAGRAPH_ELEMENT_SELECTOR = "article.result > p.content";
		private final String ATTRIBUTE_SELECTOR = "href";


		@Override
		public LinkedList<SearchHit> fetchResults(String searchQuery){
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

				Elements rawArticles = apiResponse.select(ARTICLE_SELECTOR);
				for(Element rawArticle : rawArticles){
					Element paragraph = rawArticle.selectFirst(PARAGRAPH_ELEMENT_SELECTOR);
					String content = "";
					if(paragraph != null){
						content = paragraph.text();
					}

					Element anchor = rawArticle.selectFirst(ANCHOR_ELEMENT_SELECTOR);
					if(anchor != null){
						String href = anchor.attr(ATTRIBUTE_SELECTOR);
						Results.add(new SearchHit(href, content));
					}
				}				
			}catch(IOException | InterruptedException ex){
				System.out.println("Error: Failed fetchingResults: " + ex.getMessage());
				return new LinkedList<SearchHit>();
			}
			return Results;
			
		}

		@Override
		public String buildURL(String searchTerm){
			return  "http://localhost/search?q="+ searchTerm +"&category_general=1&language=auto&time_range=&safesearch=0&theme=simple";
		}

}
