package com.test009;

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
			
			String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s","184");
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			doc = builder.parse(is);
			
			XPath xpath = XPathFactory.newInstance().newXPath();

			
			
			NodeList locationList = (NodeList)xpath.compile("/rss/channel/item/description/body/location").evaluate(doc, XPathConstants.NODESET);
			
			for(int i = 1; i < locationList.getLength(); i++) {
				/*
				Node locationNode = locationList.item(i);
				Element locationElement = (Element)locationNode;
				
				Node cityNode = locationElement.getElementsByTagName("city").item(0);
				Element cityElement = (Element)cityNode;
				String city = cityElement.getTextContent();
				*/
				
				String city = xpath.compile(String.format("/rss/channel/item/description/body/location[%s]/city",i)).evaluate(doc);
				System.out.println("-------------------------");
				System.out.printf("���� : %s%n", city);
				
				NodeList dataList = (NodeList)xpath.compile(String.format("/rss/channel/item/description/body/location[%s]/data",i)).evaluate(doc, XPathConstants.NODESET);
				   
				for(int j = 1; j < dataList.getLength(); j++) {
					String tmEf = xpath.compile(String.format("/rss/channel/item/description/body/location[%s]/data[%s]/tmEf",i,j)).evaluate(doc);
					String wf = xpath.compile(String.format("/rss/channel/item/description/body/location[%s]/data[%s]/wf",i,j)).evaluate(doc);
					System.out.printf("��¥ : %s , ���� : %s%n", tmEf, wf);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
