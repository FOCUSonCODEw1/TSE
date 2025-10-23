package com.searchEngine.apiClient;

import com.searchEngine.searchResult.SearchResult;
import com.searchEngine.searchResult.SearchResultInterface;
import com.searchEngine.dto.SearchHit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.LinkedList;

public class APIClient implements APIClientInterface{
	
		@Override
		public LinkedList<SearchHit> fetchResponse(String searchQuery){
			String url = "http://localhost/search?q="+ searchQuery +"&category_general=1&language=auto&time_range=&safesearch=0&theme=simple";
			Elements results;
			LinkedList<SearchHit> Results = new LinkedList<>();
			try{
				Document document = Jsoup.connect(url)
   				 .userAgent("Mozilla/5.0")
   				 .get();
				results = document.select("article.result");
				for(Element rs : results){
					Element link  = rs.selectFirst("article.result > h3 > a");
					String hrefLink = link.attr("href");
					Results.add(new SearchHit(hrefLink, ""));
				}
	
			}catch(IOException e){
				e.printStackTrace();
			}
			return Results;


		
}
}
