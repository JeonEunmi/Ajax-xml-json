package com.test010;

import java.net.URL;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import javax.xml.xpath.*;


public class Main {

	public static void main(String[] args) {
		
		// ���� ��û XML ������ �޸𸮿� �ε��ϰ� �м�, ��� ���
		// ���� ���,
		// ���û ���� �߱⿹�� : http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = null;
			
			String str = "http://rss.hankooki.com/daily/dh_it_tech.xml";
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			doc = builder.parse(is);
			
			XPath xpath = XPathFactory.newInstance().newXPath();

			Node channelNode = (Node)xpath.compile("/rss/channel").evaluate(doc, XPathConstants.NODE);
			String bigTitle = xpath.compile("title").evaluate(channelNode);
			String lastBuildDate = xpath.compile("lastBuildDate").evaluate(channelNode);
			
			System.out.println(bigTitle);
			System.out.println(lastBuildDate);
			System.out.println("--------------------");
			
			
			NodeList itemNodeList = (NodeList)xpath.compile("/rss/channel/item").evaluate(doc, XPathConstants.NODESET);
			for(int i = 1; i < itemNodeList.getLength(); i++) {
				
				Node itemNode  = (Node)xpath.compile(String.format("/rss/channel/item[%s]",i)).evaluate(doc, XPathConstants.NODE);
				
				String title = xpath.compile("title").evaluate(itemNode);
				String link = xpath.compile("link").evaluate(itemNode);
				String description = xpath.compile("description").evaluate(itemNode);
				String pubDate = xpath.compile("pubDate").evaluate(itemNode);
				
				System.out.printf("TITLE : %s%n", title);
				System.out.printf("LINK : %s%n", link);
				System.out.printf("DESCRIPTION : %s%n", description);
				System.out.printf("PUBDATE : %s%n", pubDate);
				System.out.println("--------------------");

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
