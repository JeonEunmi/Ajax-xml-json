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

			// ������Ʈ�� �������� Ž������ �ʰ�,
			// �ڽ� ������Ʈ ��ü�� Ž�� �����Ѵ�.
			// Node��ü.getChildNodes()

			File inputFile = new File("VEHICLES.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			Element root = doc.getDocumentElement();

			NodeList nList = root.getElementsByTagName("VEHICLE");
			for (int a = 0; a < nList.getLength(); ++a) {
				Element vElement = (Element) nList.item(a);

				System.out.println("---------------------");
				  // Ư�� �ڽ� ������Ʈ�� �ؽ�Ʈ �ڷ� ��ȯ
	            String inventory_number = vElement.getElementsByTagName("INVENTORY_NUMBER").item(0).getTextContent();
	            String make = vElement.getElementsByTagName("MAKE").item(0).getTextContent();
	            String model = vElement.getElementsByTagName("MODEL").item(0).getTextContent();
	            String year = vElement.getElementsByTagName("YEAR").item(0).getTextContent();
	            String picture = vElement.getElementsByTagName("PICTURE").item(0).getTextContent();
	            
	            System.out.printf("%s / %s / %s / %s / %s %n", inventory_number, make, model, year, picture);

				// OPTIONS ������Ʈ Ž�� �� ��� ���
				System.out.println("OPTIONS--------------");
				Node optionsNode = vElement.getElementsByTagName("OPTIONS").item(0);
				NodeList optionsChildNodes = optionsNode.getChildNodes();

				// Node ��ü���� ������Ʈ ��ü�� �ִ� ���� �ƴϰ�, �ؽ�Ʈ ���, �Ӽ� ��尡 �ִ�.
				for (int b = 0; b < optionsChildNodes.getLength(); ++b) {
					// ����) Node ��ü���� ������Ʈ ��ü�� �ִ� ���� �ƴϰ�,
					// �ؽ�Ʈ ���, �Ӽ� ��尡 ���� �� �ִ�.
					Node optionsChildNode = optionsChildNodes.item(b);

					// ������Ʈ ��ü���� Ȯ���ϴ� ���� �ʿ�
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
