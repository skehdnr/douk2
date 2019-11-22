package com.douk.web.pxy;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.douk.web.pxy.Box;

@Component("crawler")
@Lazy
public class CrawlingProxy extends Proxy{
	@Autowired Inventory<HashMap<String, String>> inventory;
	@Autowired Box<String> box;
	
	
	public ArrayList<HashMap<String, String>> engCrawling(String url) {
		url = "https://endic.naver.com/?sLn=kr";
		inventory.clear();
		try {
			Document rawData = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements txtorigin = rawData.select("div[class=\"txt_origin\"] a");
			Elements txttrans = rawData.select("div[class=\"txt_trans\"]");
			HashMap<String, String>map = null;
			for (int i=0; i<txtorigin.size(); i++) {
				map = new HashMap<>();
				map.put("origin", txtorigin.get(i).text());
				map.put("teans", txttrans.get(i).text());
				inventory.add(map);
			}
			System.out.println("box에 담긴: " + inventory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********************크롤링결과********************");
		inventory.get().forEach(System.out::println);
		
		return inventory.get();
	}
	public ArrayList<HashMap<String, String>> cgvCrawling()  {
		inventory.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String cgv = "http://www.cgv.co.kr/movies/";
			Connection.Response homePage = Jsoup.connect(cgv)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = homePage.parse();
			Elements element = temp.select("div.sect-movie-chart");
			Elements tempforTitle = element.select("strong.title");
			Elements tempforPrecent = element.select("strong.percent");
			Elements tempforTextinfo= element.select("span.txt-info");
			Elements tempforphoto = temp.select("span.thumb-image");
			HashMap<String, String>map=null;
			int cgvseq = 0;
			for (cgvseq=0; cgvseq<tempforTitle.size(); cgvseq++) {
				map = new HashMap<>();
				map.put("title", tempforTitle.get(cgvseq).text());
				map.put("percent", tempforPrecent.get(cgvseq).text());
				map.put("info", tempforTextinfo.get(cgvseq).text());
				map.put("image", tempforphoto.get(cgvseq).select("img").attr("src"));
				inventory.add(map);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("********************크롤링결과********************");
		inventory.get().forEach(System.out :: println);
		return inventory.get();
	}
	public ArrayList<HashMap<String, String>> bugsCrawling() {
		inventory.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String bugsurl = "https://music.bugs.co.kr/chart";
			Connection.Response homePage = Jsoup.connect(bugsurl).method(Connection.Method.GET).userAgent(USER_AGENT)
					.execute();
			Document temp = homePage.parse();
			Elements tempforTitle = temp.select("p.title");
			Elements tempforContent = temp.select("p.artist");
			Elements tempforphoto = temp.select("a.thumbnail");
			HashMap<String, String>map=null;
		
			
			for (int i=0;i<tempforTitle.size();i++) {
				map = new HashMap<>();
				map.put("seq", string(i+1));
				map.put("title", tempforTitle.get(i).text());
				map.put("artist", tempforContent.get(i).text());
				map.put("thumbnail", tempforphoto.get(i).select("img").attr("src"));
				inventory.add(map);
			}
		} catch (Exception e) {
		}
		System.out.println("********************크롤링결과********************");
		inventory.get().forEach(System.out :: println);
		return inventory.get();
	}
	
}
