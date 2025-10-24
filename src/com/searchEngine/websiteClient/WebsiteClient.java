package com.searchEngine.websiteClient;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import java.io.IOException;
import java.lang.InterruptedException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class WebsiteClient{

	public String getParsedHTML(String Link){
		return this.parseHTML(this.getPlainHTML(Link));
	}

	public String getPlainHTML(String Link){
		
		String Response = "";
		HttpResponse<String> response = null;

		try{
			HttpClient client = HttpClient.newBuilder()
				.build();

			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Link))
				.GET()
				.build();

			response = client.send(request, BodyHandlers.ofString());
		}catch(IOException | InterruptedException ex){
			ex.printStackTrace();
		}
		
		Response = response.body();

		return Response;
	}

	public String parseHTML(String plainHTML){
		Document doc = Jsoup.parse(plainHTML);
		return doc.text();
	}
}




