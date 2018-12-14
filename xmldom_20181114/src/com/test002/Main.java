package com.test002;

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
			File inputFile = new File("CD_CATALOG.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			

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
			
			
			//Ư�� �ڽ� ������Ʈ Ž��
			//�Ϲ������� �ݺ������� ��Ÿ����
			//�θ� ���� ������Ʈ�� Ž���ϰ�,
			//�ݺ����� �̿��ؼ� ���������� �����Ѵ�.
			//�θ� ���� ������Ʈ�� �ڽ� ������Ʈ�� �߰��� Ž��
			
	         NodeList nList = root.getElementsByTagName("CD");
	         for (int a = 0; a < nList.getLength(); ++a) {
	            // Ư�� ��� ��ü�� ������Ʈ ��ü�� ����ȯ
	            Element vElement = (Element) nList.item(a);

	            // Ư�� �ڽ� ������Ʈ�� �ؽ�Ʈ �ڷ� ��ȯ
	            String title = vElement.getElementsByTagName("TITLE").item(0).getTextContent();
	            String artist = vElement.getElementsByTagName("ARTIST").item(0).getTextContent();
	            String country = vElement.getElementsByTagName("COUNTRY").item(0).getTextContent();
	            String company = vElement.getElementsByTagName("COMPANY").item(0).getTextContent();
	            String price = vElement.getElementsByTagName("PRICE").item(0).getTextContent();
	            String year = vElement.getElementsByTagName("YEAR").item(0).getTextContent();
	            
	            System.out.printf("%d.%ntitle : %s%nartist : %s%ncountry : %s%ncompany"
	            		+ " : %s%nprice : %s%nyear : %s%n----------------------------------%n",a+1,  title, artist, country, company, price, year);

	         }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
