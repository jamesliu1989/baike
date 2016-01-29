package com.nju.iip.baike.main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawer {

	public static Document getPage(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url)
					  //.data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(30000)
					  .get();
		} catch (IOException e) {
			System.err.println(url);
			e.printStackTrace();
		}
		return doc;
	}
}
