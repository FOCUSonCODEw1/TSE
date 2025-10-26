package com.searchEngine.websiteClient;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.StringBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;


public class WebsiteClient{


	public String getReadyHTML(String Link){
		return this.formatHTML(this.getPlainHTML(Link));
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

	public String formatHTML(String HTMLString){
		Document htmlDoc = Jsoup.parse(HTMLString);
		StringBuilder strBuilder = new StringBuilder();

		for (Node nd : htmlDoc.body().childNodes()){
			if (nd instanceof Element){
				Element element = (Element) nd;
				if (element.tagName().equals("<br>")){
					strBuilder.append("/n");
				}
				else{
					strBuilder.append(element.text());
				}
			}
			if (nd instanceof TextNode){
				TextNode textNode = (TextNode) nd;
				strBuilder.append(textNode.text());
			}
		}
	return strBuilder.toString();

	}


}




