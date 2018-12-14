package com.test007;

import java.net.URL;
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class Main {

	public static void main(String[] args) {
		
		// 원격 요청 XML 문서를 메모리에 로딩하고 분석, 결과 출력
		// 예를 들어,
		// 기상청 육상 중기예보 : http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = null;
			
			String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s","105");
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			doc = builder.parse(is);
			
			System.out.println(doc.toString());
			
			Element root = doc.getDocumentElement();

			// rss-channel-item-title 엘리먼트 탐색 및 결과 출력
			// 강원도 육상 중기예보 - 2018년 11월 15일 (목)요일 06:00 발표
			Node channelNode = root.getElementsByTagName("channel").item(0);
			Element channelElement = (Element)channelNode;
			Node itemNode = channelElement.getElementsByTagName("item").item(0);
			Element itemElement = (Element)itemNode;
			Node titleNode = itemElement.getElementsByTagName("title").item(0);
			Element titleElement = (Element)titleNode;
			
			System.out.println(root.getTagName());
			System.out.println(titleElement.getTextContent());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
