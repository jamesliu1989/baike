package com.nju.iip.baike.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		String[] entrys = {"自然", "文化", "人物", "历史", "生活", "社会", "艺术", "经济", "科学", "体育", "技术", "地理", "HOT"};
		getChildEntryArray("经济");
	}
	
	public static void getChildEntryArray(String entry){
		saveChildEntryList(entry);
		
		String url = "http://www.baike.com/category/Ajax_cate.jsp?catename=" + entry;
		String jsonString = Crawer.getPage(url).text();
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		
		for (int i = 0; i < jsonArray.size(); i++) {
			String curEntry = ((JSONObject)jsonArray.get(i)).getString("name");
			getChildEntryArray(curEntry);
		}
	}
	
	public static void saveChildEntryList(String entry){
		String url = "http://fenlei.baike.com/"+ entry + "/list";
		Document doc = Crawer.getPage(url);
		if(doc == null)
			return;
		Elements elements = doc.select("dd a");
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File("E:\\桌面\\实验\\baike\\" + entry +".txt")));
			writer.write(entry);
			for (int i = 0; i < elements.size(); i++) {
				String curEntry = elements.get(i).text();
				System.err.println(curEntry);
				writer.newLine();
				writer.write(curEntry);
		    }
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
