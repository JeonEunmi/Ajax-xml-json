package com.test003;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {

	public static void main(String[] args) {

		try {

			// 엘리먼트명 기준으로 탐색하지 않고,
			// 자식 엘리먼트 전체를 탐색 진행한다.
			// Node객체.getChildNodes()

			File inputFile = new File("VEHICLES.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			Element root = doc.getDocumentElement();

			NodeList nList = root.getElementsByTagName("VEHICLE");
			for (int a = 0; a < nList.getLength(); ++a) {
				Element vElement = (Element) nList.item(a);

				System.out.println("---------------------");
				  // 특정 자식 엘리먼트의 텍스트 자료 반환
	            String inventory_number = vElement.getElementsByTagName("INVENTORY_NUMBER").item(0).getTextContent();
	            String make = vElement.getElementsByTagName("MAKE").item(0).getTextContent();
	            String model = vElement.getElementsByTagName("MODEL").item(0).getTextContent();
	            String year = vElement.getElementsByTagName("YEAR").item(0).getTextContent();
	            String picture = vElement.getElementsByTagName("PICTURE").item(0).getTextContent();
	            
	            System.out.printf("%s / %s / %s / %s / %s %n", inventory_number, make, model, year, picture);

				// OPTIONS 엘리먼트 탐색 및 결과 출력
				System.out.println("OPTIONS--------------");
				Node optionsNode = vElement.getElementsByTagName("OPTIONS").item(0);
				NodeList optionsChildNodes = optionsNode.getChildNodes();

				// Node 객체에는 엘리먼트 객체만 있는 것이 아니고, 텍스트 노드, 속성 노드가 있다.
				for (int b = 0; b < optionsChildNodes.getLength(); ++b) {
					// 주의) Node 객체에는 엘리먼트 객체만 있는 것이 아니고,
					// 텍스트 노드, 속성 노드가 있을 수 있다.
					Node optionsChildNode = optionsChildNodes.item(b);

					// 엘리먼트 객체인지 확인하는 과정 필요
					if (optionsChildNode.getNodeType() == Node.ELEMENT_NODE) {
						Element optionsChildElement = (Element) optionsChildNode;
						System.out.printf("%s / %s%n", optionsChildElement.getTagName(), optionsChildElement.getTextContent());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
