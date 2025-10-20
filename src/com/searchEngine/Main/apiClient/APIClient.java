

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class APIClient{
	public static void main(String[] args){


		
			String url = "http://localhost/search?q=java&category_general=1&language=auto&time_range=&safesearch=0&theme=simple";
			Elements results;

			try{
			Document document = Jsoup.connect(url)
   			 .userAgent("Mozilla/5.0")
   			 .get();
			results = document.select("article.result");
			for(Element rs : results){
				Element link  = rs.selectFirst("article.result > h3 > a");
				String hrefLink = link.attr("href");
				System.out.println(hrefLink);
			}

			}catch(IOException e){
				e.printStackTrace();
			}



		
}
}
