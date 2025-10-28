package com.searchEngine.websiteClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import java.io.IOException;
import java.lang.InterruptedException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import java.lang.StringBuilder;


public class WebsiteClient{

	public String getReadyHTML(String Link){
		return this.formatHTML(this.getPlainHTML(Link));
	}

	public String getPlainHTML(String Link){
		HttpResponse<String> response = null;
		try{
			HttpClient client = HttpClient.newBuilder()
				.build();

			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Link))
				.GET()
				.build();

			response = client.send(request, BodyHandlers.ofString());
			return response.body();

		}catch(IOException | InterruptedException ex){
			System.out.println("Error: Failed fetching HTML");
		       	return "";	
		}
	}

	public String formatHTML(String htmlString){
		if(htmlString == null | htmlString.isEmpty()){
			return "";
		}
		Document htmlDoc = Jsoup.parse(htmlString);
		StringBuilder strBuilder = new StringBuilder();

		for (Node nd : htmlDoc.body().childNodes()){
			this.processNode(nd, strBuilder);	
		}

	return strBuilder.toString();
	}

	public void processNode(Node node, StringBuilder strBuilder){
		if (node instanceof Element){
			Element element = (Element) node;
			if (element.tagName().equals("br")){
				strBuilder.append("/n");
			}
			else{
				for(Node childNode : element.childNodes()){
					processNode(childNode, strBuilder);
				}
			}
		}
		else if (node instanceof TextNode){
			TextNode textNode = (TextNode) node;
			strBuilder.append(textNode.text());
		}
	}	
}




