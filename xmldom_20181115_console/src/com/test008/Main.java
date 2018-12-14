package com.test008;

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
			
			String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s","105");
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			doc = builder.parse(is);
			
			System.out.println(doc.toString());
			
			Element root = doc.getDocumentElement();

			// rss-channel-item-title ������Ʈ Ž�� �� ��� ���
			// ������ ���� �߱⿹�� - 2018�� 11�� 15�� (��)���� 06:00 ��ǥ
			Node channelNode = root.getElementsByTagName("channel").item(0);
			Element channelElement = (Element)channelNode;
			Node itemNode = channelElement.getElementsByTagName("item").item(0);
			Element itemElement = (Element)itemNode;
			Node titleNode = itemElement.getElementsByTagName("title").item(0);
			Element titleElement = (Element)titleNode;
			
			System.out.println(root.getTagName());
			System.out.println(titleElement.getTextContent());
			//-> Xpath ���� 
			
			//Xpath�� ���� XML ������Ʈ Ž��
			XPath xpath = XPathFactory.newInstance().newXPath();
			/* 
			//XPath String 
			String expression = "/employees/employee[@empId='E0001']/email"; 
			//read a string value 
			String email = xpath.compile(expression).evaluate(doc); 
			//read an xml node using xpath 
			Node node = (Node)xpath.compile(expression).evaluate(doc, XPathConstants.NODE); 
			//read a nodelist using xpath NodeList
			NodeList nodeList = (NodeList)xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			*/
			String title_ = xpath.compile("/rss/channel/item/title").evaluate(doc);
			System.out.println(title_);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
