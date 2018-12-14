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
			// ���������� �����ϴ� xml ���Ͽ� ���� �޸� �ε�
			// ����) xml ������ ������Ʈ �̸� ��ġ�� �����Ѵ�.
			File inputFile = new File("VEHICLES.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			System.out.println(doc.toString());
			

			// ��Ʈ ������Ʈ Ž��
			// XML�� �����ϴ� ��� ������Ʈ�� ��Ʈ ������Ʈ��
			Element root = doc.getDocumentElement();

			/*
			 * Node��ü.getNodeName() : ��� �̸�. �±��� ��� �±� �̿�
			 * Node��ü.getNodeType() : ��� Ÿ��, 1�� ��� ������Ʈ
			 * Node��ü.getNodeValue() : ��� ��. �ؽ�Ʈ ����� ��� �ؽ�Ʈ
			 * Element��ü.getTagName() : �±� �̸�
			 * Element��ü.getTextContent() : �±װ� ���� �ؽ�Ʈ �ڷ�
			 */

			System.out.println("Root element : " + root.getTagName());
			
			
			//Ư�� �ڽ� ������Ʈ Ž��
			//�Ϲ������� �ݺ������� ��Ÿ����
			//�θ� ���� ������Ʈ�� Ž���ϰ�,
			//�ݺ����� �̿��ؼ� ���������� �����Ѵ�.
			//�θ� ���� ������Ʈ�� �ڽ� ������Ʈ�� �߰��� Ž��
			
	         NodeList nList = doc.getElementsByTagName("VEHICLE");
	         for (int a = 0; a < nList.getLength(); ++a) {
	            // Ư�� ��� ��ü�� ������Ʈ ��ü�� ����ȯ
	            Element vElement = (Element) nList.item(a);

	            // Ư�� �ڽ� ������Ʈ�� �ؽ�Ʈ �ڷ� ��ȯ
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
