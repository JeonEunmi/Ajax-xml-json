package com.test001;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {

	public static void main(String[] args) {

		/*
		 * XML Parsing
		 * 1. XML document loading -> XML Object
		 * 2. root element
		 * 3. child element
		 * 4. text node
		 * 5. print
		 */

		try {
			// 물리적으로 존재하는 xml 파일에 대한 메모리 로딩
			// 주의) xml 파일은 프로젝트 이름 위치에 저장한다.
			File inputFile = new File("VEHICLES.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			System.out.println(doc.toString());
			

			// 루트 엘리먼트 탐색
			// XML에 존재하는 모든 엘리먼트는 루트 엘리먼트의
			Element root = doc.getDocumentElement();

			/*
			 * Node객체.getNodeName() : 노드 이름. 태그인 경우 태그 이용
			 * Node객체.getNodeType() : 노드 타입, 1인 경우 엘리먼트
			 * Node객체.getNodeValue() : 노드 값. 텍스트 노드인 경우 텍스트
			 * Element객체.getTagName() : 태그 이름
			 * Element객체.getTextContent() : 태그가 가진 텍스트 자료
			 */

			System.out.println("Root element : " + root.getTagName());
			
			
			//특정 자식 엘리먼트 탐색
			//일반적으로 반복적으로 나타나는
			//부모 역할 엘리먼트를 탐색하고,
			//반복문을 이용해서 순차적으로 접근한다.
			//부모 역할 엘리먼트의 자식 엘리먼트를 추가로 탐색
			
	         NodeList nList = doc.getElementsByTagName("VEHICLE");
	         for (int a = 0; a < nList.getLength(); ++a) {
	            // 특정 노드 객체를 엘리먼트 객체로 형변환
	            Element vElement = (Element) nList.item(a);

	            // 특정 자식 엘리먼트의 텍스트 자료 반환
	            String inventory_number = vElement.getElementsByTagName("INVENTORY_NUMBER").item(0).getTextContent();
	            String make = vElement.getElementsByTagName("MAKE").item(0).getTextContent();
	            String model = vElement.getElementsByTagName("MODEL").item(0).getTextContent();
	            String year = vElement.getElementsByTagName("YEAR").item(0).getTextContent();
	            String picture = vElement.getElementsByTagName("PICTURE").item(0).getTextContent();
	            
	            System.out.printf("%s / %s / %s / %s / %s %n", inventory_number, make, model, year, picture);

	         }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
